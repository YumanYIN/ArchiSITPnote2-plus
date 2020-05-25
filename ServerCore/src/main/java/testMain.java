import BaseService.PostService;
import BaseService.ProfileService;
import Bean.Comment;
import Bean.Post;
import Bean.Profile;
import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.ProfileDAO;

import java.util.List;


public class testMain {
    public static void main(String[] argc){
        ProfileService profileService = new ProfileService();
        //profileService.register("username","password");
        profileService.login("username1","password");

        return;


        //PostService postService = new PostService();

        //List<Post> posts = postService.getPublicPost(1);
        //return;
    }
}
