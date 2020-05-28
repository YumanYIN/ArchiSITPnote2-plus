package BaseService;

import Bean.User;
import DAO.UserDAO;
import util.JwtUtils;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    public String login(String username, String password){
        try{
            if(userDAO.authentication(username,password)){
                User user = userDAO.getUserByUsername(username);
                return JwtUtils.getInstance().generateJwtToken(user.getUsername());
                //return user;
            }else{
                return "Votre connexion a ¨¦chou¨¦";
            }
        }catch (Exception e){
            return "Votre connexion a ¨¦chou¨¦, il y a un probl¨¨me";
        }
    }

    public boolean register(String username, String password){
        if(!userDAO.existUsername(username)){
            User user = new User(username, password, "");
            return userDAO.addUser(user);
        }else{
            return false;
        }
    }

    public String logout(String jwt){
        return JwtUtils.getInstance().generateJwtToken("");
    }
}
