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
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_role")
public class Role extends StatusEntity {

	@Column(unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,targetEntity=Account.class)
	private Set<Account> accounts = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,targetEntity=App.class)
	private Set<App> apps = new HashSet<>();
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the accounts
	 */
	public Set<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * @return the apps
	 */
	public Set<App> getApps() {
		return apps;
	}

	/**
	 * @param account
	 */
	public void addAccount(Account account){
		if(account != null){
			this.accounts.add(account);
			account.getRoles().add(this);
		}
	}
	
	/**
	 * @param account
	 */
	public void removeAccount(Account account){
		if(account != null){
			if(this.accounts.contains(account)){
				this.accounts.remove(account);
				account.removeRole(this);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object>res = super.toMap();
		res.put("name", this.getName());
		res.put("accounts", SerializeUtils.convertEntitiesToMaps(this.accounts));
		res.put("apps", SerializeUtils.convertEntitiesToMaps(this.apps));
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
	
	/**
	 * @param app
	 */
	public void addApp(App app){
		if(app != null){
			this.apps.add(app);
		}
	}
	
	/**
	 * @param app
	 */
	public void removeApp(App app){
		if(app != null){
			this.apps.remove(app);
		}
	}
	
	/**
	 * 
	 */
	public void removeAllApps(){
		this.apps.clear();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	protected void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @param apps the apps to set
	 */
	protected void setApps(Set<App> apps) {
		this.apps = apps;
	}
}
