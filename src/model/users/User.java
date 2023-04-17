/**
 * @author Dan Corcoran
 */


package src.model.users;

import src.model.collections.PersonalCollection;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private final Map<String, PersonalCollection> personalCollections;
    @JsonProperty("username") private String username;

    //this is pretty naive. We might want to implement some sort of password hashing
    @JsonProperty("password") private String password;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
        personalCollections = new HashMap<>();
    }

    public void addPersonalCollection(String name) {
        PersonalCollection collection = new PersonalCollection(name);
        personalCollections.put(name, collection);
    }

    public Map<String, PersonalCollection> getPersonalCollections() {
        return this.personalCollections;
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

    public Map<String, PersonalCollection> getCollections(){
        return personalCollections;
    }

    public PersonalCollection getCollectionByName(String name){
       return personalCollections.get(name);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
