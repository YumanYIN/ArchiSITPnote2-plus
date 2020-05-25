package DAO;

import Bean.Comment;
import Bean.Post;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends HibernateDAO {
    private ProfileDAO profileDAO = new ProfileDAO();

    public List<Comment> getCommentOfPost(int postId){
        String hql = "From Comment as c Where c.post.id = :postId";
        Transaction transaction = getSession().beginTransaction();
        Query query = getSession().createQuery(hql, Comment.class).setParameter("postId", postId);
        return query.getResultList();
    }

    public boolean updatePost(int postId, String text, String imagePath, String typeVisible){
        try {
            String hql = "Update Post p Set text = :text, imagePath = :imagePath, typeVisible = :typeVisible Where id = :postId";
            Transaction transaction = getSession().beginTransaction();
            Query query = getSession().createQuery(hql)
                    .setParameter("text", text)
                    .setParameter("imagePath", imagePath)
                    .setParameter("typeVisible", typeVisible)
                    .setParameter("postId", postId);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Post> getPublicPost(int authorId){
        try{
            String hql = "From Post p Where p.typeVisible = 'public' And p.author.id = :authorId";
            Transaction transaction = getSession().beginTransaction();
            Query query = getSession().createQuery(hql, Post.class).setParameter("authorId", authorId);
            return query.getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
