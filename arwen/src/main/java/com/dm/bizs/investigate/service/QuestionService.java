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

import com.dm.bizs.investigate.dao.QuestionDao;
import com.dm.bizs.investigate.domain.model.Option;
import com.dm.bizs.investigate.domain.model.Question;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.GenericObjectCallBack;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Service("questionService")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class QuestionService extends AbstractService<Question> {

	@Autowired
	private QuestionDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		final Question question = new Question();
		question.caseCade(params);
		super.create(question);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public Map<Object,Object> create1(Map<Object, Object> params){
		Question question = new Question();
		question.caseCade(params);
		super.create(question);
		return question.toMap();
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	public Map<Object,Object> update1(Map<Object, Object> params) throws MyRuntimeException {
		Question entity = getEntity(params);
		entity.caseCade(params);
		this.update(entity);
		return entity.toMap();
	}
	
	/**
	 * @param params
	 */
	public void addOption(Map<Object, Object> params){
		Question question = super.get(ParamUtils.getString(params, "questionId", ""));
		question.addOption(params);
		super.update(question);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params,Integer page,Integer pageSize) {
		return SerializeUtils.convertEntitiesToMaps(dao.getList(params, page, pageSize));
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
	protected AbstractDao<Question> getDao() {
		return dao;
	}
}
