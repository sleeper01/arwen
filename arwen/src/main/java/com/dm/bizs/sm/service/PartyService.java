/**
 * 
 */
package com.dm.bizs.sm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.sm.dao.PartyDao;
import com.dm.bizs.sm.domain.model.Party;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Service("partyService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class PartyService extends AbstractService<Party> {

	@Autowired
	private PartyDao dao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		Party party = new Party();
		party.caseCade(params);
		party.enable();
		super.create(party);
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
	public void addDept(Map<Object, Object> params){
		Party party = super.get(ParamUtils.getString(params, "partyId", ""));
		if(party == null){
			throw new MyRuntimeException();
		}
		party.addDept(params);
		super.update(party);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		List<Party> parties = dao.getParties(params);
		List<Map<Object, Object>> res = new ArrayList<>();
		for(Party p : parties){
			res.add(p.toNode());
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Party> getDao() {
		return dao;
	}
}
