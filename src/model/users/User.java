package src.model.users;

import src.model.collections.PersonalCollection;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class User {
    @JsonProperty("collections") private Map<String, PersonalCollection> personalCollections;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;

    private static ObjectMapper mapper = new ObjectMapper();

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
        personalCollections = new HashMap<>();
    }

    public void addPersonalCollection(String name) throws Exception {
        PersonalCollection collection = new PersonalCollection(name);
        personalCollections.put(name, collection);
        //mapper.writeValue(new File("data/users.json"), userArray);

        // File path to the JSON file
        String filePath = "data/users.json";
        // Read the JSON file into a JsonNode object
        JsonNode jsonNode = mapper.readTree(new File(filePath));
        for (JsonNode userNode : jsonNode) {
            String usernameNode = userNode.get("username").asText();
            // Check the value of the node and compare it with the specific value you want to locate
            System.out.println(collection + " username: " + username + " usernameNode: " + usernameNode + ": " + userNode);
            if (usernameNode.equals(username)) {
                // If the values match, update the value of the node
                ((ObjectNode) jsonNode).put("collections", collection.toString());
            }
        }
        mapper.writeValue(new File(filePath), jsonNode);

    }


    public void removePersonalCollection(String name) {
        if (personalCollections.remove(name) == null) {
            System.out.println("Collection \"" + name + "\" does not exist");
        }
        else{
            personalCollections.remove(name);
            //add code to update the json file and remove the collection
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
