package service;

import Bean.Comment;
import Bean.Post;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PostServiceSOAP {

    @WebMethod(operationName = "text")
    @WebResult(name = "testResponse")
    public String test();

    @WebMethod(operationName = "getAllPosts")
    @WebResult(name = "getAllPostsResponse")
    public Post[] getAllPost();

    @WebMethod(operationName = "publishPost")
    @WebResult(name = "publishPostResponse")
    public boolean publishPost(String text,
                            String typeVisible,
                            String jwt,
                            byte[] bytes,
                            String fileName) throws IOException;

    @WebMethod(operationName = "deletePost")
    @WebResult(name = "deletePostResponse")
    public boolean deletePost(int id, String jwt);

    @WebMethod(operationName = "showComments")
    @WebResult(name = "showCommentsResponse")
    public Comment[] showComments(int id, int idRepeat);

    @WebMethod(operationName = "updatePost")
    @WebResult(name = "updatePostResponse")
    public boolean updatePost(int postId, String text, String typeVisible, String jwt);

    @WebMethod(operationName = "allMyPosts")
    @WebResult(name = "allMyPostsResponse")
    public Post[] getMyAllPost(String jwt);
}
