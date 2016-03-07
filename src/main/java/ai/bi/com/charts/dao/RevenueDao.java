package ai.bi.com.charts.dao;

import java.util.List;
import java.util.Map;

import ai.bi.com.charts.bean.RevenueBean;
import ai.bi.com.charts.model.Revenue;
import ai.bi.com.common.dao.BaseHibernateDao;

public interface RevenueDao extends BaseHibernateDao<Revenue>{

	List<Map<String, Object>> getSql(RevenueBean list);
}
