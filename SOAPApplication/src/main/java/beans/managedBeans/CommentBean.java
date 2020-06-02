package beans.managedBeans;

import DAO.CommentDAO;
import beans.backingBeans.Comment;
import beans.backingBeans.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class CommentBean {
    private Comment comment;
    private List<Comment> listComment = new ArrayList<Comment>();

    public CommentBean(){
        comment = new Comment();
    }

    public void CommentListBean(){
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


}
