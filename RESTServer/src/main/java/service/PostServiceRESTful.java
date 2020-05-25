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
    public Post[] getAllPost(){
        List<Post> postList = super.getAllPosts();
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @POST
    @Override
    public boolean publishPost(@QueryParam("text") String text,
                               @QueryParam("imagePath") String imagePath,
                               @QueryParam("typeVisible") String typeVisible,
                               @QueryParam("jwt") String jwt){
        return super.publishPost(text, imagePath, typeVisible, jwt);
    }

    @PUT
    @Path("{postId}")
    @Override
    public boolean updatePost(@PathParam("postId") int postId,
                              @QueryParam("text") String text,
                              @QueryParam("imagePath") String imagePath,
                              @QueryParam("typeVisible") String typeVisible,
                              @QueryParam("jwt") String jwt){
        return super.updatePost(postId, text, imagePath, typeVisible, jwt);
    }

    @GET
    @Path("publicPost")
    @Override
    public List<Post> getPublicPost(@QueryParam("authorId") int authorId){
        return super.getPublicPost(authorId);
    }

    @DELETE
    @Override
    public boolean deletePost(@QueryParam("postId") int postId,
                              @QueryParam("jwt") String jwt){
        return super.deletePost(postId, jwt);
    }

    @GET
    @Path("showComments")
    @Override
    public List<Comment> showComments(@QueryParam("postId") int postId){
        return super.showComments(postId);
    }

    @GET
    @Path("allMyPosts")
    public List<Post> getMyAllPosts(@QueryParam("jwt") String jwt){
        return super.getMyAllPosts(jwt);
    }
}
