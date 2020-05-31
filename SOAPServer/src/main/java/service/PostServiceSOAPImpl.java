package service;

import BaseService.PostService;
import Bean.Comment;
import Bean.Post;
import util.JwtUtils;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@WebService(endpointInterface = "service.PostServiceSOAP")
public class PostServiceSOAPImpl implements PostServiceSOAP {

    private static String imagePath = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/images/";
    private PostService postService = new PostService();

    @Override
    public String test(){
        return "success";
    }

    @Override
    public Post[] getAllPost(){
        List<Post> postList = postService.getAllPosts();
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @Override
    public boolean publishPost(String text,String typeVisible,String jwt,byte[] bytes,String fileName) throws IOException {
        System.out.println("here is upload file service impl");
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            System.out.println("here is try");
            File w2 = new File(imagePath + fileName);//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动

            String username = JwtUtils.getInstance().getUserNameFromJwtToken(jwt);
            return postService.postDAO.addPost(new Post(fileName, text, typeVisible, postService.userDAO.getUserByUsername(username)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            bais.close();
        }
        return false;
    }

    @Override
    public boolean deletePost(int id,String jwt){
        return postService.deletePost(id, jwt);
    }

    @Override
    public Comment[] showComments(int id,int idRepeat){
        List<Comment> commentList = postService.showComments(id);
        Comment[] comments = new Comment[commentList.size()];
        commentList.toArray(comments);
        return comments;
    }

    @Override
    public boolean updatePost(int postId,String text,String typeVisible,String jwt){
        return postService.updatePost(postId, text, typeVisible, jwt);
    }

    @Override
    public Post[] getMyAllPost(String jwt){
        List<Post> postList = postService.getMyAllPosts(jwt);
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @Override
    public Post[] getPublicPosts(){
        List<Post> postList = postService.getAllPublicPosts();
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @Override
    public byte[] getPic(String picName) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(imagePath + picName));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        return content;
    }
}
