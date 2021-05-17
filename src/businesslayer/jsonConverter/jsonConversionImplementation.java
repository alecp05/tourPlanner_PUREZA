package businesslayer.jsonConverter;

import businesslayer.mapQuestApi.apiHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataaccesslayer.daos.tourModelDAO;
import gui.viewmodels.addTourViewModel;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class jsonConversionImplementation {

    private static final Logger logger = LogManager.getLogger(addTourViewModel.class);

    //connecting to database through tourModelDAO
    private tourModelDAO tourModelDAO;

    //fileAccess with apiHandler (createImage)
    private apiHandler apiHandler;

    public jsonConversionImplementation() throws IOException {
        tourModelDAO = new tourModelDAO();
        apiHandler = new apiHandler();
    }

    public void importingJsonContent(String filePath){

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        //System.out.println(file.exists());
        try {
            List<tourModel> toursList = objectMapper.readValue(file, new TypeReference<List<tourModel>>(){});

            for(int i = 0; i<toursList.size(); i++){

                String name = toursList.get(i).getTourName();
                String description = toursList.get(i).getTourDescription();
                int distance = toursList.get(i).getTourDistance();
                String start = toursList.get(i).getTourStart();
                String end = toursList.get(i).getTourEnd();

                //Check if TourName is already taken
                int checkNumber = 0;
                List<tourModel> allTours = tourModelDAO.GetTourItems();
                for(int j = 0; j < allTours.size();j++){
                    if(allTours.get(j).tourName.equals(name)){
                        checkNumber = 1;
                    }
                }

                if(checkNumber ==0){
                    //insert into Database through DAO
                    tourModelDAO.InsertTourItems(name,description,distance,start,end);
                    //creating Image
                    apiHandler.GetRequestMethod(name,start,end);
                    logger.info("Inserting Into Database through DAO");
                }else if(checkNumber==1){
                    logger.warn("Tour already exists (TourName is taken)");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatingExport(String tourName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<tourModel> allTours = tourModelDAO.GetTourItems();
        for(int j = 0; j < allTours.size();j++){
            if(allTours.get(j).tourName.equals(tourName)){
                tourModel tour = new tourModel();

                tour.tourName = allTours.get(j).tourName;
                tour.tourDescription = allTours.get(j).tourDescription;
                tour.tourDistance = allTours.get(j).tourDistance;
                tour.tourStart = allTours.get(j).tourStart;
                tour.tourEnd = allTours.get(j).tourEnd;

                String tempTourNameForFile = tourName.replace(" ","");
                //System.out.println(tourName);

                //creating json file!
                objectMapper.writeValue(
                        new FileOutputStream("jsonFiles/" + tempTourNameForFile + ".json"), tour);
                logger.warn("Exporting is done");
            }
        }
    }
}
