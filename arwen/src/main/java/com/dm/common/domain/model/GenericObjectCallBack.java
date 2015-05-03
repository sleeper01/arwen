/**
 * 
 */
package com.dm.common.domain.model;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface GenericObjectCallBack<T> {

	public T genericObjects(Map<Object,Object>params);
}
