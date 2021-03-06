/**
 * 
 */
package com.dm.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.common.constants.ResultConstants;
import com.dm.common.constants.SessionConstants;
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
	public @ResponseBody Object persist(@RequestBody Map<Object,Object> params,HttpServletRequest request)throws MyRuntimeException{
		this.getSessionAccount(params, request);
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
	public @ResponseBody Object get(@RequestParam("id")String id){
		return this.getService().getEntityMap(id);
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public Object getList(@RequestBody Map<Object,Object>params,HttpServletRequest request,HttpServletResponse response){
		this.getSessionAccount(params, request);
		return getService().getList(params);
	}
	
	@ExceptionHandler  
	@ResponseBody
    public Object exp(HttpServletRequest request, Exception ex) {  
		Map<Object,Object> res = new HashMap<>();
		res.put("success", false);
        res.put("msg", ex.getMessage());
        return res;
    } 
	/**
	 * @param params
	 * @param request
	 */
	protected void getSessionAccount(Map<Object,Object>params,HttpServletRequest request){
		if(null == request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT)){
			throw new MyRuntimeException("用户未登录,请重新登录.");
		}
		params.put(SessionConstants.LOGIN_ACCOUNT_ID, request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT_ID));
		params.put(SessionConstants.LOGIN_ACCOUNT_NAME, request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT_NAME));
	}
	
	/**
	 * @param params
	 * @param request
	 */
	protected void getSessionAccountWithParty(Map<Object,Object>params,HttpServletRequest request){
		Object o = request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT);
		if(null == o){
			throw new MyRuntimeException("用户未登录,请重新登录.");
		}
		@SuppressWarnings("unchecked")
		Map<Object,Object> account = (Map<Object,Object>)o;
		params.put(SessionConstants.LOGIN_ACCOUNT_ID, request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT_ID));
		params.put(SessionConstants.LOGIN_ACCOUNT_NAME, request.getSession().getAttribute(SessionConstants.LOGIN_ACCOUNT_NAME));
		params.put(SessionConstants.LOGIN_ACCOUNT_PARTY_ID, account.get("partyId"));
		params.put(SessionConstants.LOGIN_ACCOUNT_PARTY_NAME, account.get("partyName"));
	}
	
	protected abstract AbstractService<T> getService();
}
