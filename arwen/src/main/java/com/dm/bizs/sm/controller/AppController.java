/**
 * 
 */
package com.dm.bizs.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.bizs.sm.domain.model.App;
import com.dm.bizs.sm.service.AppService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/app")
public class AppController extends AbstractController<App> {

	@Autowired
	private AppService service;
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<App> getService() {
		return service;
	}
}
