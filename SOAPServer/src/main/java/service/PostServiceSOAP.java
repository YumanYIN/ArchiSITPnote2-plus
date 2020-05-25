package service;

import BaseService.PostService;
import Bean.Comment;
import Bean.Post;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PostServiceSOAP extends PostService {

    @WebMethod(operationName = "text")
    @WebResult(name = "testResponse")
    public String test(){
        return "success";
    }

    @WebMethod(operationName = "getAllPosts")
    @WebResult(name = "getAllPostsResponse")
    public Post[] getAllPosts(@WebParam(name = "profileId") int profileId,
                                  @WebParam(name = "profileId") int profileIdRepeat){
        List<Post> postList = super.getAllPosts(profileId);
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @WebMethod(operationName = "publishPost")
    @WebResult(name = "publishPostResponse")
    @Override
    public boolean publishPost(@WebParam(name = "text") String text,
                               @WebParam(name = "imagePath") String imagePath,
                               @WebParam(name = "typeVisible") String typeVisible,
                               @WebParam(name = "profileId") int profileId){
        return super.publishPost(text, imagePath, typeVisible, profileId);
    }

    @WebMethod(operationName = "deletePost")
    @WebResult(name = "deletePostResponse")
    @Override
    public boolean deletePost(@WebParam(name = "id") int id){
        return super.deletePost(id);
    }

    @WebMethod(operationName = "showComments")
    @WebResult(name = "showCommentsResponse")
    public Comment[] showComments(@WebParam(name = "id") int id,
                                      @WebParam(name = "id") int idRepeat){
        List<Comment> commentList = super.showComments(id);
        Comment[] comments = new Comment[commentList.size()];
        commentList.toArray(comments);
        return comments;
    }

    @WebMethod(operationName = "updatePost")
    @WebResult(name = "updatePostResponse")
    @Override
    public boolean updatePost(@WebParam(name = "postid") int postId,
                              @WebParam(name = "text") String text,
                              @WebParam(name = "imagepath") String imagePath,
                              @WebParam(name = "typevisible") String typeVisible){
        return super.updatePost(postId, text, imagePath, typeVisible);
    }
}
