package businesslayer;

import models.tourModel;

import java.util.List;

public interface tourManager {

    public List<tourModel> GetTourItems();
    public List<tourModel> SearchTourItems(String itemName, boolean caseSensitive);
    public void InsertTourItem(String name, String description, int distance, String start, String end);
    public void DeleteTourItem(String name, String description);
}
