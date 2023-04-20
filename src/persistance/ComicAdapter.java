package src.persistance;

import src.model.collections.Collection;
import src.model.collections.DatabaseCollection;


public interface ComicAdapter {
    public void exportAsFormat(Collection dataCollection);
    public DatabaseCollection importToFormat() throws Exception;
}
