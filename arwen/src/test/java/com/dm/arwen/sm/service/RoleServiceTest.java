/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.RoleService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class RoleServiceTest extends SpringTestUnitSupport {

	@Autowired
	private RoleService service;
	
	public void create(){
		service.create(new ParamUtils.ParamBuilder()
			.set("name", "系统开发人员")
			.build());	
	}
	
	public void addAccount(){
		service.addAccount(new ParamUtils.ParamBuilder()
			.set("id", "402881e84d005384014d00538b730000")
			.set("accountId", "f8a2d2714cf4dfc1014cf4dfc9070000")
			.build());
	}
	
	public void removeAccount(){
		service.removeAccount(new ParamUtils.ParamBuilder()
			.set("id", "402881e84d005384014d00538b730000")
			.set("accountId", "f8a2d2714cf4dfc1014cf4dfc9070000")
			.build());
	}
	
	public void remove(){
		service.delete("402881e84d005384014d00538b730000");
	}
	@Test
	public void addApp(){
		service.addApp(new ParamUtils.ParamBuilder()
			.set("id", "402881e84d005eea014d005ef20e0000")
			.set("appId", "402881e64cfd2d4c014cfd2d54a80000")
			.build());
	}
	
	
	public void removeApp(){
		service.removeApp(new ParamUtils.ParamBuilder()
			.set("id", "402881e84d005eea014d005ef20e0000")
			.set("appId", "402881e64cfd2d4c014cfd2d54a80000")
			.build());
	}
}
