/**
 * 
 */
package com.dm.bizs.investigate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.bizs.investigate.domain.model.Option;
import com.dm.bizs.investigate.service.OptionService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/option")
public class OptionController extends AbstractController<Option> {

	@Autowired
	private OptionService service;
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Option> getService() {
		return service;
	}
}
