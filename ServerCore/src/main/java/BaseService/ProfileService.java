package BaseService;

import Bean.Profile;
import DAO.ProfileDAO;
import util.JwtUtils;

public class ProfileService {
    private static ProfileDAO profileDAO = new ProfileDAO();

    public String login(String username, String password){
        try{
            if(profileDAO.authentication(username,password)){
                Profile profile = profileDAO.getProfileByUsername(username);
                return JwtUtils.getInstance().generateJwtToken(profile.getUsername());
                //return profile;
            }else{
                return "Votre connexion a ¨¦chou¨¦";
            }
        }catch (Exception e){
            return "Votre connexion a ¨¦chou¨¦, il y a un probl¨¨me";
        }
    }

    public boolean register(String username, String password){
        if(!profileDAO.existUsername(username)){
            Profile profile = new Profile(username, password, "");
            return profileDAO.addProfile(profile);
        }else{
            return false;
        }
    }

    public String logout(String jwt){
        return JwtUtils.getInstance().generateJwtToken("");
    }
}
