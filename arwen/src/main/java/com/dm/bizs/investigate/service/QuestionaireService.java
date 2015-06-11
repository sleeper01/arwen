package com.dm.bizs.investigate.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.investigate.dao.QuestionaireDao;
import com.dm.bizs.investigate.domain.model.Questionaire;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Service("questionaireService")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class QuestionaireService extends AbstractService<Questionaire> {

	@Autowired
	private QuestionaireDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		
	}
	
	public Map<Object,Object> create1(Map<Object,Object> params) throws MyRuntimeException {
		Questionaire qa = new Questionaire();
		qa.caseCade(params);
		super.create(qa);
		return qa.toMap();
	}
	
	public Map<Object,Object> update1(Map<Object,Object> params)throws MyRuntimeException{
		Questionaire qa = super.get(ParamUtils.getString(params, "id", ""));
		qa.caseCade(params);
		super.update(qa);
		return qa.toMap();
	}
	
	public void addTopic(Map<Object,Object> params){
		Questionaire qa = super.get(ParamUtils.getString(params, "qaId", ""));
		qa.addTopic(params);
		super.update(qa);
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
	 * @see com.dm.common.service.AbstractService#getPageList(java.util.Map, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Object getPageList(Map<Object, Object> params, Integer page,
			Integer pageSize) {
		return SerializeUtils.convertEntitiesToMaps(dao.getPageList(params, page, pageSize));
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Questionaire> getDao() {
		return dao;
	}
}
