package src.model.users;

import src.model.collections.PersonalCollection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private PersonalCollection collection;
    private String username;
    private String password;

    public User(String username, String password, PersonalCollection collection) {
        this.username = username;
        this.password = password;
        this.collection = collection; 
    }

    // public User(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("collection")PersonalCollection collection) {
    //     this.username = username;
    //     this.password = password;
    //     this.collection = collection;
    // }

    @JsonProperty("collection")
    public PersonalCollection getCollection(){
        return collection;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setCollection(PersonalCollection collection) {
        this.collection = collection;
    }
}
