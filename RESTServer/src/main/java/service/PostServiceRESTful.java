package service;

import BaseService.PostService;
import Bean.Comment;
import Bean.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostServiceRESTful extends PostService{

    @GET
    @Path("allPosts")
    @Override
    public List<Post> getAllPosts(int profileId){
        return super.getAllPosts(profileId);
    }

    @POST
    @Override
    public boolean publishPost(String text, String imagePath, String typeVisible, int profileId){
        return super.publishPost(text, imagePath, typeVisible, profileId);
    }

    @PUT
    @Path("{postId:[0-9]*}")
    @Override
    public boolean updatePost(@PathParam("postId") int postId,
                              String text,
                              String imagePath,
                              String typeVisible){
        return super.updatePost(postId, text, imagePath, typeVisible);
    }

    @GET
    @Path("publicPost")
    @Override
    public List<Post> getPublicPost(int authorId){
        return super.getPublicPost(authorId);
    }

    @DELETE
    @Override
    public boolean deletePost(int postId){
        return super.deletePost(postId);
    }

    @GET
    @Path("showComments")
    @Override
    public List<Comment> showComments(int postId){
        return super.showComments(postId);
    }
}
