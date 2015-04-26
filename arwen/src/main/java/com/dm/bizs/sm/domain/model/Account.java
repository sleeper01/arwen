/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.PwdUtils;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_account")
public class Account extends StatusEntity {

	@Column(unique = true)
	private String name;

	@Column
	private String pwd;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "accounts", cascade = CascadeType.ALL)
	private Set<Role> roles = new HashSet<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
		this.setPwd(PwdUtils.parseStrToMd5U32(ParamUtils.getString(params, "pwd", "")));
	}
	
	/**
	 * @param params
	 */
	public void updatePwd(Map<Object, Object> params){
		if(!this.pwd.equals(PwdUtils.parseStrToMd5U32(ParamUtils.getString(params, "oldPwd", "")))){
			throw new MyRuntimeException("原密码不正确.");
		}
		this.setPwd(PwdUtils.parseStrToMd5U32(ParamUtils.getString(params, "pwd", "")));
	}
	
	/**
	 * @param role
	 */
	public void addRole(Role role){
		if(role != null){
			this.roles.add(role);
			role.getAccounts().add(this);
		}
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	protected void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	protected void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
