package dataaccesslayer.file;

import java.io.IOException;

public interface fileAccess {
    public void SaveImageInFile(String completeURL, String tourName) throws IOException;
    public void DeleteImage(String tourName);
}
