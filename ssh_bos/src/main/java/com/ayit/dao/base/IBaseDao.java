package com.ayit.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ayit.beans.Staff;
import com.ayit.utils.PageBean;

public interface IBaseDao<T> {
	 public void save(T entity);
	 public void saveOrUpdate(T entity);
	 public void delete(T entity);
     public void update(T entity);
     public T selectById(Serializable id);
     public List<T> selectAll();
     public List<T> findByCriteria(DetachedCriteria criteria);
     public void executeQuery(String queryName,Object...objects);
     public void pageQuery(PageBean pageBean);
}
