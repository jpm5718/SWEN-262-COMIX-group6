package src.persistance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.users.User;

public class UserFileDAO {
    Map<String, User> users;

    private ObjectMapper mapper;
    private String filename;

    public UserFileDAO(String filename, ObjectMapper mapper) throws IOException{
        this.filename = filename;
        this.mapper = mapper;
        load();
    }

    public boolean load() throws IOException {
        users = new TreeMap<>();
        File file = new File(filename);

        if (file.exists() && file.length() > 0) {
            User[] userArray = mapper.readValue(file, User[].class);

            for (User u : userArray) {
                users.put(u.getUsername(), u);
            }

            return true;
        } else {
            return false;
        }
    }



    public User[] convertMapToArray(){
        ArrayList<User> userAList = new ArrayList<>();

        for(User u : users.values()){
            userAList.add(u);
        }

        User[] userArray = new User[userAList.size()];
        userAList.toArray(userArray);
        return userArray;
    }

    public boolean save() throws IOException{
        User[] userArray = convertMapToArray();
        mapper.writeValue(new File(filename), userArray);

        return true;
    }

    public User addUser(User user) throws IOException{
        synchronized(users){
            User newUser =  new User(user.getUsername(), user.getPassword(), user.getCollection());
            users.put(newUser.getUsername(), newUser);
            save();

            return newUser;
        }
    }

    public User[] getUsers(){
        synchronized(users){
            return convertMapToArray();
        }
    }
}
