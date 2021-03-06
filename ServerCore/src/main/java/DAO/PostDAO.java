package DAO;

import Bean.Comment;
import Bean.Post;
import Bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostDAO extends BaseDAO {
    private UserDAO userDAO = new UserDAO();
    private CommentDAO commentDAO = new CommentDAO();

    public Post getPost(int id){
        String sql = "SELECT * FROM `post` WHERE `id` = ?";
        Object param[] = { id };
        return turnToPost(select(sql, param)).get(0);
    }

    public List<Post> findAll(){
        String sql = "SELECT * FROM post";
        Object param[] = { };
        List<Map<String, Object>> result = select(sql, param);
        return turnToPost(result);
    }

    public List<Post> findAllPublicPosts(){
        String sql = "SELECT * FROM post WHERE `typevisible` = 'public'";
        Object param[] = {};
        List<Map<String, Object>> result = select(sql, param);
        return turnToPost(result);
    }

    public List<Post> getPublicPost(int userId){
        String sql = "SELECT * FROM post WHERE ((`author_id` = ?) OR (`author_id` <> ? AND `typevisible` = public))";
        Object param[] = {
                userId,
                userId
        };
        List<Map<String, Object>> result = select(sql, param);
        return turnToPost(result);
    }

    public boolean addPost(Post post){
        String sql = "INSERT INTO post VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        Object param[] = {
                post.getImagePath(),
                post.getCreated(),
                post.getText(),
                post.getTypeVisible(),
                post.getAuthor().getId()
        };
        try {
            return updateByParams(sql, param);
        }catch (Exception e){
            return false;
        }
    }

    public boolean delPost(int postId){
        String sql = "DELETE FROM post WHERE `id` = ?";
        Object param[] = { postId };
        try{
            return updateByParams(sql, param);
        }catch (Exception e){
            return false;
        }
    }

    public List<Comment> getCommentOfPost(int postId){
        return commentDAO.getAllCommentsOfPost(postId);
    }

    public boolean updatePost(int postId, String text, String typeVisible){
        String sql = "UPDATE `post` SET text = ?, typevisible = ? WHERE id = ?";
        Object param[] = {
                text,
                typeVisible,
                postId
        };
        try{
            return updateByParams(sql, param);
        }catch (Exception e){
            return false;
        }
    }

    public List<Post> getAllPostsOfUser(int userId){
        String sql = "SELECT * FROM `post` WHERE `author_id` = ?";
        Object param[] = { userId };
        return turnToPost(select(sql, param));
    }

    private List<Post> turnToPost(List<Map<String, Object>> result){
        List<Post> posts = new ArrayList<Post>();
        for (Map<String, Object> postMap:result) {
            Post post = new Post();
            post.setId(Integer.valueOf(postMap.get("id").toString()));
            post.setAuthor(userDAO.getUserById(Integer.valueOf(postMap.get("author_id").toString())));
            post.setCreated(postMap.get("created").toString());
            post.setImagePath(postMap.get("imagepath").toString());
            post.setText(postMap.get("text").toString());
            post.setTypeVisible(postMap.get("typevisible").toString());
            posts.add(post);
        }
        return posts;
    }
}
