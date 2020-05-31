package service;

import BaseService.UserService;
import Bean.User;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;


@Path("user")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class  UserServiceRESTful extends UserService {

    @GET
    @Path("login")
    public Response loginREST(@QueryParam("username") String username, @QueryParam("password") String password){
        try{
            return Response.ok().entity(super.login(username, password)).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        //return super.login(username, password);
    }

    @POST
    @Path("register")
    public Response registerREST(@QueryParam("username") String username, String password){
        try{
            return Response.ok().entity(super.register(username, password)).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
        //return super.register(username, password);
    }

    @DELETE
    public Response logoutREST(@QueryParam("jwt") String jwt){
        try {
            return Response.ok().entity(super.logout(jwt)).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
