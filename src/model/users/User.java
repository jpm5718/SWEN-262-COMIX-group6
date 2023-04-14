/**
 * @author Dan Corcoran
 */


package src.model.users;

import src.model.collections.PersonalCollection;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final Map<String, PersonalCollection> personalCollections;
    private String username;

    //this is pretty naive. We might want to implement some sort of password hashing
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        personalCollections = new HashMap<>();
    }

    public void addPersonalCollection(String name) {
        PersonalCollection collection = new PersonalCollection(name);
        personalCollections.put(name, collection);
    }

    public void removePersonalCollection(String name) {
        if (personalCollections.remove(name) == null) {
            System.out.println("Collection \"" + name + "\" does not exist");
        }
    }

    public String getUsername(){
        return this.username;
    }
}
