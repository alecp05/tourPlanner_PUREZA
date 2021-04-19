package dataaccesslayer.file;

import dataaccesslayer.file.fileAccess;

public class filesystem implements fileAccess {

    private String connectionString;

    public filesystem(){
        //get info from config file
        connectionString = "...";
        //establish DB connection
    };
}
