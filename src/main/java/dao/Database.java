package dao;

import model.Comment;
import model.Post;
import model.Profile;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Map<Integer, Post> postList = new HashMap<Integer, Post>();
    private static Map<String, Profile> profileList = new HashMap<String, Profile>();
    private static Map<Integer, Comment> commentList = new HashMap<Integer, Comment>();

    public static Map<Integer, Post> getPostList(){
        Post post1 = new Post("./post/image1.jpg","text1","public",profileList.get("username1"));
        Post post2 = new Post("./post/image2.jpg","text2","public",profileList.get("username1"));
        Post post3 = new Post("./post/image3.jpg","text3","private",profileList.get("username1"));
        Post post4 = new Post("./post/image4.jpg","text4","public",profileList.get("username2"));
        Post post5 = new Post("./post/image5.jpg","text5","private",profileList.get("username2"));
        Post post6 = new Post("./post/image6.jpg","text6","public",profileList.get("username3"));
        Post post7 = new Post("./post/image7.jpg","text7","private",profileList.get("username3"));
        postList.put(1, post1);
        postList.put(2, post2);
        postList.put(3, post3);
        postList.put(4, post4);
        postList.put(5, post5);
        postList.put(6, post6);
        postList.put(7, post7);
        return postList;
    }

    public static Map<String, Profile> getProfileList(){
        Profile profile1 = new Profile("username1", "password", "./avatar/avatar1.jpg");
        Profile profile2 = new Profile("username2", "password", "./avatar/avatar2.jpg");
        Profile profile3 = new Profile("username3", "password", "./avatar/avatar3.jpg");
        profileList.put(profile1.getUsername(), profile1);
        profileList.put(profile2.getUsername(), profile2);
        profileList.put(profile3.getUsername(), profile3);
        return profileList;
    }

    public static Map<Integer, Comment> getCommentList(){
        Comment comment1 = new Comment("message1",  postList.get(1), profileList.get("username2"));
        Comment comment2 = new Comment("message2",  postList.get(1), profileList.get("username3"));
        Comment comment3 = new Comment("message3",  postList.get(1), profileList.get("username2"));
        Comment comment4 = new Comment("message4",  postList.get(4), profileList.get("username1"));
        commentList.put(1,comment1);
        commentList.put(2,comment2);
        commentList.put(3,comment3);
        commentList.put(4,comment4);
        return commentList;
    }




}
