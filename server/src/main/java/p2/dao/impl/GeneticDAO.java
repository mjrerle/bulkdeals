package p2.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import p2.util.HibernateUtil;

public class GeneticDAO<T> {
	
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GeneticDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public int insert(T t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int id = 0;

		try {
			tx = session.beginTransaction();
			id = Integer.parseInt(session.save(t).toString());
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return id;
	}

	public void update(T t) {

		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.merge(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = HibernateUtil.getSession();
		List<T> bees = null;

		try {
			bees = session.createQuery("FROM " + persistentClass.getSimpleName()).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bees;
	}

	@SuppressWarnings("unchecked")
	public T findById(int id) {
		Session session = HibernateUtil.getSession();
		T t = null;

		try {
			t = (T) session.get(persistentClass, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}

	@SuppressWarnings("null")
	public void deleteById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			session.beginTransaction();
			session.delete(session.get(persistentClass, id));
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}
}
