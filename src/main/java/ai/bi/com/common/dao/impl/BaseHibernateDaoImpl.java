package ai.bi.com.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import ai.bi.com.common.dao.BaseHibernateDao;

@SuppressWarnings("unchecked")
@Transactional
abstract public class BaseHibernateDaoImpl<T> implements BaseHibernateDao<T> {
	private static final Logger log = LogManager.getLogger();
	// 注入sessionfactory
	@Resource
	public SessionFactory sessionFactory;
	private Class clazz;

	// 构造方法：获取T的真实类型
	public BaseHibernateDaoImpl() {
		ParameterizedType pType = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) pType.getActualTypeArguments()[0];
		log.debug(clazz.getSimpleName());
	}

	/*
	 * 删除对象 (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#delete(int)
	 */
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().get(clazz, id));
	}

	/*
	 * 查询所有对象 (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#getObjectALL()
	 */

	public List<T> getObjectALL() {
		// System.out.println("=====："+"from"+clazz.getSimpleName());
		// System.out.println("=====："+"from    "+clazz.getSimpleName());
		// System.out.println("--------------"+clazz.getSimpleName());
		return sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getSimpleName()).list();
	}

	/*
	 * 根据id获取对象 (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#getObjectByid(int)
	 */
	public T getObjectByid(int id) {
		// TODO Auto-generated method stub
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	/*
	 * 根据一组id获取一组对象 (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#getObjectByids(int)
	 */
	public List<T> getObjectByids(int ids) {
		// TODO Auto-generated method stub
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from" + clazz.getSimpleName() + "where id in(:ids)")
				.setParameter("ids", ids).list();
	}

	/*
	 * 保存对象 (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#save(java.lang.Object)
	 */
	public void save(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(t);
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(t);
	}

	/**
	 * 通过SQL查询,结果集不作处理直接返回
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param params
	 *            参数
	 * @return Object
	 */
	public List<Object> findSqlObject(String queryString, Object[] params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(queryString);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List<Object> list = query.list();
		return list;
	}
	/**
	 * 通过SQL查询,结果集不作处理直接返回
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param params
	 *            参数
	 * @return Object[]
	 */
	public List<Object[]> find_sql(String queryString, Object[] params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(queryString);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * 通过SQL查询，将结果集转换成Map对象，列名作为键(适用于有返回结果集的存储过程或查询语句)
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> find_sql_toMap(String queryString,
			Object[] params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(queryString);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List<java.util.Map<String, Object>> list = query.list();
		return list;
	}

	/**
	 * 通过SQL执行无返回结果的语句，执行修改、删除、添加等(适用于无结果集返回SQL语句，不能用于存储过程)
	 * 
	 * @param queryString
	 * @param params
	 */
	public void executeSql(String queryString, Object[] params) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * 通过SQL执行无返回结果的存储过程(仅限于存储过程)
	 * 
	 * @param queryString
	 * @param params
	 */
	public void executeVoidProcedureSql(final String queryString,
			final Object[] params) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				ResultSet rs = null;
				CallableStatement call = conn.prepareCall("{" + queryString
						+ "}");
				if (null != params) {
					for (int i = 0; i < params.length; i++) {
						call.setObject(i + 1, params[i]);
					}
				}
				rs = call.executeQuery();
				call.close();
				rs.close();
			}
		});
	}

	/**
	 * 通过存储过程查询(单结果集)
	 * 
	 * @param sql
	 *            查询sql
	 * @param params
	 *            参数
	 * @param columnNum
	 *            返回的列数
	 * @return
	 */
	public List<Map<String, Object>> find_procedure(final String sql,
			final Object[] params) {
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			Session session = sessionFactory.getCurrentSession();
			session.doWork(new Work() {
				public void execute(Connection conn) throws SQLException {
					CallableStatement cs = null;
					ResultSet rs = null;
					cs = conn.prepareCall(sql);
					for (int i = 1; i <= params.length; i++) {
						cs.setObject(i, params[i - 1]);// 设置参数
					}
					rs = cs.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					int colCount = metaData.getColumnCount();
					while (rs.next()) {
						Map<String, Object> map = new HashMap<String, Object>();
						for (int i = 1; i <= colCount; i++) {
							String colName = metaData.getColumnName(i);
							map.put(colName, rs.getObject(colName));
						}
						result.add(map);
					}
					close(cs, rs);
				}
			});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过存储过程查询(多结果集)
	 * 
	 * @param sql
	 *            查询sql
	 * @param params
	 *            参数
	 * @param columnNum
	 *            返回的列数
	 * @return
	 */
	public List<List<Map<String, Object>>> find_procedure_multi(
			final String sql, final Object[] params) {
		final List<List<Map<String, Object>>> result = new ArrayList<List<Map<String, Object>>>();
		try {
			// conn =
			// SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			Session session = sessionFactory.getCurrentSession();
			session.doWork(new Work() {
				public void execute(Connection conn) throws SQLException {
					CallableStatement cs = null;
					ResultSet rs = null;
					cs = conn.prepareCall(sql);
					for (int i = 1; i <= params.length; i++) {
						cs.setObject(i, params[i - 1]);
					}
					boolean hadResults = cs.execute();
					ResultSetMetaData metaData = null;
					while (hadResults) {// 遍历结果集
						List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();// 用于装该结果集的内容
						rs = cs.getResultSet();// 获取当前结果集
						metaData = rs.getMetaData();
						int colCount = metaData.getColumnCount();// 获取当前结果集的列数
						while (rs.next()) {
							Map<String, Object> map = new HashMap<String, Object>();
							for (int i = 1; i <= colCount; i++) {
								String colName = metaData.getColumnName(i);
								map.put(colName, rs.getObject(colName));
							}
							rsList.add(map);
						}
						result.add(rsList);
						close(null, rs);// 遍历完一个结果集，将其关闭
						hadResults = cs.getMoreResults();// 移到下一个结果集
					}
					close(cs, rs);
				}
			});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void close(CallableStatement cs, ResultSet rs) {
		try {
			if (cs != null) {
				cs.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
