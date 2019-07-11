package p2.service.impl.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import p2.model.User;
import p2.service.impl.UserService;
import p2.util.Roles;

public class UserServiceTest {
	
	@Test
	public void insert() {
		assertNull(UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322)));
	}
	
	@Test
	public void update() {
		
		User user = UserService.findById(50);
		user.setEmail("abcdef@gmail.com");
		UserService.update(user);
		System.out.println(UserService.findById(50).toString());
		
	}

	@Test
	public void deleteById() {
		UserService.deleteById(50);
		
	}
	
	
	@Test
	public void findAll() {
		
		for(User user: UserService.findAll())
			System.out.println(user.toString());
		
	}
	
}
