package beans.managedBeans;

import DAO.CommentDAO;
import beans.backingBeans.Comment;
import beans.backingBeans.Post;
import beans.backingBeans.User;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class CommentBean implements Serializable {
    private Comment comment;
    private List<Comment> listComment = new ArrayList<Comment>();
    private WebTarget targetComment;
    private Post post;

    public CommentBean(){
        comment = new Comment();
        String path = "http://localhost:8080/RESTServer_war_exploded";
        Client client = ClientBuilder.newClient();
        targetComment = client.target(path+"/comment");
    }

    public void CommentListBean(int postId){

        this.listComment = new CommentDAO().getListOfAll();
    }

    public List<Comment> getCommentByUser(User user){
        listComment = new CommentDAO().getListOfAll();
        List<Comment> list = new ArrayList<>();
        for(Comment comment: listComment){
            if (comment.getAuthor() == user){
                list.add(comment);
            }
        }
        return list;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
