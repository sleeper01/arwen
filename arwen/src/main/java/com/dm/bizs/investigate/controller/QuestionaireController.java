/**
 * 
 */
package com.dm.bizs.investigate.controller;

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

import com.dm.bizs.investigate.domain.model.Questionaire;
import com.dm.bizs.investigate.service.QuestionaireService;
import com.dm.common.constants.ResultConstants;
import com.dm.common.controller.AbstractController;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/questionaire")
public class QuestionaireController extends AbstractController<Questionaire> {

	@Autowired
	private QuestionaireService service;
	
	@RequestMapping("/getPageList")
	@ResponseBody
	public Object getPageList(@RequestBody Map<Object,Object>params,@RequestParam("page")Integer page,@RequestParam("pageSize")Integer pageSize, HttpServletRequest request,HttpServletResponse response){
		this.getSessionAccount(params, request);
		return service.getPageList(params, page, pageSize);
	}
	
	@RequestMapping("/persist")
	public @ResponseBody Object persist(@RequestBody Map<Object,Object> params,HttpServletRequest request)throws MyRuntimeException{
		this.getSessionAccount(params, request);
		Map<Object,Object> res = new HashMap<Object,Object>();
		if(!"".equals(ParamUtils.getString(params, "id", ""))){
			res.put("data", service.update1(params));
		}else{
			res.put("data", service.create1(params));
		}
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Questionaire> getService() {
		return service;
	}
}
