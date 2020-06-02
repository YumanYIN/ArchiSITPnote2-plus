package beans.managedBeans;

import DAO.UserDAO;
import beans.backingBeans.User;
import service.UserServiceSOAP;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.namespace.QName;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

//import util.SessionUtils;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private User user;
    private UserDAO userDAO;
    private URL url = new URL("http://localhost:8080/user?wsdl");
    private QName sname = new QName("http://service/","UserServiceSOAPImplService");
    private javax.xml.ws.Service service = javax.xml.ws.Service.create(url,sname);
    private UserServiceSOAP userServiceSOAP = service.getPort(UserServiceSOAP.class);
    private String jwtText = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/SOAPApplication/jwt.txt";

    public UserBean() throws MalformedURLException {
        user = new User();
    }

    public String RegisterAccount() {
        if (user.getUserName() != null && user.getEmail() == null && user.getPassword() == null){
            if(!userServiceSOAP.register(user.getUserName(), user.getPassword()).equals("error")){
                return "login.xhtml";
            }
        }
        return "register.xhtml";
    }

    public String validateUserLogin () throws IOException {
        String jwt = userServiceSOAP.login(user.getUserName(), user.getPassword());

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
        File filename = new File(jwtText);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while (line != null) {
            line = br.readLine();
        }
        if(line != null){
            return true;
        }else{
            return false;
        }
    }

    public String logoutUser(){



        return "index.jsp";
        /*HttpSession session = SessionUtils.getSession();
        if (session != null){
            session.invalidate();
        }
        return "/index.xhtml?faces-redirect=true";
        */
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
