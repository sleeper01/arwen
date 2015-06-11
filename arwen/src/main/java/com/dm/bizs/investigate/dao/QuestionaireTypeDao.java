/**
 * 
 */
package com.dm.bizs.investigate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dm.bizs.investigate.domain.model.QuestionaireType;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.StatusEntity.Status;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class QuestionaireTypeDao extends AbstractDao<QuestionaireType> {

	/**
	 * @param params
	 * @return
	 */
	public List<QuestionaireType> getList(Map<Object,Object> params){
		StringBuffer sb = new StringBuffer(" from QuestionaireType where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			if(null != ParamUtils.getEnum(params, "status", Status.values())){
				sb.append(" and status=? ");
				args.add(ParamUtils.getEnum(params, "status", Status.values()));
			}
		}
		return super.find(sb.toString(), args.toArray());
	}
}
