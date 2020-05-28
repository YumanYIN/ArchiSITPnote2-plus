package Bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private int id;


    private String text;

    private String created;

    private Post post;

    private User author;

    public Comment(String text, Post post, User author) {
        this.text = text;
        this.post = post;
        this.author = author;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        this.created = df.format(today);
    }
}
