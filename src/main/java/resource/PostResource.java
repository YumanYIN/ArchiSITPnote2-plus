package resource;

import model.Comment;
import model.Post;
import model.Profile;
import service.CommentService;
import service.PostService;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/posts")
@Produces({"application/json", "text/plain"})
public class PostResource {
    private final PostService postService = new PostService();
    private final CommentService commentService = new CommentService();

    @GET
    public List<Post> getAllPosts() {
       return postService.getAllPosts();
    }

}
