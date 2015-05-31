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
import com.dm.common.exception.MyRuntimeException;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public class AbstractDao<T extends IEntity> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> entityclass;
	
	public AbstractDao() {
		super();
		this.entityclass = getEntityClass();
	}

	/**
	 * @return
	 */
	protected SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(String id){
		return (T) sessionFactory.getCurrentSession().get(entityclass, id);
	}
	
	/**
	 * @param hql
	 * @param args
	 * @return
	 */
	public T findEntity(String hql,Object ...args){
		List<T> list = this.find(hql, args);
		if(list != null){
			if(list.size() > 1){
				throw new MyRuntimeException("查询结果不唯一.");
			}
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * @param hql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object ...args){
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(args != null && args.length > 0){
			for(int i=0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T load(String id){
		return (T)sessionFactory.getCurrentSession().load(entityclass, id);
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
			query.setFirstResult((page-1)*pageSize).setMaxResults(page*pageSize);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(entityclass).list();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		Type type = getClass().getGenericSuperclass();
		Class<T> result = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			result = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		}
		return result;
	}
}
