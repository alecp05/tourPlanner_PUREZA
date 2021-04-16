package businesslayer;

public class logManagerFactory {

    private static logManager logManager;

    public static logManager GetLogManager(){
        if(logManager == null){
            logManager = new logImplementation();
        }
        return logManager;
    }
}
