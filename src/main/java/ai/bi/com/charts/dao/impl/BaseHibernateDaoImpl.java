package ai.bi.com.charts.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ai.bi.com.charts.dao.BaseHibernateDao;

@SuppressWarnings("unchecked")
@Transactional
abstract public class BaseHibernateDaoImpl<T> implements BaseHibernateDao<T> {
	private static final Logger log = LogManager.getLogger();
	// ע��sessionfactory
	@Resource
	private SessionFactory sessionFactory;
	private Class clazz;

	// ���췽������ȡT����ʵ����
	public BaseHibernateDaoImpl(){
        ParameterizedType pType=(ParameterizedType) this.getClass().getGenericSuperclass();
        clazz=(Class) pType.getActualTypeArguments()[0];
        log.debug(clazz.getSimpleName());
    }

	/*
	 * ɾ������ (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#delete(int)
	 */
	public void delete(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().get(clazz, id));
	}

	/*
	 * ��ѯ���ж��� (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#getObjectALL()
	 */

	public List<T> getObjectALL() {
		// System.out.println("=====��"+"from"+clazz.getSimpleName());
		// System.out.println("=====��"+"from    "+clazz.getSimpleName());
		// System.out.println("--------------"+clazz.getSimpleName());
		return sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getSimpleName()).list();
	}

	/*
	 * ����id��ȡ���� (non-Javadoc)
	 * 
	 * @see com.ssh.SpringMVC.Dao.IBaseDao#getObjectByid(int)
	 */
	public T getObjectByid(int id) {
		// TODO Auto-generated method stub
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	/*
	 * ����һ��id��ȡһ����� (non-Javadoc)
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
	 * ������� (non-Javadoc)
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

}
