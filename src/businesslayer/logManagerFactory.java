package businesslayer;

import java.io.IOException;

public class logManagerFactory {

    private static logManager logManager;

    public static logManager GetLogManager() throws IOException {
        if(logManager == null){
            logManager = new logImplementation();
        }
        return logManager;
    }
}
