package services;

import java.sql.SQLException;

import javax.xml.ws.Endpoint;

public class Server {
	
	private static final String URL = "http://localhost:8080/ws/soap";

	public static void main(String[] args) {
		try {
			Endpoint.publish(URL, new UserServiceImpl());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
