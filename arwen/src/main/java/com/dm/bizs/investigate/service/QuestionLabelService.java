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

import com.dm.bizs.investigate.dao.QuestionLabelDao;
import com.dm.bizs.investigate.domain.model.QuestionLabel;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 * 
 */
@Service("questionLabelService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class QuestionLabelService extends AbstractService<QuestionLabel> {

	@Autowired
	private QuestionLabelDao dao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		QuestionLabel label = new QuestionLabel();
		label.caseCade(params);
		super.create(label);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		super.update(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return SerializeUtils.convertEntitiesToMaps(dao.getQuestionLabels(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<QuestionLabel> getDao() {
		return dao;
	}
}
