/**
 * 
 */
package com.dm.bizs.investigate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dm.bizs.investigate.domain.model.Questionaire;
import com.dm.common.dao.AbstractDao;

/**
 * @author Administrator
 *
 */
@Repository
public class QuestionaireDao extends AbstractDao<Questionaire> {

	/**
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Questionaire> getPageList(Map<Object, Object> params, Integer page, Integer pageSize){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Questionaire where 1=1 ");
		List<Object> args = new ArrayList<>();
		if(params != null){
			
		}
		Query query = super.getSessionFactory().getCurrentSession().createQuery(sb.toString());
		if(args.size() > 0){
			for(int i=0;i<args.size();i++){
				query.setParameter(i, args.get(i));
			}
		}
		query.setFirstResult((page-1)*pageSize).setMaxResults(page*pageSize);
		return query.list();
	}
}
