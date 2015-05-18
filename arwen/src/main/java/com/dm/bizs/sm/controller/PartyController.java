/**
 * 
 */
package com.dm.bizs.sm.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dm.bizs.sm.domain.model.Party;
import com.dm.bizs.sm.service.DeptService;
import com.dm.bizs.sm.service.PartyService;
import com.dm.common.constants.ResultConstants;
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
	@Autowired
	private DeptService deptService;
	
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
			a_attr.put("type", "super");
			node.put("li_attr", a_attr);
			res.add(node);
		}else if("super".equals(type)){
			res.addAll(service.getList(map));
		}else if("party".equals(type)){
			map.put("partyId", parentId);
			res.addAll(deptService.getList(map));
		}else if("dept".equals(type)){
			map.put("parentId", parentId);
			res.addAll(deptService.getList(map));
		}
		return res;
	}
	
	@RequestMapping("/getPartyById")
	@ResponseBody
	public Object getPartyById(@RequestParam("id")String id){
		return service.getEntityMap(id);
	}
	
	@RequestMapping("/addDept")
	@ResponseBody
	public Object addDept(@RequestBody Map<Object,Object> params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		service.addDept(params);
		Map<Object,Object> res = new HashMap<Object,Object>();
		res.put(ResultConstants.SUCCESS, true);
		return res;
	}
	
	@RequestMapping("/getPartyByNameLike")
	@ResponseBody
	public Object getPartyByNameLike(@RequestParam("q") String q,@RequestParam("page_limit") Integer page_limit,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		q = new String(q.getBytes("GBK"),"UTF-8");
		return service.getPartyByNameLike(q, page_limit);
	}
	
	@RequestMapping("/getParties")
	@ResponseBody
	public Object getParties(@RequestBody Map<Object,Object> params,HttpServletRequest request,HttpServletResponse response){
		super.getSessionAccount(params, request);
		Map<Object,Object> map = new ParamUtils.ParamBuilder().set("status", Status.ENABLE).build();
		return service.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.controller.AbstractController#getService()
	 */
	@Override
	protected AbstractService<Party> getService() {
		return service;
	}

}
