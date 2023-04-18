package src.model.comics;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ComicDeserializer extends JsonDeserializer<Comic> {

    @Override
    public Comic deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("grade")) {
            // if the comic has a "grade" field, deserialize it as a GradedComic
            return jp.getCodec().treeToValue(node, GradedComic.class);
        } else {
            // otherwise, deserialize it as a ComicBook
            return jp.getCodec().treeToValue(node, ComicBook.class);
        }
    }
}

