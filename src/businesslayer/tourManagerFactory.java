package businesslayer;

public class tourManagerFactory {

    tourImplementation tourManager;

    public tourImplementation GetTourManager(){
        if(tourManager == null){
            tourManager = new tourImplementation();
        }
        return tourManager;
    }
}
