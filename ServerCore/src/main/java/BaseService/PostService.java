package BaseService;

import Bean.Comment;
import Bean.Post;
import Bean.User;
import DAO.PostDAO;
import DAO.UserDAO;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import util.JwtUtils;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PostService {
    public PostDAO postDAO = new PostDAO();
    public UserDAO userDAO = new UserDAO();
    protected static final String imagePath = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/images/";

    public List<Post> getAllPosts(){
        return postDAO.findAll();
    }

    public List<Post> getMyAllPosts(String jwt){
        try{
            String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
            return postDAO.getAllPostsOfUser(userDAO.getUserByUsername(username).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response publishPost(String text, String typeVisible, String jwt, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) throws Exception {
        String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
        String imageName = fileDetail.getFileName();
        String files[] = imageName.split(":");
        imageName = files[files.length-1];
        System.out.println("filename => "+ imageName);
        String fileLocation = imagePath + imageName;
        File filePath = new File(imagePath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        System.out.println("filename => "+ fileLocation);
        //saving file
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output = "File successfully uploaded to : " + fileLocation;
        postDAO.addPost(new Post(imageName, text, typeVisible, userDAO.getUserByUsername(username)));
        return Response.status(200).entity(output).build();
    }

    public boolean deletePost(int postId, String jwt){
        try {
            if(!JwtUtils.getInstance().getUserNameFromJwtToken(jwt).isEmpty()) {
                return postDAO.delPost(postId);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comment> showComments(int postId){
        return postDAO.getCommentOfPost(postId);
    }

    public boolean updatePost(int postId, String text, String typeVisible, String jwt){
        try {
            if(!JwtUtils.getInstance().getUserNameFromJwtToken(jwt).isEmpty()) {
                return postDAO.updatePost(postId, text, typeVisible);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> getPublicPost(int authorId){
        return postDAO.getPublicPost(authorId);
    }
}
