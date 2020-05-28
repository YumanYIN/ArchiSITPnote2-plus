package service;

import BaseService.UserService;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserServiceSOAP {

    @WebMethod(operationName = "text")
    @WebResult(name = "testResponse")
    public String test();

    @WebMethod(operationName = "login")
    @WebResult(name = "loginResponse")
    public String login(String username, String password);

    @WebMethod(operationName = "register")
    @WebResult(name = "registerResponse")
    public boolean register(String username, String password);

    @WebMethod(operationName = "logout")
    @WebResult(name = "logoutResponse")
    public String logout(String jwt);


}
