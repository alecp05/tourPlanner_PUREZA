package businesslayer;

import dataaccesslayer.logModelDAO;
import models.logModel;

import java.util.List;

public class logImplementation {

    logModelDAO logModelDAO = new logModelDAO();

    public List<logModel> GetLogItems(){return logModelDAO.GetLogItems();}
}
