package src.model.comics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Creators implements SpecialComicAttribute {
    @JsonProperty("creators")
    private String[] creators;

    public Creators(String creators) {
        setCreators(creators);
    }

    public Creators(@JsonProperty("creators") String[] creators) {
        this.creators = creators;
    }

    public void setCreators(String creators) {
        this.creators = creators.split("\\|");
    }

    public String[] getCreators() {
        return creators;
    }

    /*
     * 
     */
    @Override
    public String toString() {
        String returnString = "";
        String[] creators = getCreators();
        for (String index : creators) {
            returnString = returnString + index + ", ";
        }
        returnString = returnString.substring(0, returnString.length()-2);
        return returnString;
    }
}
