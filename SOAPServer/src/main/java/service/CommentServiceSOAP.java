package service;

import BaseService.CommentService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CommentServiceSOAP extends CommentService {

    @WebResult(name = "publishCommentResponse")
    @WebMethod(operationName = "publishComment")
    @Override
    public boolean publishComment(@WebParam(name = "postId") int postId,
                                  @WebParam(name = "text") String text,
                                  @WebParam(name = "jwt") String jwt){
        return super.publishComment(postId, text, jwt);
    }

    @WebMethod(operationName = "deleteComment")
    @WebResult(name = "deleteCommentResponse")
    @Override
    public boolean deleteComment(@WebParam(name = "commentId") int commentId,
                                 @WebParam(name = "jwt") String jwt){
        return super.deleteComment(commentId, jwt);
    }
}
