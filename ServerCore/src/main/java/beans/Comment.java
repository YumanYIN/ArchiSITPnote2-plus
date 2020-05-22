package beans;

import javafx.geometry.Pos;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @Setter
    private String text;

    @Basic
    private LocalDateTime commentedTime;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Profile author;

    public Comment(){}

    public Comment(String text, LocalDateTime commentedTime, Post post, Profile author){
        this.text = text;
        this.commentedTime = commentedTime;
        this.post = post;
        this.author = author;
    }


}
