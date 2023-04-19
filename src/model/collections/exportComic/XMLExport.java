package src.model.collections.exportComic;

import java.io.FileWriter;
import java.io.IOException;

import src.model.collections.ComicCollection;

public class XMLExport implements Export {

    private FileWriter writer;

    public XMLExport(String filename) throws IOException {
        String fileDest = "data/" + filename + ".xml";
        this.writer = new FileWriter(fileDest);
    }

    @Override
    public void exportCollection(ComicCollection comics) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportCollection'");
    }
    
}
