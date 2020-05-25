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
    public Profile login(@QueryParam("username") String username, @QueryParam("password") String password){
        Profile profile = super.login(username, password);
        return profile;
    }

    @GET
    @Path("register")
    @Override
    public boolean register(@QueryParam("username") String username, @QueryParam("password") String password){
        return super.register(username, password);
    }
}
