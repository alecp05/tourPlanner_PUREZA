package businesslayer.jsonConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataaccesslayer.daos.tourModelDAO;
import models.tourModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class jsonConversionImplementation {

    //connecting to database through tourModelDAO
    private tourModelDAO tourModelDAO;

    public jsonConversionImplementation() throws IOException {
        tourModelDAO = new tourModelDAO();
    }

    public void importingJsonContent(String filePath){

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        //System.out.println(file.exists());
        try {
            List<tourModel> toursList = objectMapper.readValue(file, new TypeReference<List<tourModel>>(){});

            for(int i = 0; i<toursList.size(); i++){
                //System.out.println(toursList.get(i).getTourName());
                String name = toursList.get(i).getTourName();
                //System.out.println(toursList.get(i).getTourDescription());
                String description = toursList.get(i).getTourDescription();
                //System.out.println(toursList.get(i).getTourDistance());
                int distance = toursList.get(i).getTourDistance();
                //System.out.println(toursList.get(i).getTourStart());
                String start = toursList.get(i).getTourStart();
                //System.out.println(toursList.get(i).getTourEnd());
                String end = toursList.get(i).getTourEnd();

                //insert into Database through DAO
                tourModelDAO.InsertTourItems(name,description,distance,start,end);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
