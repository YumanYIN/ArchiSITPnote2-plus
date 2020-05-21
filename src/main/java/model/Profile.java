package model;

//import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.PreparedStatement;

@XmlRootElement
public class Profile implements Serializable {
    private int id;
    private String username;
    private String password;
    private String avatar; // image path

    public Profile(String username, String password, String avatar){
        //this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    @XmlAttribute(name = "userId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlAttribute(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlAttribute(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
