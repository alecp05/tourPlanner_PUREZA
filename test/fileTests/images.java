package fileTests;

import dataaccesslayer.file.filesystem;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class images {

    @Test
    public void testSavingImageInFile_shouldBeSaved() throws IOException {
        //System.out.println("testing images");
        filesystem filesystem = new filesystem();
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
            System.out.println("check");
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
            System.out.println("not working");
            checkNumber = 1;
        }

        assertTrue("The Image should not be converted and saved properly", checkNumber == 1);

    }


}
