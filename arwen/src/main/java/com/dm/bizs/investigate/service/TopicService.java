package com.dm.bizs.investigate.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.investigate.dao.QuestionDao;
import com.dm.bizs.investigate.dao.TopicDao;
import com.dm.bizs.investigate.domain.model.Question;
import com.dm.bizs.investigate.domain.model.Topic;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Service("topicService")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class TopicService extends AbstractService<Topic> {

	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private TopicDao dao;
	
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
	 * @Title: addQuestion
	 * @Description: 
	 * @param params
	 */
	public void addQuestion(Map<Object, Object> params){
		Topic topic = super.get(ParamUtils.getString(params, "topicId", ""));
		Question question = questionDao.get(ParamUtils.getString(params, "questionId", ""));
		topic.addQuestion(question, params);
		super.update(topic);
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
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Topic> getDao() {
		return dao;
	}
}
