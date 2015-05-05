/**
 * 
 */
package com.dm.bizs.sm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.sm.domain.model.Party;
import com.dm.bizs.sm.service.PartyService;
import com.dm.common.controller.AbstractController;
import com.dm.common.domain.model.StatusEntity.Status;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/party")
public class PartyController extends AbstractController<Party> {

	@Autowired
	private PartyService service;
	
	@RequestMapping("/getNodes")
	@ResponseBody
	public Object getNodes(@RequestParam("parentId")String parentId,@RequestParam("type")String type,HttpServletRequest request,HttpServletResponse response){
		List<Map<Object,Object>> res = new ArrayList<Map<Object,Object>>();
		Map<Object,Object> map = new ParamUtils.ParamBuilder().set("status", Status.ENABLE).build();
		if("root".equals(type)){
			Map<Object,Object> node = new HashMap<Object,Object>();
			node.put("id", "-1");
			node.put("text", "组织机构");
			node.put("children", true);
			Map<Object,Object> a_attr = new HashMap<Object,Object>();
			a_attr.put("type", "party");
			node.put("li_attr", a_attr);
			res.add(node);
		}else if("party".equals(type)){
			res.addAll(service.getList(map));
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Party> getService() {
		return service;
	}

}
