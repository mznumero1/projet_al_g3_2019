package services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import bean.User;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {
	
	@WebMethod
	public User[] getAllUsers(String token);
	
	@WebMethod
	public User getUser(int id, String token);
	
	@WebMethod
	public boolean addUser(User u, String token);
	
	@WebMethod
	public boolean deleteUser(int id,String token);
	
	@WebMethod
	public boolean updateUser(User u,String token);
	
	@WebMethod
	public boolean updateUserEmail(User u,String token);
	
	@WebMethod
	public User login(User u);
	
	@WebMethod
	public boolean logout(String token);
}
