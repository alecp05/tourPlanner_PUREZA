package businesslayer;

import dataaccesslayer.tourModelDAO;
import models.tourModel;

import java.util.List;

public class tourImplementation {

    tourModelDAO tourModelDAO = new tourModelDAO();

    public List<tourModel> GetTourItems(){return tourModelDAO.GetTourItems(); }
}
