/**
 * 
 */
package com.dm.bizs.sm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dm.bizs.sm.domain.model.Party;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.StatusEntity.Status;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 */
@Repository
public class PartyDao extends AbstractDao<Party> {

	/**
	 * @param params
	 * @return
	 */
	public List<Party> getParties(Map<Object,Object> params){
		StringBuffer hql = new StringBuffer("from Party where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			if(null != ParamUtils.getEnum(params, "status", Status.values())){
				hql.append(" and status=? ");
				args.add(ParamUtils.getEnum(params, "status", Status.values()));
			}
		}
		return super.find(hql.toString(), args.toArray());
	}
}
