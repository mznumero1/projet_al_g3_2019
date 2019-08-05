package bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -848149903907662246L;
	
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private String token;

	
	public User() {
		
	}
	
	public User(int id, String username, String email, String password, String role, String token) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public String getToken() {
		return token;
	}
	
	
	public void setToken(String token) {
		this.token = token;
	}	
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
