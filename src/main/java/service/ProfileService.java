package service;

import dao.Database;
import model.Post;
import model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ProfileService {
    private Map<String, Profile> profileMap = Database.getProfileList();

    public ProfileService(){
        profileMap.put("username1",    //Map Key (Integer)
                new Profile( // Map Value
                        "username1", // example
                        "password",
                        "./avatars/1.jpg"));
    }

    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profileMap.values());
    }

    public Profile getProfile(String username){
        return profileMap.get(username);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profileMap.size() + 1);
        profileMap.put(profile.getUsername(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile){
        if (profile.getUsername().equals("")) {
            return null;
        }
        profileMap.put(profile.getUsername(), profile);
        return profile;
    }

    public Profile removeProfile(String username){
        return profileMap.remove(username);
    }

    public boolean isLogin(String username, String password){
        Profile profile = profileMap.get(username);
        if(profile == null){
            return false;
        }else if(!profile.getPassword().equals(password)){
            return false;
        }else{
            return true;
        }
    }

    public Profile login(String username, String password){
        Profile profile = profileMap.get(username);
        if(profile == null){
            return null;
        }else if(!profile.getPassword().equals(password)){
            return null;
        }else{
            return profile;
        }
    }

    public boolean existUsername(String username){
        return profileMap.containsKey(username);
    }
}
