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

import com.dm.bizs.investigate.dao.QuestionCopyDao;
import com.dm.bizs.investigate.domain.model.QuestionCopy;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Service("questionCopyService")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class QuestionCopyService extends AbstractService<QuestionCopy> {

	@Autowired
	private QuestionCopyDao dao;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws MyRuntimeException {
		QuestionCopy copy = super.get(id);
		copy.disable();
		super.update(copy);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<QuestionCopy> getDao() {
		return dao;
	}
}
