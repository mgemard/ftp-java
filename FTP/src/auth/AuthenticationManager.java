package auth;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {

	private Map<String, User> users = new HashMap<>();
	
	private static AuthenticationManager instance = null;
	
	private AuthenticationManager() {
		// On empêche l'appel au constructeur
	}
	
	public static AuthenticationManager getInstance() {
		if (instance == null) {
			instance = new AuthenticationManager();
			instance.addUser(new User("admin", "admin", "C:/"));
			instance.addUser(new User("root", "admin", "C:/Windows/"));
			instance.addUser(new User("yves", "123456", "C:/Users/"));
			instance.addUser(new User("test", "test", "C:/"));
//			instance.addUser(new User("admin", "admin", "/tmp"));
//			instance.addUser(new User("root", "admin", "/home/yves"));
//			instance.addUser(new User("yves", "123456", "/home/yves"));
//			instance.addUser(new User("test", "test", "/"));
		}
		return instance;
	}
	
	private void addUser(User user) {
		users.put(user.getLogin(), user);
	}
	
	public User getUser(String login, String password) {
		User user = users.get(login);
		if (user != null && user.getPassword().equals(password)) return user;
		return null;
	}
	
}
