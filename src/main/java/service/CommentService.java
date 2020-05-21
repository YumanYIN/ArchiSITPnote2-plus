package service;

import dao.Database;
import model.Comment;
import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {
    private Map<Integer, Comment> commentMap = Database.getCommentList();

    public CommentService(){

    }

    public List<Comment> getAllComments(){
        return new ArrayList<Comment>(commentMap.values());
    }

    public Comment getComment(int id){
        return commentMap.get(id);
    }

    public Comment addComment(Comment comment){
        comment.setId(commentMap.size() + 1);
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(Comment comment){
        if(comment.getId() <= 0){
            return null;
        }
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(int id){
        return commentMap.remove(id);
    }

    public List<Comment> getAllCommentsByPostId(int postId){
        List<Comment> comments = getAllComments();
        List<Comment> result = new ArrayList<Comment>();
        for(Comment comment:comments){
            if(comment.getPost().getId()==postId){
                result.add(comment);
            }
        }
        return result;
    }
}
