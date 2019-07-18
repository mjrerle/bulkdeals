package p2.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.ITaxonomyDAO;
import p2.model.Taxonomy;
import p2.util.Glogger;
import p2.util.HibernateUtil;

public class TaxonomyDAO extends GenericDAO<Taxonomy> implements ITaxonomyDAO {
	private static Logger logger = Glogger.logger;

	@SuppressWarnings("unchecked")
	@Override
	public Taxonomy findByTaxonomy(Taxonomy taxonomy) {
		Session session = HibernateUtil.getSession();
		List<Taxonomy> result = null;

		try {
			Query query = session
					.createQuery("from Taxonomy where name like :name and type like :type and subType like :subType");
			query.setParameter("name", taxonomy.getName());
			query.setParameter("type", taxonomy.getType());
			query.setParameter("subType", taxonomy.getSubType());
			result = query.list();
			if (result.size() == 0) {
				taxonomy.setTaxonomyId(insert(taxonomy));
				if (taxonomy.getTaxonomyId() == -1)
					return null;
				else
					return taxonomy;
			} else {
				return result.get(0);
			}
		} catch (HibernateException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
