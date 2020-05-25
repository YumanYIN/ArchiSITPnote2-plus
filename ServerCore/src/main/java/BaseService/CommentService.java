package BaseService;

import Bean.Comment;
import DAO.CommentDAO;
import DAO.PostDAO;
import DAO.ProfileDAO;

public class CommentService {
    private CommentDAO commentDAO = new CommentDAO();
    private PostDAO postDAO = new PostDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public boolean publishComment(int postId, int authorId, String text){
        Comment comment = new Comment(text, postDAO.getPost(postId), profileDAO.getProfileById(authorId));
        return commentDAO.addComment(comment);
    }

    public boolean deleteComment(int commentId){
        return commentDAO.delComment(commentId);
    }

    /*public boolean publishComment(int postId, int authorId, String text){
        Comment comment = new Comment(text, commentDAO.findById(Post.class, postId), commentDAO.findById(Profile.class, authorId));
        return commentDAO.addEntity(comment);
    }

    public boolean deleteComment(int commentId){
        Comment comment = commentDAO.findById(Comment.class, commentId);
        return commentDAO.delEntity(commentId);
    }*/
}
