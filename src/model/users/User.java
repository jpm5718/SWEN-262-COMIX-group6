/**
 * @author Dan Corcoran
 */


package src.model.users;

import src.model.collections.PersonalCollection;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("collections") private Map<String, PersonalCollection> personalCollections;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
        personalCollections = new HashMap<>();
    }

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password, Map<String, PersonalCollection> personalCollections) {
        this.username = username;
        this.password = password;
        this.personalCollections = personalCollections;
    }

    public void addPersonalCollection(String name) {
        PersonalCollection collection = new PersonalCollection(name);
        personalCollections.put(name, collection);
    }

    public void removePersonalCollection(String name) {
        if (personalCollections.remove(name) == null) {
            System.out.println("Collection \"" + name + "\" does not exist");
        }
        else{
            personalCollections.remove(name);
            System.out.println(name + " removed successfully");
        }
    }

    @JsonProperty("collections")
    public Map<String, PersonalCollection> getCollections(){
        return personalCollections;
    }

    public PersonalCollection getCollectionByName(String name){
       return personalCollections.get(name);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setCollections(Map<String, PersonalCollection> personalCollections) {
        this.personalCollections = personalCollections;
    }
}
