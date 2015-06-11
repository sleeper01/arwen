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

import com.dm.bizs.investigate.dao.QuestionaireTypeDao;
import com.dm.bizs.investigate.domain.model.QuestionaireType;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Service("questionaireTypeService")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class QuestionaireTypeService extends AbstractService<QuestionaireType> {

	@Autowired
	private QuestionaireTypeDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		QuestionaireType type = new QuestionaireType();
		type.caseCade(params);
		super.create(type);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return SerializeUtils.convertEntitiesToMaps(dao.getList(params));
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<QuestionaireType> getDao() {
		return dao;
	}
}
