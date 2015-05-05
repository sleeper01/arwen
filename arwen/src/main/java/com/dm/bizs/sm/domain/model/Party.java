/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_party")
public class Party extends StatusEntity {

	@Column(unique=true)
	private String name;

	@Column
	private String logoUrl;

	@Column
	private String desp;

	@OneToMany(mappedBy = "party", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Dept> depts = new HashSet<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @return the desp
	 */
	public String getDesp() {
		return desp;
	}

	/**
	 * @return the depts
	 */
	public Set<Dept> getDepts() {
		return depts;
	}
	
	/**
	 * @param params
	 */
	public void addDept(Map<Object,Object> params){
		Dept dept = new Dept();
		dept.caseCade(params);
		this.addDept(dept);
	}
	
	/**
	 * @param dept
	 */
	public void addDept(Dept dept){
		if(dept != null){
			dept.setParty(this);
			this.depts.add(dept);
		}
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		res.put("logoUrl", this.logoUrl);
		res.put("desp", this.desp);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
		this.setLogoUrl(ParamUtils.getString(params, "logoUrl", ""));
		this.setDesp(ParamUtils.getString(params, "desp", ""));
	}
	
	public Map<Object,Object> toNode(){
		Map<Object,Object> res = new HashMap<>();
		res.put("id", this.getId());
		res.put("text", this.name);
		res.put("children", true);
		Map<Object,Object> li_attr = new HashMap<Object,Object>();
		li_attr.put("type", "party");
		res.put("li_attr", li_attr);
		return res;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param logoUrl
	 *            the logoUrl to set
	 */
	protected void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @param desp
	 *            the desp to set
	 */
	protected void setDesp(String desp) {
		this.desp = desp;
	}

	/**
	 * @param depts the depts to set
	 */
	protected void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}
}
