package BaseService;

import Bean.Profile;
import DAO.ProfileDAO;

public class ProfileService {
    private static ProfileDAO profileDAO = new ProfileDAO();

    public Profile login(String username, String password){
        try{
            if(profileDAO.authentication(username,password)){
                return profileDAO.getProfileByUsername(username);
            }else{
                return new Profile();
            }
        }catch (Exception e){
            return new Profile();
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
}
