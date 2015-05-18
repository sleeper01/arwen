/**
 * 
 */
package com.dm.bizs.investigate.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.dm.bizs.investigate.domain.model.Question;
import com.dm.bizs.investigate.domain.model.Question.QuestionType;
import com.dm.common.dao.AbstractDao;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Repository
public class QuestionDao extends AbstractDao<Question> {

	/**
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Question> getList(Map<Object,Object>params,Integer page,Integer pageSize){
		StringBuffer hql = new StringBuffer();
		hql.append("from Question where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			if(null != ParamUtils.getDouble(params, "score", null)){
				hql.append(" and score=? ");
				args.add(ParamUtils.getDouble(params, "score", null));
			}
			if(StringUtils.hasText(ParamUtils.getString(params, "subject", ""))){
				hql.append(" and subject like ? ");
				args.add("%"+ParamUtils.getString(params, "subject", "")+"%");
			}
			if(StringUtils.hasText(ParamUtils.getString(params, "updateTimeBegin", ""))){
				hql.append(" and updateTime>=? ");
				args.add(Timestamp.valueOf(ParamUtils.getString(params, "updateTimeBegin", "")+" 00:00:00.0"));
			}
			
			if(StringUtils.hasText(ParamUtils.getString(params, "updateTimeEnd", ""))){
				hql.append(" and updateTime<=? ");
				args.add(Timestamp.valueOf(ParamUtils.getString(params, "updateTimeEnd", "")+" 23:59:59.9"));
			}
			if(StringUtils.hasText(ParamUtils.getString(params, "firLevelLabel", ""))){
				hql.append(" and firLevelLabel=? ");
				args.add(ParamUtils.getString(params, "firLevelLabel", ""));
			}
			if(StringUtils.hasText(ParamUtils.getString(params, "secLevelLabel", ""))){
				hql.append(" and secLevelLabel=? ");
				args.add(ParamUtils.getString(params, "secLevelLabel", ""));
			}
			if(StringUtils.hasText(ParamUtils.getString(params, "type", ""))){
				hql.append(" and type=? ");
				args.add(ParamUtils.getEnum(params, "type", QuestionType.values()));
			}
		}
		hql.append(" order by updateTime desc ");
		return super.getList(hql.toString(), args, page, pageSize);
	}
}
