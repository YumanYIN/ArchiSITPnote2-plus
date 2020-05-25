package service;

import BaseService.ProfileService;
import Bean.Profile;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  ProfileServiceRESTful extends ProfileService {

    @POST
    @Path("login")
    @Override
    public String login(@QueryParam("username") String username, @QueryParam("password") String password){
        return super.login(username, password);
    }

    @GET
    @Path("register")
    @Override
    public boolean register(@QueryParam("username") String username, @QueryParam("password") String password){
        return super.register(username, password);
    }

    @DELETE
    @Override
    public String logout(@QueryParam("jwt") String jwt){
        return super.logout(jwt);
    }
}
