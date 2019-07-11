package p2.service.impl.test;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import p2.model.User;
import p2.service.impl.UserService;
import p2.util.Roles;

public class UserServiceTest {
	
	@Test
	public void insert() {
		assertNull(UserService.insert(new User("vajira", "Hapu", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322)));
	}
	
}
