/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_role")
public class Role extends StatusEntity {

	@Column(unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<>();

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
	 * @param account
	 */
	public void addAccount(Account account){
		if(account != null){
			this.accounts.add(account);
			account.getRoles().add(this);
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
	 * @param accounts
	 *            the accounts to set
	 */
	protected void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
