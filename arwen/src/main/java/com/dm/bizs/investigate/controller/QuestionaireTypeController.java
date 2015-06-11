/**
 * 
 */
package com.dm.bizs.investigate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.bizs.investigate.domain.model.QuestionaireType;
import com.dm.bizs.investigate.service.QuestionaireTypeService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/questionaireType")
public class QuestionaireTypeController extends AbstractController<QuestionaireType> {

	@Autowired
	private QuestionaireTypeService service;
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<QuestionaireType> getService() {
		return service;
	}
}
