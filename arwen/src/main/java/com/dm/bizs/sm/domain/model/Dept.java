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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_dept")
public class Dept extends StatusEntity {

	@Column(unique=true)
	private String name;

	@ManyToOne(targetEntity = Dept.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Dept parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Dept> children = new HashSet<>();

	@ManyToOne(targetEntity = Party.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "partyId")
	private Party party;

	@OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<User> users = new HashSet<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the parent
	 */
	public Dept getParent() {
		return parent;
	}

	/**
	 * @return the children
	 */
	public Set<Dept> getChildren() {
		return children;
	}

	/**
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object,Object> res = super.toMap();
		res.put("name", this.name);
		return res;
	}
	
	public Map<Object,Object> toNode(){
		Map<Object,Object> res = new HashMap<>();
		res.put("id", this.getId());
		res.put("text", this.name);
		res.put("children", true);
		Map<Object,Object> li_attr = new HashMap<Object,Object>();
		li_attr.put("type", "dept");
		res.put("li_attr", li_attr);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
	}
	
	public void addChild(Map<Object, Object> params){
		Dept dept = new Dept();
		dept.caseCade(params);
		this.addChild(dept);
	}
	
	public void addChild(Dept child){
		if(child != null){
			child.setParent(this);
			this.children.add(child);
		}
	}
	
	public void addUser(Map<Object,Object> params){
		User user = new User();
		user.caseCade(params);
		this.addUser(user);
	}
	
	public void addUser(User user){
		if(user != null){
			user.setDept(this);
			this.users.add(user);
		}
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param parent the parent to set
	 */
	protected void setParent(Dept parent) {
		this.parent = parent;
	}

	/**
	 * @param children the children to set
	 */
	protected void setChildren(Set<Dept> children) {
		this.children = children;
	}

	/**
	 * @param party the party to set
	 */
	protected void setParty(Party party) {
		this.party = party;
	}

	/**
	 * @param users the users to set
	 */
	protected void setUsers(Set<User> users) {
		this.users = users;
	}
}
