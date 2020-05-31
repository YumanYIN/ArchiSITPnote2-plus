package service;

import BaseService.PostService;
import Bean.Comment;
import Bean.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("post")
public class PostServiceRESTful extends PostService{
    @GET
    @Path("allPublicPosts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post[] getAllPublicPost(){
        List<Post> postList = super.getAllPublicPosts();
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @GET
    @Path("allMyPosts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post[] getAllMyPosts(@QueryParam("jwt") String jwt){
        List<Post> postList = super.getMyAllPosts(jwt);
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @GET
    @Path("allPosts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post[] getAllPost(){
        List<Post> postList = super.getAllPosts();
        Post[] posts = new Post[postList.size()];
        postList.toArray(posts);
        return posts;
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishPost(@QueryParam("text") String text,
                                @QueryParam("typeVisible") String typeVisible,
                                @QueryParam("jwt") String jwt,
                                @QueryParam("imageName") String imageName,
                                InputStream uploadedInputStream) throws Exception {
        return super.publishPost(text, typeVisible, jwt, uploadedInputStream, imageName);
    }

    @GET
    @Path("/download/{imageName}")
    @Produces("image/png")
    public Response getImage(@PathParam("imageName") String imageName) {
        String filename = super.imagePath + imageName;
        File file = new File(filename);
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=" + imageName);
        return response.build();
    }

    @PUT
    @Path("{postId}")
    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public boolean updatePost(@PathParam("postId") int postId,
                              @FormDataParam("text") String text,
                              @FormDataParam("typeVisible") String typeVisible,
                              @FormDataParam("jwt") String jwt){
        return super.updatePost(postId, text, typeVisible, jwt);
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
                              @FormDataParam("jwt") String jwt){
        return super.deletePost(postId, jwt);
    }

    @GET
    @Path("showComments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment[] getComments(@QueryParam("postId") int postId){
        List<Comment> commentList = super.showComments(postId);
        Comment[] comments = new Comment[commentList.size()];
        commentList.toArray(comments);
        return comments;
    }
}
