
package services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://services/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param arg0
     * @return
     *     returns services.User
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/loginRequest", output = "http://services/UserService/loginResponse")
    public User login(
        @WebParam(name = "arg0", partName = "arg0")
        User arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/deleteUserRequest", output = "http://services/UserService/deleteUserResponse")
    public boolean deleteUser(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/logoutRequest", output = "http://services/UserService/logoutResponse")
    public boolean logout(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns services.User
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/getUserRequest", output = "http://services/UserService/getUserResponse")
    public User getUser(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns services.UserArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/getAllUsersRequest", output = "http://services/UserService/getAllUsersResponse")
    public UserArray getAllUsers(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/addUserRequest", output = "http://services/UserService/addUserResponse")
    public boolean addUser(
        @WebParam(name = "arg0", partName = "arg0")
        User arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/updateUserEmailRequest", output = "http://services/UserService/updateUserEmailResponse")
    public boolean updateUserEmail(
        @WebParam(name = "arg0", partName = "arg0")
        User arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/UserService/updateUserRequest", output = "http://services/UserService/updateUserResponse")
    public boolean updateUser(
        @WebParam(name = "arg0", partName = "arg0")
        User arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}
