/**
 * 
 */
package com.dm.bizs.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.bizs.sm.domain.model.User;
import com.dm.bizs.sm.service.UserService;
import com.dm.common.controller.AbstractController;
import com.dm.common.service.AbstractService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController<User> {

	@Autowired
	private UserService service;
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<User> getService() {
		return service;
	}
}
