package src.model.comics;

public class Creators implements SpecialComicAttribute {

    private String[] creators;

    public Creators(String creators) {
        setCreators(creators);
    }

    public void setCreators(String creators) {
        this.creators = creators.split("\\|");
    }

    public String[] getCreators() {
        return creators;
    }
}
