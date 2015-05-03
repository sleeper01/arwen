/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.AccountService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class AccountServiceTest extends SpringTestUnitSupport{

	@Autowired
	private AccountService service;
	
	public void create(){
		service.create(new ParamUtils.ParamBuilder()
			.set("name", "admin")
			.set("pwd", "000000")
			.build());
	}
	
	public void removeRole(){
		service.removeRole(new ParamUtils.ParamBuilder()
		.set("roleId", "f8a2d2714cf4adde014cf4ade5be0000")
		.set("id", "f8a2d2714cf4a9db014cf4a9e7aa0000")
		.build());
	}
	
	public void addRole(){
		service.addRole(new ParamUtils.ParamBuilder()
		.set("roleId", "402881e84d005eea014d005ef20e0000")
		.set("id", "f8a2d2714cf4dfc1014cf4dfc9070000")
		.build());
	}
	
	public void remove(){
		service.delete("f8a2d2714cf4de10014cf4de17170000");
	}
	
	@Test
	public void test(){
		System.out.println(service.getAccountAuth(new ParamUtils.ParamBuilder()
		.set("id", "f8a2d2714cf4dfc1014cf4dfc9070000")
		.build()));
	}
}
