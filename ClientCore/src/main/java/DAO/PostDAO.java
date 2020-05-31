package DAO;

import beans.backingBeans.Post;
import beans.backingBeans.User;

import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private Post post;
    private List<Post> postList = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();

    public PostDAO(){
        postList = AllPostList();
    }

    public List<Post> getPostListByUser(User user){
        List<Post> list = new ArrayList<>();
        for (Post post: postList) {
            if(post.getAuthor() == user){
                list.add(post);
            }
        }
        return list;
    }

    public List<Post> AllPostList(){
        postList.clear();
        postList.add(new Post(1,
                "1.jpg",
                "my post1",
                "public",
                userDAO.getUserById(1)));
        postList.add(new Post(2,
                "2.jpg",
                "my post2",
                "public",
                userDAO.getUserById(2)));
        return postList;
    }


}
