package service;

import dao.Database;
import model.Post;
import model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService {
    private Map<Integer, Post> postMap = Database.getPostList();

    public PostService(){
        postMap.put(1,    //Map Key (Integer)
                new Post( // Map Value
                        "./images/1.jpg", // beans
                        "Beautiful days",
                        "public",
                        new Profile("username", "password", "./avatars/1.jpg")));
    }

    public List<Post> getAllPosts(){
        return new ArrayList<Post>(postMap.values());
    }

    public Post getPost(int idPost){
        return postMap.get(idPost);
    }

    public Post addPost(Post post){
        post.setId(postMap.size() + 1);
        postMap.put(post.getId(), post);
        return post;
    }

    public Post updatePost(Post post){
        if(post.getId() <= 0){
            return null;
        }
        postMap.put(post.getId(), post);
        return post;
    }

    public Post removePost(int idPost){
        return postMap.remove(idPost);
    }

    public List<Post> getAllPostsByUserId(int userId){
        List<Post> posts = new ArrayList<Post>();
        for(Post post:getAllPosts()){
            if(post.getAuthor().getId() == userId){
                posts.add(post);
            }
        }
        return posts;
    }

    public List<Post> getPublicPostsByUserId(int userId){
        List<Post> posts = new ArrayList<Post>();
        for(Post post:getAllPosts()){
            if((post.getAuthor().getId() == userId) && (post.getTypeVisible().equals("public"))){
                posts.add(post);
            }
        }
        return posts;
    }

}
