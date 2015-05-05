/**
 * 
 */
package com.dm.arwen.sm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dm.bizs.sm.service.DeptService;
import com.dm.common.service.SpringTestUnitSupport;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
public class DeptServiceTest extends SpringTestUnitSupport {

	@Autowired
	private DeptService service;
	
	
	public void update(){
		service.update(new ParamUtils.ParamBuilder()
			.set("id", "402881e44d2128bb014d2128c4e90000")
			.set("name", "公共部门")
			.set("status", "ENABLE")
			.build());
	}
	
	
	public void addChild(){
		service.addChild(new ParamUtils.ParamBuilder()
		.set("parentId", "402881e44d2128bb014d2128c4e90000")
		.set("name", "人力资源")
		.set("status", "ENABLE")
		.build());
	}
	
	@Test
	public void addUser(){
		service.addUser(new ParamUtils.ParamBuilder()
		.set("deptId", "402881e44d214dd6014d214ddf5d0000")
		.set("name", "张三")
		.set("status", "ENABLE")
		.set("idCardNum", "350888XXXXXXXX9999")
		.set("num", "07001")
		.set("email", "zhangs@126.com")
		.set("phoneNum", "13099891111")
		.set("desp", "北方来的")
		.build());
	}
}
