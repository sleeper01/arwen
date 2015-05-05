/**
 * 
 */
package com.dm.bizs.sm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.sm.domain.model.AppType;
import com.dm.bizs.sm.service.AppTypeService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/apptype")
public class AppTypeController extends AbstractController<AppType> {

	@Autowired
	private AppTypeService service;
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		return service.getList(params);
	}
	
	@RequestMapping("/getEnableList")
	@ResponseBody
	public Object getEnableList(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		return service.getEnableList(params);
	}
	
	@RequestMapping("/addApp")
	@ResponseBody
	public Object addApp(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		service.addApp(params);
		Map<Object,Object> res = new HashMap<>();
		res.put("success", true);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<AppType> getService() {
		return service;
	}
}
