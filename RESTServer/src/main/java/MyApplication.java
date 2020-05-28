import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import service.CommentServiceRESTful;
import service.PostServiceRESTful;
import service.UserServiceRESTful;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class MyApplication extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( UserServiceRESTful.class );
        h.add( PostServiceRESTful.class );
        h.add( CommentServiceRESTful.class );
        h.add(MultiPartFeature.class);
        h.add(LoggingFilter.class);
        return h;
    }
}