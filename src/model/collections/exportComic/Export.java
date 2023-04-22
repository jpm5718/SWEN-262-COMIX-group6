package src.model.collections.exportComic;

import java.io.IOException;

import src.model.collections.ComicCollection;

public interface Export {
    public void exportCollection(ComicCollection comics) throws IOException;
}
