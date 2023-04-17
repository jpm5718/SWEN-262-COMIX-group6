package src.persistance;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.model.collections.Collection;
import src.model.collections.ComicCollection;


public interface ComicAdapter {
    public void exportAsFormat(Collection dataCollection);
    public ComicCollection importToFormat() throws Exception;

}
