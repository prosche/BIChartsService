package ai.bi.com.charts.dao;

import java.util.List;

public interface BaseHibernateDao<T> {
	//�������
    public void save(T t);
    //ɾ������
    public void delete(int id);
    //���¶���
    public void update(T t);
    //����id��ѯ����
    public T getObjectByid(int id);
    //��ѯ���ж���
    public List<T> getObjectALL();
    //����һ��id��ѯһ�����
    public List<T> getObjectByids(int ids);

}
