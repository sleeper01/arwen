/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_sm_app")
public class App extends StatusEntity {

	@Column(unique = true)
	private String name;

	@Column
	private String stateName;

	@Column
	private String templateUrl;

	@Column
	private String ctlName;

	@Column
	private String ctlUrl;

	@Column
	private Integer sort;

	@ManyToOne(targetEntity = AppType.class)
	private AppType appType;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @return the templateUrl
	 */
	public String getTemplateUrl() {
		return templateUrl;
	}

	/**
	 * @return the ctlName
	 */
	public String getCtlName() {
		return ctlName;
	}

	/**
	 * @return the ctlUrl
	 */
	public String getCtlUrl() {
		return ctlUrl;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @return the appType
	 */
	public AppType getAppType() {
		return appType;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	protected void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @param templateUrl
	 *            the templateUrl to set
	 */
	protected void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	/**
	 * @param ctlName
	 *            the ctlName to set
	 */
	protected void setCtlName(String ctlName) {
		this.ctlName = ctlName;
	}

	/**
	 * @param ctlUrl
	 *            the ctlUrl to set
	 */
	protected void setCtlUrl(String ctlUrl) {
		this.ctlUrl = ctlUrl;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	protected void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @param appType
	 *            the appType to set
	 */
	protected void setAppType(AppType appType) {
		this.appType = appType;
	}
}
