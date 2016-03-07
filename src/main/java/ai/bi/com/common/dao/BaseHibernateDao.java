package ai.bi.com.common.dao;

import java.util.List;
import java.util.Map;

public interface BaseHibernateDao<T> {
	/**
	 * 保存对象
	 * 
	 * @param t
	 * 
	 */
	public void save(T t);

	/**
	 * 删除对象
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/**
	 * 更新对象
	 * 
	 * @param t
	 * 
	 */
	public void update(T t);

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public T getObjectByid(int id);

	/**
	 * 查询所有对象
	 * 
	 * @return
	 */
	public List<T> getObjectALL();

	/**
	 * 根据一组id查询一组对象
	 * 
	 * @param ids
	 * 
	 * @return
	 */
	public List<T> getObjectByids(int ids);
	/**
	 * 通过SQL查询,结果集不作处理直接返回
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param params
	 *            参数
	 * @return
	 */
	public List<Object> findSqlObject(String queryString, Object[] params);

	/**
	 * 通过SQL查询,结果集不作处理直接返回
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param params
	 *            参数
	 * @return Object[]
	 */
	public List<Object[]> find_sql(String queryString, Object[] params);

	/**
	 * 通过SQL查询，将结果集转换成Map对象，列名作为键(适用于有返回结果集的存储过程或查询语句)
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> find_sql_toMap(String queryString,
			Object[] params);

	/**
	 * 通过SQL执行无返回结果的语句，执行修改、删除、添加等(适用于无结果集返回SQL语句，不能用于存储过程)
	 * 
	 * @param queryString
	 * @param params
	 */
	public void executeSql(String queryString, Object[] params);

	/**
	 * 通过SQL执行无返回结果的存储过程(仅限于存储过程)
	 * 
	 * @param queryString
	 * @param params
	 */
	public void executeVoidProcedureSql(final String queryString,
			final Object[] params) throws Exception;

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
			final Object[] params);

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
			final String sql, final Object[] params);
}
