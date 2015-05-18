/**
 * 
 */
package com.dm.bizs.sm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dm.bizs.sm.domain.model.Dept;
import com.dm.bizs.sm.domain.model.Party;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.StatusEntity.Status;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class DeptDao extends AbstractDao<Dept> {

	public List<Dept> getDepts(Map<Object,Object> params){
		StringBuffer hql = new StringBuffer("from Dept where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			if(null != ParamUtils.getEnum(params, "status", Status.values())){
				hql.append(" and status=? ");
				args.add(ParamUtils.getEnum(params, "status", Status.values()));
			}
			if(!"".equals(ParamUtils.getString(params, "partyId", ""))){
				hql.append(" and party.id=? ");
				args.add(ParamUtils.getString(params, "partyId", ""));
			}
			if(!"".equals(ParamUtils.getString(params, "parentId", ""))){
				hql.append(" and parent.id=? ");
				args.add(ParamUtils.getString(params, "parentId", ""));
			}
		}
		return super.find(hql.toString(), args.toArray());
	}
}
