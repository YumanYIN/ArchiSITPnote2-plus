package service;

import BaseService.CommentService;

import javax.jws.WebService;

@WebService(endpointInterface = "service.CommentServiceSOAP")
public class CommentServiceSOAPImpl implements CommentServiceSOAP {

    CommentService commentService = new CommentService();

    @Override
    public boolean publishComment(int postId, String text, String jwt){
        return commentService.publishComment(postId, text, jwt);
    }

    @Override
    public boolean deleteComment(int commentId, String jwt){
        return commentService.deleteComment(commentId, jwt);
    }
}
