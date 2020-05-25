package BaseService;

import Bean.Comment;
import Bean.Post;
import Bean.Profile;
import DAO.PostDAO;
import DAO.ProfileDAO;
import util.JwtUtils;

import java.util.List;

public class PostService {
    private PostDAO postDAO = new PostDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public List<Post> getAllPosts(){
        return postDAO.findAll();
    }

    public List<Post> getMyAllPosts(String jwt){
        try{
            String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
            return postDAO.getAllPostsOfProfile(profileDAO.getProfileByUsername(username).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean publishPost(String text, String imagePath, String typeVisible, String jwt){
        try{
            String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
            return postDAO.addPost(new Post(imagePath, text, typeVisible, profileDAO.getProfileByUsername(username)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(int postId, String jwt){
        try {
            if(!JwtUtils.getInstance().getUserNameFromJwtToken(jwt).isEmpty()) {
                return postDAO.delPost(postId);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comment> showComments(int postId){
        return postDAO.getCommentOfPost(postId);
    }

    public boolean updatePost(int postId, String text, String imagePath, String typeVisible, String jwt){
        try {
            if(!JwtUtils.getInstance().getUserNameFromJwtToken(jwt).isEmpty()) {
                return postDAO.updatePost(postId, text, imagePath, typeVisible);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> getPublicPost(int authorId){
        return postDAO.getPublicPost(authorId);
    }
}
