package businesslayer;

public class tourManagerFactory {

    private static tourManager tourManager;

    public static tourManager GetTourManager(){
        if(tourManager == null){
            tourManager = new tourImplementation();
        }
        return tourManager;
    }
}
