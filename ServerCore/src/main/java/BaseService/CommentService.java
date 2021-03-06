package BaseService;

import Bean.Comment;
import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import util.JwtUtils;

public class CommentService {
    private CommentDAO commentDAO = new CommentDAO();
    private PostDAO postDAO = new PostDAO();
    private UserDAO userDAO = new UserDAO();

    public boolean publishComment(int postId, String text, String jwt){
        try {
            String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
            Comment comment = new Comment(text, postDAO.getPost(postId), userDAO.getUserByUsername(username));
            return commentDAO.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(int commentId, String jwt){
        try {
            if(!JwtUtils.getInstance().getUserNameFromJwtToken(jwt).isEmpty()) {
                return commentDAO.delComment(commentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*public boolean publishComment(int postId, int authorId, String text){
        Comment comment = new Comment(text, commentDAO.findById(Post.class, postId), commentDAO.findById(User.class, authorId));
        return commentDAO.addEntity(comment);
    }

    public boolean deleteComment(int commentId){
        Comment comment = commentDAO.findById(Comment.class, commentId);
        return commentDAO.delEntity(commentId);
    }*/
}
