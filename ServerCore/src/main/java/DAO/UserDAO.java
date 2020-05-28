package DAO;

import Bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO extends BaseDAO {
    public boolean addUser(User user){
        String sql = "INSERT INTO user VALUES(DEFAULT, ?, ?, ?)";
        Object param[] = {
                user.getUsername(),
                user.getPassword(),
                user.getAvatar()
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
        String sql = "SELECT * FROM user WHERE `username` = ? AND `password` = ?";
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

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM user WHERE `username` = ?";
        Object param[] = {
                username
        };
        List<Map<String, Object>> result = select(sql, param);
        return turnToList(result).get(0);
    }

    public User getUserById(int id){
        String sql = "SELECT * FROM user WHERE `id` = ?";
        Object param[] = {
                id
        };
        List<Map<String, Object>> result = select(sql, param);
        return turnToList(result).get(0);
    }

    public boolean existUsername(String username){

        return false;
    }

    private List<User> turnToList(List<Map<String, Object>> result){
        List<User> users = new ArrayList<User>();
        for (Map<String, Object> userMap:result) {
            User user = new User();
            user.setId(Integer.valueOf(userMap.get("id").toString()));
            user.setUsername(userMap.get("username").toString());
            user.setPassword(userMap.get("password").toString());
            user.setAvatar(userMap.get("avatar").toString());
            users.add(user);
        }
        return users;
    }
}
