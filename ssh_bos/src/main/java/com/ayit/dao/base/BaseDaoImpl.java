package com.ayit.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ayit.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	//在父类的构造方法中动态获取entityClass对象
	public BaseDaoImpl() {
		/*ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
		*/
		
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		
		entityClass = (Class<T>) actualTypeArguments[0];
		
		
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public T selectById(Serializable id) {
		
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> selectAll() {
		String hql = "From " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeQuery(String queryName,Object...objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		//获取要执行的sql语句，这些语句都写在对应的类中
		Query query = session.getNamedQuery(queryName);
		//为HQL语句中的？赋值
		int count = 0;
		for (Object object : objects) {
			query.setParameter(count++, object);
		}
		
		//执行更新
		query.executeUpdate();
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//查询total---总数据量
		detachedCriteria.setProjection(Projections.rowCount());//指定hibernate框架发出sql的形式----》select count(*) from bc_staff;
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count = countList.get(0);
		pageBean.setTotal(count.intValue());
		
		//查询rows---当前页需要展示的数据集合
		detachedCriteria.setProjection(null);//指定hibernate框架发出sql的形式----》select * from bc_staff;
		detachedCriteria.setResultTransformer(detachedCriteria.ROOT_ENTITY);
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	
		

}
