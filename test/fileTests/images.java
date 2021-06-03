package fileTests;

import dataaccesslayer.file.filesystem;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class images {

    private filesystem filesystem;

    @Before
    public void setup(){
        filesystem = new filesystem();
    }

    @Test
    public void testSavingImageInFile_shouldBeSaved() throws IOException {
        //System.out.println("testing images");
        try{
            filesystem.SaveImageInFile("https://i.pinimg.com/originals/ab/ce/cc/abcecc889ad3c5705166f8edf3efe1a6.jpg"
                    ,"testingImage01");
        }catch (Exception e){
            System.out.println("Could not convert Url into a Picture");
        }
        File tmpDir = new File("src/tourImages/"+"testingImage01.jpg");
        boolean exists = tmpDir.exists();
        int checkCount = 0;
        if(exists){
            checkCount = 1;
            //System.out.println("check");
        }

        assertTrue("The Image is not being saved properly",checkCount == 1);

    }

    @Test
    public void testSavingImageInFile_shouldNotBeSaved() throws IOException {
        filesystem filesystem = new filesystem();
        int checkNumber = 0;

        try {
            filesystem.SaveImageInFile("---","testingImage02");
        }catch (Exception e){
            //System.out.println("not working");
            checkNumber = 1;
        }

        assertTrue("The Image should not be converted and saved properly", checkNumber == 1);

    }


    @Test
    public void testDeletingImageInFile_shouldBeDeleted(){
        try{
            filesystem.SaveImageInFile("https://i.pinimg.com/originals/ab/ce/cc/abcecc889ad3c5705166f8edf3efe1a6.jpg"
                    ,"testingImage02");
        }catch (Exception e){
            System.out.println("Could not convert Url into a Picture");
        }

        int checkNumber = 0;
        try{
            filesystem.DeleteImage("testingImage02");
            checkNumber = 1;
        }catch (Exception e){
            System.out.println("Could not convert Url into a Picture");
        }

        assertTrue("The Image could not be deleted", checkNumber == 1);

    }

    @Test
    public void testSaveImageInGallery_shouldBeSaved() throws IOException {
        BufferedImage img = ImageIO.read(new File("C:/Users/alecp/OneDrive/Bilder/bild_1.jpeg"));
        //String pathForImage = selectedFile.getName();
        int checkCount = 0;
        try {
            filesystem.saveImageForGallery(img,"bild_01.jpeg");
        }catch (Exception e){
            System.out.println("Image could not be saved in Gallery");
        }

        File tmpDir = new File("./galleryImages/"+"bild_01.jpeg");
        boolean exists = tmpDir.exists();
        if(exists){
            checkCount = 1;
            //System.out.println("check");
        }
        assertTrue("Image could not be saved in Gallery", checkCount == 1);
    }

    @Test
    public void testGalleryNames_shouldNotBeNull(){
        List<String> allGalleryNames = new ArrayList<>();
        allGalleryNames = filesystem.getGalleryNames();

        int checkCount = 0;
        if(allGalleryNames != null){
            checkCount = 1;
        }
        assertTrue( "All ImagesNames are not correctly generated", checkCount == 1);
    }

    @Test
    public void testDeletingGalleryImage_shouldBeDeleted() throws IOException {
        BufferedImage img = ImageIO.read(new File("C:/Users/alecp/OneDrive/Bilder/bild_1.jpeg"));
        int checkCount = 0;
        try {
            filesystem.saveImageForGallery(img,"bild_02.jpeg");
        }catch (Exception e){
            System.out.println("Image could not be saved in Gallery");
        }
        try{
            filesystem.deleteImageFromGallery("bild_02.jpeg");
            checkCount = 1;
        }catch (Exception e){
            System.out.println("Could not convert Url into a Picture");
        }

        assertTrue("The Image could not be deleted", checkCount == 1);
    }
}
