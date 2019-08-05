package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.User;
import token.TokenGenerator;

public class DAO {

	private static final String DRIVER = "jdbc:mysql";
	private static final String HOST = "localhost";
	private static final String DBNAME =  "mglsi_news";
	private static final String PORT = "3306";
	private static final String USERNAME = "mglsi_user";
	private static final String PASSWORD = "passer";
	
	private Connection con=null;
	private PreparedStatement st=null;
	private ResultSet rs=null; 
	
	public DAO() throws SQLException {
		con = DriverManager.getConnection(DRIVER+"://"+HOST+":"+PORT+"/"+DBNAME, USERNAME, PASSWORD);
	}
	
	public User login(User u) throws SQLException, Exception {
		st = con.prepareStatement("select id, username, email, role from user where email = ? and password = password(?)");
		st.setString(1, u.getEmail());
		st.setString(2, u.getPassword());
		rs = st.executeQuery();
		User res = new User();
		if(rs.next()) {
			int id  = rs.getInt("id");
			String email = rs.getString("email");
			String name = rs.getString("username");
			String role = rs.getString("role");
			String token = TokenGenerator.encrypt(String.valueOf(id));
			res = new User(id, name, email, null, role, token);
		}
		rs.close();
		return res;
	}
	
	public boolean logout(String t) throws SQLException {
		st = con.prepareStatement("delete from token where value = ?");
		st.setString(1, t);
		int res = st.executeUpdate();		
		return res >0;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		st = con.prepareStatement("delete from user where id = ?");
		st.setInt(1, id);
		int res = st.executeUpdate();		
		return res >0;
	}
	
	public boolean addUser(User u)  throws SQLException {
		st = con.prepareStatement("insert into user(email, username, password, role) values(?,?,password(?),?)");
		st.setString(1, u.getEmail());
		st.setString(2, u.getUsername());
		st.setString(3, u.getPassword());
		st.setString(4, u.getRole());
		int res = st.executeUpdate();		
		return res >0;
	}
	
	public boolean updateUser(User u)  throws SQLException {
		st = con.prepareStatement("update user set username=?, role=?, email=? where id=?");
		st.setString(1, u.getUsername());
		st.setString(2, u.getRole());
		st.setString(3, u.getEmail());
		st.setInt(4, u.getId());
		int res = st.executeUpdate();
		return res >0;
	}
	
	public User getUser(String token) throws NumberFormatException, SQLException, Exception {
		return getUser(Integer.valueOf(TokenGenerator.decrypt(token)));
	}
	
	public boolean updateEmail(User u)  throws SQLException {
		st = con.prepareStatement("update user set email=? where id=?)");
		st.setString(1, u.getEmail());;
		st.setInt(2, u.getId());
		int res = st.executeUpdate();
		return res >0;
	}
	
	public boolean updatePassword(User u)  throws SQLException {
		st = con.prepareStatement("update user set password=password(?) where id=?)");
		st.setString(1, u.getPassword());;
		st.setInt(2, u.getId());
		int res = st.executeUpdate();
		return res >0;
	}
	
	public User getUser(int id) throws SQLException {
		st = con.prepareStatement("select id, username, email, role from user where id = ?");
		st.setInt(1, id);
		rs = st.executeQuery();
		User res = new User();
		if(rs.next()) {
			String email = rs.getString("email");
			String role = rs.getString("role");
			String name = rs.getString("username");
			res = new User(id, name, email, null, role, null);
		}
		rs.close();
		return res;
	}
	
	public User[] getUsers() throws SQLException {
		st = con.prepareStatement("select id, email, username, role from user");
		rs = st.executeQuery();
		ArrayList<User> res = new ArrayList<User>();
		while(rs.next()) {
			int id  = rs.getInt("id");
			String email = rs.getString("email");
			String role = rs.getString("role");
			String name = rs.getString("username");
			res.add(new User(id, name, email, null, role, null));
		}
		User[] users = new User[res.size()];
		rs.close();
		return res.toArray(users);
	}
	
}
