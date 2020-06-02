package beans.backingBeans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Getter @Setter
    private int userId;
    @Getter @Setter
    private String userName;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String email;

    public User(int userId, String userName, String password, String email){
        this.setUserId(userId);
        this.setPassword(password);
        this.setUserName(userName);
        this.setEmail(email);
    }
}
