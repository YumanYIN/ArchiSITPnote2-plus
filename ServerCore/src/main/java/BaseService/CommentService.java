package BaseService;

import Bean.Comment;
import Bean.Post;
import Bean.Profile;
import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.ProfileDAO;

public class CommentService {
    private CommentDAO commentDAO = new CommentDAO();

    public boolean publishComment(int postId, int authorId, String text){
        Comment comment = new Comment(text, commentDAO.findById(Post.class, postId), commentDAO.findById(Profile.class, authorId));
        return commentDAO.addEntity(comment);
    }

    public boolean deleteComment(int commentId){
        Comment comment = commentDAO.findById(Comment.class, commentId);
        return commentDAO.delEntity(commentId);
    }
}
