package businesslayer;

public class logManagerFactory {

    logImplementation logManager;

    public logImplementation GetLogManager(){
        if(logManager == null){
            logManager = new logImplementation();
        }
        return logManager;
    }
}
