/**
 * 
 */
package com.dm.common.domain.model;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface IEntity {

	/**
	 * @return
	 */
	public String getId();
	
	/**
	 * @return
	 */
	public Map<Object,Object> toMap();
	
	/**
	 * @param params
	 */
	public void initCreateInfo(Map<Object,Object> params);
	
	/**
	 * @param params
	 */
	public void initUpdateInfo(Map<Object,Object> params);
	
	/**
	 * @param params
	 */
	public void caseCade(Map<Object,Object> params);
}
