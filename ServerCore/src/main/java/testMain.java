import BaseService.PostService;
import BaseService.UserService;
import Bean.Comment;
import Bean.Post;
import Bean.User;
import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.UserDAO;

import java.util.List;


public class testMain {
    public static void main(String[] argc){
        UserService userService = new UserService();
        //userService.register("username","password");
        userService.login("username1","password");

        return;


        //PostService postService = new PostService();

        //List<Post> posts = postService.getPublicPost(1);
        //return;
    }
}
