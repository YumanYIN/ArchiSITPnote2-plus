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
    public boolean publishComment(@QueryParam("postId") int postId,
                                  @QueryParam("text") String text,
                                  @QueryParam("jwt") String jwt){
        return super.publishComment(postId, text, jwt);
    }

    @DELETE
    @Override
    public boolean deleteComment(@QueryParam("commentId") int commentId,
                                 @QueryParam("jwt") String jwt){
        return super.deleteComment(commentId,jwt);
    }
}
