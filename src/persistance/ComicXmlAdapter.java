package src.persistance;

import src.model.collections.Collection;
import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.comics.ComicBook;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
public class ComicXmlAdapter implements ComicAdapter {

    String filename;
    public ComicXmlAdapter(String filename) {
        this.filename = "data/" + filename + ".xml";
    }

    @Override
    public void exportAsFormat(Collection dataCollection) {
        try {
            JAXBContext context = JAXBContext.newInstance(DatabaseCollection.class);
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshal.marshal(dataCollection, new FileOutputStream(this.filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public ComicCollection importToFormat() throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(ComicBook[].class);
            Unmarshaller unmarsh = context.createUnmarshaller();
            ComicBook[] comicList = (ComicBook[]) unmarsh.unmarshal(new File(this.filename));
            DatabaseCollection collection = new DatabaseCollection();
            for (ComicBook book: comicList) {
                collection.addComic(book);
            }
            return collection;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
