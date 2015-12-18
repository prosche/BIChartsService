package ai.bi.com.charts.dao;

import java.util.List;

public interface BaseHibernateDao<T> {
	//保存对象
    public void save(T t);
    //删除对象
    public void delete(int id);
    //更新对象
    public void update(T t);
    //根据id查询对象
    public T getObjectByid(int id);
    //查询所有对象
    public List<T> getObjectALL();
    //根据一组id查询一组对象
    public List<T> getObjectByids(int ids);

}
