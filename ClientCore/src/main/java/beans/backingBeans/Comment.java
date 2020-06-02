package beans.backingBeans;

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
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String text;
    @Getter @Setter
    private String created;
    @Getter @Setter
    private Post post;
    @Getter @Setter
    private User author;

    public Comment(int id, String text, Post post, User author) {
        this.id = id;
        this.text = text;
        this.post = post;
        this.author = author;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        this.created = df.format(today);
    }
}
