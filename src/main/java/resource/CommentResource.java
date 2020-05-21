package resource;

import model.Comment;
import model.Post;
import model.Profile;
import service.CommentService;
import service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Path("/comments")
@Produces({"application/json", "text/plain"})
public class CommentResource {
    private final CommentService commentService = new CommentService();
    private final PostService postService = new PostService();

    @GET
    public List<Comment> getAllComments(@Context HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null) {
            return commentService.getAllComments();
        } else {
            return new ArrayList<Comment>();
            //logger.warn("No session found");
        }
    }

    @POST
    @Path("/addcomment/{postid}")
    public boolean publishComment(@Context HttpServletRequest req,
                                  @PathParam("postid") int postId,
                                  String text){
        HttpSession session = req.getSession(false);
        if(session != null) {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = Calendar.getInstance().getTime();
            String todayAsString = df.format(today);

            Comment comment = new Comment(text, postService.getPost(postId), (Profile) session.getAttribute("user"));

            commentService.addComment(comment);
            return true;
        }else{
            return false;
        }
    }


}
