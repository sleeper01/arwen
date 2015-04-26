/**
 * 
 */
package com.dm.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.common.constants.ResultConstants;
import com.dm.common.domain.model.IEntity;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public abstract class AbstractController<T extends IEntity> {

	/**
	 * @param params
	 * @return
	 * @throws MyRuntimeException
	 */
	@RequestMapping("/persist")
	public @ResponseBody Object persist(Map<Object,Object> params)throws MyRuntimeException{
		if(!"".equals(ParamUtils.getString(params, "id", ""))){
			this.getService().update(params);
		}else{
			this.getService().create(params);
		}
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	public @ResponseBody Object get(String id){
		return this.getService().getEntity(id);
	}
	
	protected abstract AbstractService<T> getService();
}
