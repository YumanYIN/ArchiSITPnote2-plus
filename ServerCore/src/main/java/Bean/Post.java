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
public class Post {
    private int id;

    @Setter
    private String imagePath;

    private String created;

    @Setter
    private String text;

    @Setter
    private String typeVisible; // public or private

    private User author;

    public Post(String imagePath, String text, String typeVisible, User author) {
        this.text = text;
        this.typeVisible = typeVisible;
        this.author = author;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        this.created = df.format(today);
        this.imagePath = this.created + '#' + imagePath;
    }
}
