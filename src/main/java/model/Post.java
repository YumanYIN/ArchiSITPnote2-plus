package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class Post implements Serializable {
    private int id;
    private String imagePath;
    private String created;
    private String text;
    private String typeVisible; // public or private
    private Profile author;

    public Post(String imagePath, String text, String typeVisible, Profile author){
        //this.id = id;
        this.imagePath = imagePath;
        this.text = text;
        this.typeVisible = typeVisible;
        this.author = author;
        this.created = new Date().toString().substring(0,10); // ex:2020-05-19 15:17:20
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTypeVisible() {
        return typeVisible;
    }

    public void setTypeVisible(String typeVisible) {
        this.typeVisible = typeVisible;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }
}
