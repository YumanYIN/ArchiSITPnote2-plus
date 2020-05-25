package service;

import BaseService.ProfileService;
import Bean.Profile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ProfileServiceSOAP extends ProfileService {

    @WebMethod(operationName = "text")
    @WebResult(name = "testResponse")
    public String test(){
        return "success";
    }

    @WebMethod(operationName = "login")
    @WebResult(name = "loginResponse")
    @Override
    public Profile login(@WebParam(name = "username") String username,
                         @WebParam(name = "password") String password){
        return super.login(username, password);
    }

    @WebMethod(operationName = "register")
    @WebResult(name = "registerResponse")
    @Override
    public boolean register(@WebParam(name = "username") String username,
                            @WebParam(name = "passeord") String password){
        return super.register(username, password);
    }
}
