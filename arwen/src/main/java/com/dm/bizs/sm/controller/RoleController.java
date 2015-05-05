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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.sm.domain.model.Role;
import com.dm.bizs.sm.service.RoleService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends AbstractController<Role> {

	@Autowired
	private RoleService service;
	
	/**
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addAccount")
	@ResponseBody
	public Object addAccount(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> res = new HashMap<>();
		res.put("success", true);
		service.addAccount(params);
		return res;
	}
	
	/**
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addApps")
	@ResponseBody
	public Object addApps(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> res = new HashMap<>();
		res.put("success", true);
		service.addApps(params);
		return res;
	}
	
	/**
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/removeAccount")
	@ResponseBody
	public Object removeAccount(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> res = new HashMap<>();
		res.put("success", true);
		service.removeAccount(params);
		return res;
	}
	
	@RequestMapping("/getRoleById")
	@ResponseBody
	public Object getRoleById(@RequestParam("id")String id,HttpServletRequest request,HttpServletResponse response){
		return service.getEntityMap(id);
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Role> getService() {
		return service;
	}

}
