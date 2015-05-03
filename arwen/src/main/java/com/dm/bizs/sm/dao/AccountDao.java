/**
 * 
 */
package com.dm.bizs.sm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dm.bizs.sm.domain.model.Account;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class AccountDao extends AbstractDao<Account> {
	
	/**
	 * @param params
	 * @return
	 */
	public Account getAccount(Map<Object,Object> params){
		StringBuffer hql = new StringBuffer();
		hql.append(" from Account where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			if(!"".equals(ParamUtils.getString(params, "name", ""))){
				hql.append(" and name = ? ");
				args.add(ParamUtils.getString(params, "name", ""));
			}
			if(null != ParamUtils.getEnum(params, "status", StatusEntity.Status.values())){
				hql.append(" and status = ? ");
				args.add(ParamUtils.getEnum(params, "status", StatusEntity.Status.values()));
			}
		}
		return super.findEntity(hql.toString(), args.toArray());
	}

}
