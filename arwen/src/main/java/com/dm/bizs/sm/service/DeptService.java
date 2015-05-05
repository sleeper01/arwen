/**
 * 
 */
package com.dm.bizs.sm.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.sm.dao.DeptDao;
import com.dm.bizs.sm.domain.model.Dept;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Service("deptService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class DeptService extends AbstractService<Dept> {

	@Autowired
	private DeptDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		super.update(params);
	}

	/**
	 * @param params
	 */
	public void addChild(Map<Object, Object> params){
		Dept parent = dao.get(ParamUtils.getString(params, "parentId", ""));
		if(parent == null)
			throw new MyRuntimeException();
		parent.addChild(params);
		super.update(parent);
	}
	
	/**
	 * @param params
	 */
	public void addUser(Map<Object, Object> params){
		Dept dept = dao.get(ParamUtils.getString(params, "deptId", ""));
		if(dept == null)
			throw new MyRuntimeException();
		dept.addUser(params);
		super.update(dept);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Dept> getDao() {
		return dao;
	}
}
