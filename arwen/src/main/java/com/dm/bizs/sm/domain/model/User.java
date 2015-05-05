/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "tbl_sm_user")
public class User extends StatusEntity {

	@Column(unique=true)
	private String name;
	
	@Column
	private String idCardNum;
	
	@Column
	private String num;
	
	@Column
	private String phoneNum;
	
	@Column
	private String email;
	
	@Column
	private String desp;
	
	@ManyToOne(targetEntity = Dept.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "deptId")
	private Dept dept;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
	private Account account;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the idCardNum
	 */
	public String getIdCardNum() {
		return idCardNum;
	}


	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the desp
	 */
	public String getDesp() {
		return desp;
	}

	/**
	 * @return the dept
	 */
	public Dept getDept() {
		return dept;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		res.put("idCardNum", this.idCardNum);
		res.put("num", this.num);
		res.put("phoneNum", this.phoneNum);
		res.put("email", this.email);
		res.put("desp", this.desp);
		return res;
	}
	
	public Map<Object,Object> toNode(){
		Map<Object,Object> res = new HashMap<>();
		res.put("id", this.getId());
		res.put("text", this.name);
		res.put("children", true);
		Map<Object,Object> li_attr = new HashMap<Object,Object>();
		li_attr.put("type", "user");
		res.put("li_attr", li_attr);
		return res;
	}
	
	/**
	 * @param account
	 */
	public void updateAccount(Account account){
		if(account != null){
			this.setAccount(account);
			account.setUser(this);
		}
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
		this.setNum(ParamUtils.getString(params, "num", ""));
		this.setPhoneNum(ParamUtils.getString(params, "phoneNum", ""));
		this.setEmail(ParamUtils.getString(params, "email", ""));
		this.setDesp(ParamUtils.getString(params, "desp", ""));
		this.setIdCardNum(ParamUtils.getString(params, "idCardNum", ""));
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param idCardNum the idCardNum to set
	 */
	protected void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}


	/**
	 * @param num the num to set
	 */
	protected void setNum(String num) {
		this.num = num;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	protected void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @param email the email to set
	 */
	protected void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param desp the desp to set
	 */
	protected void setDesp(String desp) {
		this.desp = desp;
	}

	/**
	 * @param dept the dept to set
	 */
	protected void setDept(Dept dept) {
		this.dept = dept;
	}

	/**
	 * @param account the account to set
	 */
	protected void setAccount(Account account) {
		this.account = account;
	}
}
