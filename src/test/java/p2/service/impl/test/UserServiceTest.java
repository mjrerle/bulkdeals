package p2.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import p2.model.User;
import p2.service.UserService;
import p2.util.Roles;

public class UserServiceTest {

	@Test
	public void insert() {
		int id = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));

		User user = UserService.findById(id);
		Assert.assertTrue(user.getUserId() >= 0);
		UserService.deleteById(user.getUserId());
	}

	@Test
	public void update() {
		int id = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));

		User user = UserService.findById(id);
		user.setCreditCardNumber(0);
		Assert.assertTrue(UserService.update(user));
		Assert.assertEquals(UserService.findById(id).getCreditCardNumber(), 0);

		UserService.deleteById(user.getUserId());
	}

	@Test
	public void deleteById() {
		int id = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));

		User user = UserService.findById(id);
		Assert.assertTrue(UserService.deleteById(user.getUserId()));
	}

	@Test
	public void findAll() {
		List<User> allUsers = new ArrayList<>();
		int id = UserService.insert(new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		int id2 = UserService.insert(new User("vajira", "Hapu Arachchige", "adsf@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		int id3 = UserService.insert(new User("vajira", "Hapu Arachchige", "sdfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));

		allUsers = UserService.findAll();
		Assert.assertEquals(allUsers.size(), 3);

		UserService.deleteById(id);
		UserService.deleteById(id2);
		UserService.deleteById(id3);
	}

}
