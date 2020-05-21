package resource;

import model.Post;
import model.Profile;
import service.ProfileService;

import javax.jws.WebParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/profiles")
@Produces({"application/json", "text/plain"})
public class ProfileResource {
	
	private final ProfileService ps = new ProfileService();

	@POST
	@Path("/login")
	public String login( @Context HttpServletRequest request,
						 @FormParam("username") String username,
						 @FormParam("password") String password){
		if(ps.isLogin(username, password)){
			HttpSession session = request.getSession();   // getSession()找不到session时就会在服务器创建一个session，如果创建新session则会自动把session id放入响应报文头的Set-Cookie字段中
			session.setAttribute("user", ps.getProfile(username));
			session.setMaxInactiveInterval(30 * 60); // 30 minutes
			String sessionId = session.getId();
			//Profile profile = (Profile)session.getAttribute("user");
			return "username: " + username + "; sessionID: " + sessionId;
		}else{
			return "failure of login";
		}
	}

	@GET
	public List<Profile> getAllProfiles(){

			return ps.getAllProfiles();

	}


	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo){
		String path = uriInfo.getAbsolutePath().toString();
		return "Path : " + path;
	}


	@GET
	@Path("/session")
	public String getSessionContext(@Context HttpServletRequest req){
		HttpSession session = req.getSession(false);
		if(session != null)
			return "Session : " + session.toString();
		else
			return "session is vide";
	}

}
