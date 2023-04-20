package src.model.collections.exportComic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.JAXB;

import src.model.collections.ComicCollection;
import src.model.comics.Comic;

public class XMLExport implements Export {

    private FileWriter writer;
    private String fileDest;
    private File file;

    public XMLExport(String filename) throws IOException {
        this.fileDest = filename + ".xml";
        this.file = new File(fileDest);
        if (!(file.exists())) {
            this.writer = new FileWriter(fileDest);
        } else {
            file.delete();
            this.writer = new FileWriter(fileDest);
        }
        System.out.println("Exporter Created!");
    }

    @Override
    public void exportCollection(ComicCollection comics) throws IOException {
        try {
            for (Comic comic : comics.getCollection()) {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.writeValue(this.writer, comic);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Verify that file works
        BufferedReader br = new BufferedReader(new FileReader(fileDest));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
    
}
