import service.CommentServiceSOAP;
import service.PostServiceSOAP;
import service.ProfileServiceSOAP;

import javax.xml.ws.Endpoint;

public class MyApplication {
    public static void main(String[] args) {
        String addressProfile = "http://localhost:8080/profile";
        Endpoint.publish(addressProfile, new ProfileServiceSOAP());

        String addressPost = "http://localhost:8080/post";
        Endpoint.publish(addressPost, new PostServiceSOAP());

        String addressComment = "http://localhost:8080/comment";
        Endpoint.publish(addressComment, new CommentServiceSOAP());
    }
}
