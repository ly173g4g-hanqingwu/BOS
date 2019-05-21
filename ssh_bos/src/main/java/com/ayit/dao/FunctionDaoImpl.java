package com.ayit.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.ayit.beans.Function;
import com.ayit.dao.base.BaseDaoImpl;
import com.ayit.utils.PageBean;
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
	@Override
	public List<Function> selectAll() {

		String hql = "FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findFunctionListByUserId(String id) {
		String hql = "select distinct f From Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id=?";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

	/*@Override
	public List<Function> findAllMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = '1' order by zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql = "select distinct f From Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id=? "
				+ "AND f.generatemenu = '1' order by f.zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
		
	}*/
	
	// 查询所有菜单
		public List<Function> findAllMenu() {
			String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex asc";
			List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
			return list;
		}
		
		//根据用户id查询菜单
		public List<Function> findMenuByUserId(String userId) {
			String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
					+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
					+ "ORDER BY f.zindex asc";
			List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, userId);
			return list;
		}
	
	
}
