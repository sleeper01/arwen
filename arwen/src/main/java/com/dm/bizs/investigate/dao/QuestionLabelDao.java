/**
 * 
 */
package com.dm.bizs.investigate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dm.bizs.investigate.domain.model.QuestionLabel;
import com.dm.bizs.investigate.domain.model.QuestionLabel.QuestionLableLevel;
import com.dm.common.dao.AbstractDao;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class QuestionLabelDao extends AbstractDao<QuestionLabel> {
	
	/**
	 * @param params
	 * @return
	 */
	public List<QuestionLabel> getQuestionLabels(Map<Object,Object>params){
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<>();
		hql.append(" from QuestionLabel where 1=1 ");
		if(params != null){
			if(null != ParamUtils.getEnum(params, "level", QuestionLableLevel.values())){
				hql.append(" and level=? ");
				args.add(ParamUtils.getEnum(params, "level", QuestionLableLevel.values()));
			}
		}
		return super.find(hql.toString(), args.toArray());
	}
}
