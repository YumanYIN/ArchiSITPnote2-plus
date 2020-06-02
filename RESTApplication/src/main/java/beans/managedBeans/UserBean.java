package beans.managedBeans;

import DAO.UserDAO;
import beans.backingBeans.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private User user;

    private WebTarget targetUser;
    private String jwt = null;

    private String jwtText = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/RESTApplication/jwt.txt";

    public UserBean() {
        user = new User();
        String path = "http://localhost:8080/RESTServer_war_exploded";
        Client client = ClientBuilder.newClient();
        targetUser = client.target(path+"/user");
    }

    public String RegisterAccount() {
        if (user.getUserName() != null && user.getEmail() != null && user.getPassword() != null){
            Response response = targetUser.path("register")
                    .queryParam("username", user.getUserName())
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.entity(user.getPassword(), MediaType.APPLICATION_XML));
            System.out.println("here is register");
            if(response.readEntity(String.class).equals("success")) {
                return "login.xhtml";
            }
        }
        return "register.xhtml";
    }

    public String validateUserLogin () throws Exception {

        Response response = targetUser.path("login")
                .queryParam("username", user.getUserName())
                .queryParam("password", user.getPassword())
                .request(MediaType.APPLICATION_XML_TYPE)
                .get();

        jwt = response.readEntity(String.class);
        if(!jwt.equals("error")) {
            System.out.println(jwt);

            File writename = new File(jwtText);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(jwt);
            out.flush();
            out.close();

            return "/index.xhtml?faces-redirect=true";
        }else{
            return "/login.xhtml";
        }

    }

    public boolean isLoggedIn() throws IOException {

        if(jwt == null){
            return false;
        }
        return true;


        /*File filename = new File(jwtText);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while (line != null) {
            line = br.readLine();
        }
        if(line == null){
            return false;
        }else{
            return true;
        }*/
        //return !line.equals("");
    }

    public String logoutUser() throws IOException {
        Response response = targetUser
                .request(MediaType.APPLICATION_XML_TYPE)
                .delete();
        File writename = new File(jwtText);
        writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write("");
        out.flush();
        out.close();
        return "login.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {return jwt;}

    public void setJwt(String jwt){this.jwt = jwt;}

}
