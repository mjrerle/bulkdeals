package p2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.dao.IUserDAO;
import p2.model.User;
import p2.util.HibernateUtil;

public class UserDAO extends GenericDAO<User> implements IUserDAO {

	public User findByEmailANDPassword(String email, String password) {
		Session session = HibernateUtil.getSession();
		User user = null;

		try {
			user =  (User) session.createQuery("FROM Interest WHERE email = " + email+" AND password =" + password);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	} 

}
