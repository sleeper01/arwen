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

import com.dm.bizs.sm.dao.AppDao;
import com.dm.bizs.sm.domain.model.App;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Service("appService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class AppService extends AbstractService<App> {

	@Autowired
	private AppDao dao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<App> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		App app = super.getEntity(params);
		app.caseCade(params);
		super.update(app);
	}
}
