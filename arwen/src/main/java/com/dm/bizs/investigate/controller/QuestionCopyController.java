/**
 * 
 */
package com.dm.bizs.investigate.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.investigate.domain.model.QuestionCopy;
import com.dm.bizs.investigate.service.QuestionCopyService;
import com.dm.common.constants.ResultConstants;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/questioncopy")
public class QuestionCopyController extends AbstractController<QuestionCopy> {

	@Autowired
	private QuestionCopyService service;
	
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Object delete(@RequestParam("id")String id){
		Map<Object,Object> res = new HashMap<>();
		service.delete(id);
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<QuestionCopy> getService() {
		return service;
	}
}
