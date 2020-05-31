package DAO;

import beans.backingBeans.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private User user;
    private List<User> userList = new ArrayList<>();
    private User user1 = new User(1,"user1", "pass1", "user1@mail.com");
    private User user2 = new User(2,"user2", "pass2", "user2@mail.com");
    private User user3 = new User(3,"user3", "pass3", "user3@mail.com");

    public UserDAO(){
        userList = MyUserList();
    }

    public User getUserById(int id){
        userList = MyUserList();
        for(User user : userList){
            if (user.getUserId() == id){
                return user;
            }
        }
        return null;
    }

    public List<User> getListOfAll(){
        return MyUserList();
    }

    public List<User> MyUserList(){
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

    public boolean validateAccount(String userName, String password) {
        userList = getListOfAll();
        for (User user: userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


}
