package dataaccesslayer;

import models.logModel;
import models.tourModel;

import java.util.List;

public interface dataAccess {
    public List<tourModel> GetTourItems();
    public List<logModel> GetLogItems();
}
