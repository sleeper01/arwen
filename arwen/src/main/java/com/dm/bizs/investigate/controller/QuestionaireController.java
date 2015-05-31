/**
 * 
 */
package com.dm.bizs.investigate.controller;

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
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

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
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Questionaire> getService() {
		return service;
	}
}
