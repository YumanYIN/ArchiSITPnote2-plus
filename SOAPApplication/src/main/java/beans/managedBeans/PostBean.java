package beans.managedBeans;

import DAO.PostDAO;
import beans.backingBeans.Post;
import beans.backingBeans.User;
import service.PostServiceSOAP;
import service.UserServiceSOAP;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import util.SessionUtils;

@ManagedBean
@RequestScoped
public class PostBean {
    private Post post;
    private List<Post> postList = new ArrayList<>();
    private List<Post> myPostList = new ArrayList<>();
    private HttpSession session;

    private String imagePath = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/SOAPApplication/web/images/post/";

    private URL url = new URL("http://localhost:8080/post?wsdl");
    private QName sname = new QName("http://service/","PostServiceSOAPImplService");
    private javax.xml.ws.Service service = javax.xml.ws.Service.create(url,sname);
    private PostServiceSOAP postServiceSOAP = service.getPort(PostServiceSOAP.class);
    private String jwtText = "/Users/wangwei/IdeaProjects/ArchiTPNote2-plus/ArchiSITPnote2-plus/SOAPApplication/jwt.txt";

    public PostBean() throws MalformedURLException {
        post = new Post();
    }

    /**
     * this method return all of public posts.
     * @return
     */
    public List<Post> getPostList() throws IOException {
        Bean.Post[] postsFromService = postServiceSOAP.getPublicPosts();
        for(Bean.Post beanPost: postsFromService){
            postList.add(beanPostToBackPost(beanPost));
            savePic(beanPost.getImagePath());
        }
        return postList;
    }

    public List<Post> getMyPostList() throws IOException {
        File filename = new File(jwtText);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        Bean.Post[] postsFromService = postServiceSOAP.getMyAllPost(line);
        for(Bean.Post beanPost: postsFromService){
            myPostList.add(beanPostToBackPost(beanPost));
            savePic(beanPost.getImagePath());
        }
        return myPostList;

        //session = SessionUtils.getSession();
        //User user = (User) session.getAttribute("user");
        //return new PostDAO().getPostListByUser(user);
    }

    public String toMyPostList(){
        return "/myPostList.xhtml?faces-redirect=true";
    }

    public String toPostPage() { return "/post.xhtml?faces-redirect=true"; }

    private Post beanPostToBackPost(Bean.Post beanPost){
        User user = new User(beanPost.getAuthor().getId(), beanPost.getAuthor().getUsername(), beanPost.getAuthor().getPassword(), "");
        Post post = new Post(beanPost.getId(),beanPost.getImagePath(), beanPost.getText(), beanPost.getTypeVisible(), user);
        return post;
    }

    private void savePic(String picName) throws IOException {
        byte[] bytes = postServiceSOAP.getPic(picName);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            System.out.println("here is try");
            File w2 = new File(imagePath + picName);//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }
}
