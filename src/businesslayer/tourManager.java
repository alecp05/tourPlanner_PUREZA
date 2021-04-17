package businesslayer;

import models.tourModel;

import java.util.List;

public interface tourManager {

    public List<tourModel> GetTourItems();
    public List<tourModel> SearchTourItems(String itemName, boolean caseSensitive);
}
