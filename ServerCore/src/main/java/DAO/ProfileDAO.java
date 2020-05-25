package DAO;

import Bean.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileDAO extends BaseDAO {
    public boolean addProfile(Profile profile){
        String sql = "INSERT INTO profile VALUES(DEFAULT, ?, ?, ?)";
        Object param[] = {
                profile.getUsername(),
                profile.getPassword(),
                profile.getAvatar()
        };
        if (updateByParams(sql, param)){
            return true;
        }
        else{
            System.out.println("please check your input");
            return false;
        }
    }

    public boolean authentication(String username, String password){
        String sql = "SELECT * FROM profile WHERE `username` = ? AND `password` = ?";
        Object param[] = {
                username,
                password
        };
        List<Map<String, Object>> result = select(sql, param);
        if(result.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean isLogin(String username, String password){
        return true;
    }

    public Profile getProfileByUsername(String username){
        String sql = "SELECT * FROM profile WHERE `username` = ?";
        Object param[] = {
                username
        };
        List<Map<String, Object>> result = select(sql, param);
        return turnToList(result).get(0);
    }

    public Profile getProfileById(int id){
        String sql = "SELECT * FROM profile WHERE `id` = ?";
        Object param[] = {
                id
        };
        List<Map<String, Object>> result = select(sql, param);
        return turnToList(result).get(0);
    }

    public boolean existUsername(String username){

        return false;
    }

    private List<Profile> turnToList(List<Map<String, Object>> result){
        List<Profile> profiles = new ArrayList<Profile>();
        for (Map<String, Object> profileMap:result) {
            Profile profile = new Profile();
            profile.setId(Integer.valueOf(profileMap.get("id").toString()));
            profile.setUsername(profileMap.get("username").toString());
            profile.setPassword(profileMap.get("password").toString());
            profile.setAvatar(profileMap.get("avatar").toString());
            profiles.add(profile);
        }
        return profiles;
    }
}
