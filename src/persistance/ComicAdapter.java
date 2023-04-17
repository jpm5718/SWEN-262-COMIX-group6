package src.persistance;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import src.model.collections.Collection;


public interface ComicAdapter {
    public void exportAsFormat(Collection dataCollection);
    public Collection importToFormat() throws Exception;
}
