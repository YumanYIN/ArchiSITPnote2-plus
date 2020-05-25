package BaseService;

import Bean.Comment;
import Bean.Post;
import Bean.Profile;
import DAO.PostDAO;
import DAO.ProfileDAO;
import org.jboss.logging.annotations.Pos;

import java.util.List;

public class PostService {
    private PostDAO postDAO = new PostDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public List<Post> getAllPosts(int profileId){
        return postDAO.findAll(Post.class);
    }

    public boolean publishPost(String text, String imagePath, String typeVisible, int profileId){
        Post post = new Post(imagePath, text, typeVisible, profileDAO.findById(Profile.class, profileId));
        return postDAO.addEntity(post);
    }

    public boolean deletePost(int postId){
        Post post = postDAO.findById(Post.class, postId);
        return postDAO.delEntity(post);
    }

    public List<Comment> showComments(int postId){
        return postDAO.getCommentOfPost(postId);
    }

    public boolean updatePost(int postId, String text, String imagePath, String typeVisible){
        return postDAO.updatePost(postId, text, imagePath, typeVisible);
    }

    public List<Post> getPublicPost(int authorId){
        return postDAO.getPublicPost(authorId);
    }
}
