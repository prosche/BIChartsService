package ai.bi.com.charts.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ai.bi.com.charts.dao.RevenueTypeDao;
import ai.bi.com.charts.model.Revenue;
import ai.bi.com.common.dao.MyHibernateDaoSupport;

@Transactional
@Repository("revenueTypeDao")
public class RevenueTypeDaoImpl extends MyHibernateDaoSupport implements
		RevenueTypeDao {
	@Override
	public List<Revenue> getAll() {
		Criteria c = this.getSession().createCriteria(Revenue.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		c.add(Restrictions.eq("id", "10001"));
		return c.list();
	}
}
