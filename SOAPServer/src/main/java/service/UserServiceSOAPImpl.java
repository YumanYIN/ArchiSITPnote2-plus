package service;

import BaseService.UserService;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "service.UserServiceSOAP")
public class UserServiceSOAPImpl implements UserServiceSOAP {

    private UserService userService = new UserService();

    @WebMethod(operationName = "text")
    @WebResult(name = "testResponse")
    public String test(){
        return "success";
    }

    @WebMethod(operationName = "login")
    @WebResult(name = "loginResponse")
    public String login(String username, String password){
        return userService.login(username, password);
    }

    @WebMethod(operationName = "register")
    @WebResult(name = "registerResponse")
    public boolean register(String username, String password){
        return userService.register(username, password);
    }

    @WebMethod(operationName = "logout")
    @WebResult(name = "logoutResponse")
    public String logout(String jwt){
        return userService.logout(jwt);
    }
}
