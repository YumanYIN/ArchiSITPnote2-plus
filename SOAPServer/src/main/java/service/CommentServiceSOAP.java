package service;


import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CommentServiceSOAP {

    @WebResult(name = "publishCommentResponse")
    @WebMethod(operationName = "publishComment")
    public boolean publishComment(int postId, String text, String jwt);

    @WebMethod(operationName = "deleteComment")
    @WebResult(name = "deleteCommentResponse")
    public boolean deleteComment(int commentId, String jwt);
}
