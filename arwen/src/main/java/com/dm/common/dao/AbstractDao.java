/**
 * 
 */
package com.dm.common.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.MappedSuperclass;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.dm.common.domain.model.IEntity;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public class AbstractDao<T extends IEntity> {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @return
	 */
	protected SessionFactory getSessJionFactory(){
		return sessionFactory;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(String id){
		return (T) sessionFactory.getCurrentSession().get(this.getClass1(), id);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T load(String id){
		return (T)sessionFactory.getCurrentSession().load(getClass1(), id);
	}
	
	/**
	 * @param entity
	 */
	public void save(T entity){
		sessionFactory.getCurrentSession().save(entity);
	}
	
	/**
	 * @param entity
	 */
	public void update(T entity){
		sessionFactory.getCurrentSession().update(entity);
	}
	
	/**
	 * @param entity
	 */
	public void delete(T entity){
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	/**
	 * @param hql
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(String hql,List<Object> params,Integer page,Integer pageSize){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(!CollectionUtils.isEmpty(params)){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		if(page != null && pageSize != null){
			query.setFirstResult((page-1)*pageSize).setMaxResults(page*pageSize-1);
		}
		return query.list();
	}
	
	private Class<T> getClass1(){
		Type type = getClass().getGenericSuperclass();  
        Type[] trueType = ((ParameterizedType) type).getActualTypeArguments();
		return (Class<T>) trueType[0];
	}
}
