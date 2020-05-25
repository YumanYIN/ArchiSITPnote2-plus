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
                                  @WebParam(name = "profileId") int profileId,
                                  @WebParam(name = "text") String text){
        return super.publishComment(postId, profileId, text);
    }

    @WebMethod(operationName = "deleteComment")
    @WebResult(name = "deleteCommentResponse")
    @Override
    public boolean deleteComment(@WebParam(name = "commentId") int commentId){
        return super.deleteComment(commentId);
    }
}
