package businesslayer;

import models.logModel;

import java.util.List;

public interface logManager {

    public List<logModel> GetLogItems();
    public List<logModel> SearchLogItems(String itemName, boolean caseSensitive);
}
