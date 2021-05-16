package businesslayer;

import java.io.IOException;

public class tourManagerFactory {

    private static tourManager tourManager;

    public static tourManager GetTourManager() throws IOException {
        if(tourManager == null){
            tourManager = new tourImplementation();
        }
        return tourManager;
    }
}
