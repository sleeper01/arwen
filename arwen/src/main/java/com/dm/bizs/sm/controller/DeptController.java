/**
 * 
 */
package com.dm.bizs.sm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.sm.domain.model.Dept;
import com.dm.bizs.sm.service.DeptService;
import com.dm.common.constants.ResultConstants;
import com.dm.common.controller.AbstractController;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends AbstractController<Dept> {

	@Autowired
	private DeptService service;
	
	/**
	 * @param params
	 * @return
	 * @throws MyRuntimeException
	 */
	@RequestMapping("/addChild")
	public @ResponseBody Object addChild(@RequestBody Map<Object,Object> params,HttpServletRequest request)throws MyRuntimeException{
		this.getSessionAccount(params, request);
		service.addChild(params);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	@RequestMapping("/addUser")
	public @ResponseBody Object addUser(@RequestBody Map<Object,Object> params,HttpServletRequest request)throws MyRuntimeException{
		this.getSessionAccount(params, request);
		service.addUser(params);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	@RequestMapping("/getDeptById")
	@ResponseBody
	public Object getDeptById(@RequestParam("id")String id){
		return service.getEntityMap(id);
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Dept> getService() {
		return service;
	}

}
