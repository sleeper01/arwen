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
import com.dm.bizs.investigate.domain.model.Topic;
import com.dm.bizs.investigate.service.TopicService;
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
@RequestMapping("/topic")
public class TopicController extends AbstractController<Topic> {

	@Autowired
	private TopicService service;
	
	/**
	 * @Title: addTopic
	 * @Description: 
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping("/addQuestion")
	@ResponseBody
	public Object addQuestion(@RequestBody Map<Object,Object> params,HttpServletRequest request){
		this.getSessionAccount(params, request);
		service.addQuestion(params);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Topic> getService() {
		return service;
	}
}
