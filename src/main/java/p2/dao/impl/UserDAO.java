package p2.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.IUserDAO;
import p2.model.User;
import p2.util.Glogger;
import p2.util.HibernateUtil;

public class UserDAO extends GenericDAO<User> implements IUserDAO {
	private static Logger logger = Glogger.logger;

	public User findByEmailAndPassword(String email, String password) {
		Session session = HibernateUtil.getSession();
		User user = null;

		try {
			Query query = session.createQuery("FROM Interest WHERE email = :email AND password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			user = (User) query.list().get(0);
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

}
