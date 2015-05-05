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

import com.dm.bizs.sm.domain.model.Account;
import com.dm.bizs.sm.service.AccountService;
import com.dm.common.constants.SessionConstants;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController extends AbstractController<Account> {
	
	@Autowired
	private AccountService service;
	
	@RequestMapping("/isLogin")
	@ResponseBody
	public Object isAccountLogin(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> res = new HashMap<>();
		res.put("isLogin",request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT) != null);
		res.put("account", request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT));
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/login")
	@ResponseBody
	public Object login(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> res = service.login(params);
		if((Boolean)res.get("success") && res.get("account") != null){
			Map<Object,Object> account = (Map<Object,Object>)res.get("account");
			request.getSession().setAttribute(SessionConstants.LOGIN_ACCOUNT, account);
			request.getSession().setAttribute(SessionConstants.LOGIN_ACCOUNT_ID, account.get("id"));
			request.getSession().setAttribute(SessionConstants.LOGIN_ACCOUNT_NAME, account.get("name"));
		}
		return res;
	}
	
	@RequestMapping("/loginout")
	@ResponseBody
	public Object loginout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(SessionConstants.LOGIN_ACCOUNT);
		request.getSession().removeAttribute(SessionConstants.LOGIN_ACCOUNT_ID);
		request.getSession().removeAttribute(SessionConstants.LOGIN_ACCOUNT_NAME);
		Map<Object,Object> res = new HashMap<>();
		res.put("success", false);
		return res;
	}
	
	@RequestMapping("/getAccountAuth")
	@ResponseBody
	public Object getAccountAuth(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object>params = new HashMap<>();
		super.getSessionAccount(params, request);
		params.put("id", params.get(SessionConstants.LOGIN_ACCOUNT_ID));
		return service.getAccountAuth(params);
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public Object getList(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		return service.getList(params);
	}
	
	@RequestMapping("/getAccountByNameLike")
	@ResponseBody
	public Object getAccountByNameLike(@RequestParam("q") String q,@RequestParam("page_limit") Integer page_limit,HttpServletRequest request,HttpServletResponse response){
		return service.getAccountByNameLike(q, page_limit);
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Account> getService() {
		return service;
	}

}
