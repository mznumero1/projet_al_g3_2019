package program;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import services.User;
import services.UserService;
import services.UserServiceImplService;

public class Test {

	public static void main(String[] args) {
			UserServiceImplService s = new UserServiceImplService();
			UserService service = s.getUserServiceImplPort();
			User u1 = new User();
			u1.setUsername("MZ");
			u1.setRole("admin");
			u1.setPassword("passer");
			u1.setEmail("mzerbo@mzerbo.xyz");
			User u2 = service.login(u1);
			System.out.println(u2.getToken());
			List<User> u = service.getAllUsers("").getItem();
			System.out.println(u.size());
			for(User e:u)
				System.out.println(e.getUsername());
			

	}

}
