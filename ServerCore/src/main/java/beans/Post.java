package beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //persistence
@Getter //lombok
@NoArgsConstructor // lombok
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @ManyToOne
    private Profile author;

    @Basic
    private LocalDateTime postedTime;

    @Setter
    private String text;

    @Setter
    private String imagePath;

    public Post(){}

    public Post(Profile author, LocalDateTime postedTime, String text, String imagePath) {
        this.author = author;
        this.postedTime = postedTime;
        this.text = text;
        this.imagePath = imagePath;
    }
}
