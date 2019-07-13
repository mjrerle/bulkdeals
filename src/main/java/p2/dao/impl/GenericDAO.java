package p2.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.util.Glogger;
import p2.util.HibernateUtil;

public class GenericDAO<T> {

	private static Logger logger = Glogger.logger;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public int insert(T t) {
		Session session = HibernateUtil.getSession();
		int id = -1;

		try {
			session.beginTransaction();
			id = Integer.parseInt(session.save(t).toString());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return id;
	}

	public boolean update(T t) {
		boolean success = false;
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.merge(t);
			session.getTransaction().commit();
			success = true;
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
			session.getTransaction().rollback();
			success = false;
		} finally {
			session.close();
		}
		return success;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = HibernateUtil.getSession();
		List<T> list = null;

		try {
			list = session.createQuery("FROM " + persistentClass.getSimpleName()).list();
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public T findById(int id) {
		Session session = HibernateUtil.getSession();
		T t = null;

		try {
			t = (T) session.get(persistentClass, id);
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}

	public boolean deleteById(int id) {
		boolean success = false;
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.delete(session.get(persistentClass, id));
			session.getTransaction().commit();
			success = true;
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
			session.getTransaction().rollback();
			success = false;
		} finally {
			session.close();
		}
		return success;

	}
}
