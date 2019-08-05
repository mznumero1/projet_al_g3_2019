package services;

import java.sql.SQLException;

import javax.jws.WebService;

import bean.User;
import data.DAO;

@WebService(endpointInterface="services.UserService")
public class UserServiceImpl implements UserService {

	private DAO dao;
	private static final String ADMIN_ROLE = "admin";
	
	public UserServiceImpl() throws SQLException {
		dao = new DAO();
	}
	
	@Override
	public User[] getAllUsers(String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE)){
				return dao.getUsers();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new User[0];
	}

	@Override
	public User getUser(int id,String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE))
				return dao.getUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new User();
	}

	@Override
	public boolean addUser(User u,String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE))
				return dao.addUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id,String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE))
				return dao.deleteUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User u,String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE))
				return dao.updateUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User login(User u) {
		try {
			return dao.login(u);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean logout(String t) {
		try {
			return dao.logout(t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserEmail(User u,String token) {
		try {
			if(dao.getUser(token).getRole().equalsIgnoreCase(ADMIN_ROLE))
				return dao.updateEmail(u);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

}
