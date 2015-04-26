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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_app_type")
public class AppType extends StatusEntity {

	@Column(unique = true)
	private String name;

	@Column
	private Integer sort;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appType")
	private Set<App> apps = new HashSet<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @return the apps
	 */
	public Set<App> getApps() {
		return apps;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	protected void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @param apps
	 *            the apps to set
	 */
	protected void setApps(Set<App> apps) {
		this.apps = apps;
	}
}
