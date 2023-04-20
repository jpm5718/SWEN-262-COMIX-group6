/**
 * MAY NEED EDITING
 */

package src.model.users;

import src.model.collections.PersonalCollection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;
    @JsonProperty("personalCollection") private PersonalCollection collection;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public User(@JsonProperty("username") String username, 
                @JsonProperty("password") String password, 
                @JsonProperty("personalCollection")PersonalCollection collection) {
        this.username = username;
        this.password = password;
        this.collection = collection;
    }

    // public ObjectNode toJson() {
    //     ObjectMapper mapper = new ObjectMapper();
    //     ObjectNode userNode = mapper.createObjectNode();
    //     userNode.put("username", username);
    //     userNode.put("password", password);
    //     userNode.set("personalCollection", collection.toJson());
    //     return userNode;
    // }

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
