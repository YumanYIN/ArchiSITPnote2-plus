package beans;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;

    private String username;

    private String password;

    public Profile(){}

    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
