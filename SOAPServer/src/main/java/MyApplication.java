import service.*;

import javax.xml.ws.Endpoint;

public class MyApplication {
    public static void main(String[] args) {
        String addressUser = "http://localhost:8080/user";
        Endpoint.publish(addressUser, new UserServiceSOAPImpl());

        String addressPost = "http://localhost:8080/post";
        Endpoint.publish(addressPost, new PostServiceSOAPImpl());

        String addressComment = "http://localhost:8080/comment";
        Endpoint.publish(addressComment, new CommentServiceSOAPImpl());

        System.out.println("Service is running");
    }
}
