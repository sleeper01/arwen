/**
 * 
 */
package com.dm.bizs.investigate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.bizs.investigate.domain.model.QuestionLabel;
import com.dm.bizs.investigate.service.QuestionLabelService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/ql")
public class QuestionLabelController extends AbstractController<QuestionLabel> {
	@Autowired
	private QuestionLabelService service;
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<QuestionLabel> getService() {
		return service;
	}

}
