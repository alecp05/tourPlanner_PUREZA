package businesslayer.jsonConverter;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class jsonConverterHandler {

    private jsonConversionImplementation jsonConversionImplementation = new jsonConversionImplementation();

    public jsonConverterHandler() throws IOException {
    }

    public void fileChoosing(){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            //handover the path of file and save the content
            jsonConversionImplementation.importingJsonContent(selectedFile.getAbsolutePath());
            //System.out.println(selectedFile.getAbsolutePath());
        }
    }
}
