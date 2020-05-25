package DAO;

import Bean.Comment;
import Bean.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDAO extends BaseDAO {
    //private PostDAO postDAO = new PostDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public List<Comment> getAllCommentsOfPost(int postId){
        String sql = "SELECT * FROM comment WHERE postid = ?";
        Object param[] = { postId };
        List<Map<String, Object>> result = select(sql, param);
        return turnToComment(result);
    }

    public boolean addComment(Comment comment){
        String sql = "INSERT INTO comment VALUES(default, ?, ?, ?, ?)";
        Object param[] = {
                comment.getPost().getId(),
                comment.getAuthor().getId(),
                comment.getText(),
                comment.getCreated()
        };
        try{
            return updateByParams(sql, param);
        }catch (Exception e){
            return false;
        }
    }

    public boolean delComment(int commentId){
        String sql = "DELETE FROM `comment` WHERE `id` = ?";
        Object param[] = { commentId };
        try{
            return updateByParams(sql, param);
        }catch (Exception e){
            return false;
        }
    }

    private List<Comment> turnToComment(List<Map<String, Object>> result){
        List<Comment> comments = new ArrayList<Comment>();
        PostDAO postDAO = new PostDAO();
        for(Map<String, Object> commentMap:result){
            Comment comment = new Comment();
            comment.setId(Integer.valueOf(commentMap.get("id").toString()));
            comment.setText(commentMap.get("text").toString());
            comment.setPost(postDAO.getPost(Integer.valueOf(commentMap.get("postid").toString())));
            comment.setAuthor(profileDAO.getProfileById(Integer.valueOf(commentMap.get("authorid").toString())));
            comment.setCreated(commentMap.get("created").toString());
            comments.add(comment);
        }
        return comments;
    }
}
