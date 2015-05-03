/**
 * 
 */
package com.dm.bizs.sm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dm.bizs.sm.domain.model.AppType;
import com.dm.common.dao.AbstractDao;

/**
 * @author Administrator
 *
 */
@Repository
public class AppTypeDao extends AbstractDao<AppType> {

	/**
	 * @return
	 */
	public List<AppType> getAppTypes(){
		return super.find("from AppType");
	}
}
