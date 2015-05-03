/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.AppTypeService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class AppTypeServiceTest extends SpringTestUnitSupport {

	@Autowired
	private AppTypeService service;
	
	public void create(){
		service.create(new ParamUtils.ParamBuilder()
			.set("name", "系统管理")
			.build());
	}
	
	@Test
	public void addApp(){
		service.addApp(new ParamUtils.ParamBuilder()
			.set("name", "角色管理")
			.set("id", "402881e64cfd2a02014cfd2a09f70000")
			.build());
	}
}
