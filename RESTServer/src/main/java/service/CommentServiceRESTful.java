package service;

import BaseService.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("comment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentServiceRESTful extends CommentService {

    @POST
    @Override
    public boolean publishComment(int postId, int authorId, String text){
        return super.publishComment(postId, authorId, text);
    }

    @DELETE
    @Override
    public boolean deleteComment(int commentId){
        return super.deleteComment(commentId);
    }
}
