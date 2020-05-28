package service;

import BaseService.UserService;
import Bean.User;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("user")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class  UserServiceRESTful extends UserService {

    @POST
    @Path("login")
    @Override
    public String login(@FormDataParam("username") String username, @FormDataParam("password") String password){
        return super.login(username, password);
    }

    @POST
    @Path("register")
    @Override
    public boolean register(@FormParam("username") String username, @FormParam("password") String password){
        return super.register(username, password);
    }

    @DELETE
    @Override
    public String logout(@QueryParam("jwt") String jwt){
        return super.logout(jwt);
    }
}
