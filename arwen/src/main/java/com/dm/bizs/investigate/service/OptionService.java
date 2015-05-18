/**
 * 
 */
package com.dm.bizs.investigate.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.investigate.dao.OptionDao;
import com.dm.bizs.investigate.domain.model.Option;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Service("optionService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class OptionService extends AbstractService<Option> {

	@Autowired
	private OptionDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		super.update(params);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Option> getDao() {
		return dao;
	}

}
