package beans.managedBeans;

import beans.backingBeans.Comment;
import beans.backingBeans.Post;
import beans.backingBeans.User;
import com.google.gson.reflect.TypeToken;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

@ManagedBean
@RequestScoped
public class PostBean {
    private Post post;
    private List<Post> postList = new ArrayList<>();
    private List<Post> myPostList = new ArrayList<>();
    private HttpSession session;
    private Gson gson = new Gson();
    private String imageName;
    private Map<String, List<Comment>> commentLists = new HashMap<String, List<Comment>>();
    private List<Comment> commentList = new ArrayList<Comment>();

    private String jwtText = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/RESTApplication/jwt.txt";

    private WebTarget targetPost;

    public PostBean(){
        post = new Post();
        String path = "http://localhost:8080/RESTServer_war_exploded";
        Client client = ClientBuilder.newClient();
        targetPost = client.target(path+"/post");
    }

    public List<Post> getPostList() throws IOException {
        Response response = targetPost.path("allPublicPosts")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        postList = jsonToPostList(response.readEntity(String.class));
        return postList;
    }

    public List<Post> getMyPostList() throws IOException {
        File filename = new File(jwtText);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        Response response = targetPost.path("allMyPosts")
                .queryParam("jwt", line)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        myPostList = jsonToPostList(response.readEntity(String.class));
        return myPostList;
    }

    public String toMyPostList(){
        return "/myPostList.xhtml?faces-redirect=true";
    }

    public String getComment(int postId){
        Response response = targetPost.path("showComments")
                .queryParam("postId", postId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        String commentJson = response.readEntity(String.class);
        commentList = jsonToCommentList(commentJson);
        commentLists.put(String.valueOf(postId), commentList);
        return "/comment.xhtml?faces-redirect=true&postId=" + postId;
    }

    public String publishPost() throws IOException {
        File filename = new File(jwtText);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String jwt = br.readLine();

        Response response = targetPost.queryParam("text", post.getText())
                .queryParam("typeVisible", post.getTypeVisible())
                .queryParam("jwt",jwt)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return "/myPostList.xhtml?faces-redirect=true";
    }

    public List<Post> jsonToPostList(String json){
        List<Map<String, Object>> maps = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
        System.out.println(maps);
        List<Post> postList = new ArrayList<Post>();
        for(Map<String, Object> map: maps){
            Post post = new Post();
            post.setId(Float.valueOf(map.get("id").toString()).intValue());
            post.setCreated(map.get("created").toString());
            post.setImagePath(map.get("imagePath").toString());
            post.setText(map.get("text").toString());
            post.setTypeVisible(map.get("typeVisible").toString());
            String jsonUser = map.get("author").toString();
            Map<String, Object> userMap = gson.fromJson(jsonUser, new TypeToken<Map<String, Object>>() {}.getType());
            User user = new User();
            user.setUserId(Float.valueOf(userMap.get("id").toString()).intValue());
            user.setUserName(userMap.get("username").toString());
            user.setPassword(userMap.get("password").toString());
            post.setAuthor(user);
            postList.add(post);
        }
        return postList;
    }

    public List<Comment> jsonToCommentList(String json){
        List<Map<String, Object>> maps = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
        List<Comment> commentList = new ArrayList<Comment>();
        for(Map<String, Object> map: maps){
            Comment comment = new Comment();
            comment.setId(Float.valueOf(map.get("id").toString()).intValue());
            comment.setText(map.get("text").toString());
            comment.setCreated(map.get("created").toString());

            String jsonUser = map.get("author").toString();
            Map<String, Object> userMap = gson.fromJson(jsonUser, new TypeToken<Map<String, Object>>() {}.getType());
            User user = new User();
            user.setUserName(userMap.get("username").toString());
            comment.setAuthor(user);

            commentList.add(comment);
        }
        return commentList;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Map<String, List<Comment>> getCommentLists() {
        return commentLists;
    }

    public void setCommentLists(Map<String, List<Comment>> commentLists) {
        this.commentLists = commentLists;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
