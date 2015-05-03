/**
 * 
 */
package com.dm.common.service;

import java.util.Collection;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.IEntity;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public abstract class AbstractService<T extends IEntity> {

	/**
	 * @param params
	 */
	public abstract void create(Map<Object,Object> params)throws MyRuntimeException;
	
	/**
	 * @param params
	 */
	public void update(Map<Object,Object> params)throws MyRuntimeException{
		T entity = getEntity(params);
		entity.caseCade(params);
		this.update(entity);
	}
	
	/**
	 * @param id
	 * @throws MyRuntimeException
	 */
	public void delete(String id) throws MyRuntimeException{
		T entity = this.get(id);
		if(entity == null)
			throw new MyRuntimeException(String.format("id 为：%s的实体不存在.", id));
		this.getDao().delete(entity);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public abstract Collection<Map<Object,Object>> getList(Map<Object,Object> params);
	
	/**
	 * @param id
	 * @return
	 */
	public Map<Object,Object> getEntityMap(String id){
		T entity = this.get(id);
		if(entity != null)
			return entity.toMap();
		return null;
	}
	
	/**
	 * @param params
	 * @return
	 */
	public T getEntity(Map<Object,Object> params){
		T entity = getDao().get(ParamUtils.getString(params, "id", ""));
		if(entity == null)
			throw new MyRuntimeException(String.format("id为%s的实体不存在.", ParamUtils.getString(params, "id", "")));
		return entity;
	}
	/**
	 * @param id
	 * @return
	 */
	protected T get(String id){
		return getDao().get(id);
	}
	
	/**
	 * @param id
	 * @return
	 */
	protected T load(String id){
		return getDao().load(id);
	}
	/**
	 * @param entity
	 * @param params
	 */
	protected void create(T entity){
		getDao().save(entity);
	}
	
	/**
	 * @param entity
	 * @param params
	 */
	protected void update(T entity){
		getDao().update(entity);
	}
	
	/**
	 * @return
	 */
	protected abstract AbstractDao<T> getDao();
}
