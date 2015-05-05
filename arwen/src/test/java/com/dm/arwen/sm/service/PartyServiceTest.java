/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.PartyService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class PartyServiceTest extends SpringTestUnitSupport {

	@Autowired
	private PartyService service;
	
	
	public void create(){
		service.create(new ParamUtils.ParamBuilder()
			.set("name", "软媒科技")
			.set("logoUrl", "D:\\annex\\logo.jpg")
			.set("desp", "厦门公司")
			.build());
	}
	
	@Test
	public void addDept(){
		service.addDept(new ParamUtils.ParamBuilder()
		.set("partyId", "402881e44d2124a4014d2124ab860000")
		.set("name", "人事部")
		.set("status", "ENABLE")
		.build());
	}
}
