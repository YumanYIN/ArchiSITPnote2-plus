package DAO;

import beans.backingBeans.Comment;
import beans.backingBeans.Post;
import beans.backingBeans.User;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private List<Comment> commentList = new ArrayList<>();
    private PostDAO postDAO;
    private UserDAO userDAO;

    public CommentDAO(){
    }

    public List<Comment> getListOfAll(){
        return MyCommentList();
    }

    public List<Comment> getCommentByUser(User user){
        List<Comment> list = new ArrayList<>();
        for (Comment comment: commentList) {
            if(comment.getAuthor() == user){
                list.add(comment);
            }
        }
        return list;
    }

    public List<Comment> MyCommentList() {
        User author1 = userDAO.getUserById(1);
        Post post1 = new Post();
        post1.setAuthor(author1);
        commentList.add(new Comment(1, "comment1", post1, author1));
        commentList.add(new Comment(2, "comment2", post1, author1));


        return commentList;
    }
}
