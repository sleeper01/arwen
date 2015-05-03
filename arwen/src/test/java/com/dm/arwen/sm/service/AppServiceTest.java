/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.AppService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class AppServiceTest extends SpringTestUnitSupport {

	@Autowired
	private AppService service;
	
	@Test
	public void updateTest(){
		service.update(new ParamUtils.ParamBuilder().set("id", "402881e64cfd2d4c014cfd2d54a80000").build());
	}
}
