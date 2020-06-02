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
public class Post {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String imagePath;
    @Getter @Setter
    private String created;
    @Getter @Setter
    private String text;
    @Getter @Setter
    private String typeVisible; // public or private
    @Getter @Setter
    private User author;

    public Post(int id, String imagePath, String text, String typeVisible, User author) {
        this.imagePath = imagePath;
        this.text = text;
        this.typeVisible = typeVisible;
        this.author = author;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        this.created = df.format(today);
    }
}
