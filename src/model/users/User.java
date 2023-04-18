package src.model.users;

import src.model.collections.PersonalCollection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("personalCollection") private PersonalCollection collection;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("personalCollection")PersonalCollection collection) {
        this.username = username;
        this.password = password;
        this.collection = collection;
    }

    @JsonProperty("personalCollection")
    public PersonalCollection getCollection(){
        return collection;
    }

    @JsonProperty("username")
    public String getUsername(){
        return username;
    }

    @JsonProperty("password")
    public String getPassword(){
        return password;
    }

    public void setCollection(PersonalCollection collection) {
        this.collection = collection;
    }
}
