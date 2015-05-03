/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dm.common.domain.model.Constant;
import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

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
	
	@Column
	private String iconClass;

	@ManyToOne(targetEntity = AppType.class,cascade=CascadeType.PERSIST)
	private AppType appType;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Constant showMenus = Constant.YES;

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
	 * @return the showMenus
	 */
	public Constant getShowMenus() {
		return showMenus;
	}

	/**
	 * @return the iconClass
	 */
	public String getIconClass() {
		return iconClass;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		res.put("stateName", this.stateName);
		res.put("templateUrl", this.templateUrl);
		res.put("ctlName", this.ctlName);
		res.put("ctlUrl", this.ctlUrl);
		res.put("iconClass", this.iconClass);
		res.put("sort", this.sort);
		res.put("showMenus", this.showMenus);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", name));
		this.setStateName(ParamUtils.getString(params, "stateName", ""));
		this.setTemplateUrl(ParamUtils.getString(params, "templateUrl", ""));
		this.setCtlName(ParamUtils.getString(params, "ctlName", ""));
		this.setCtlUrl(ParamUtils.getString(params, "ctlUrl", ""));
		this.setSort(ParamUtils.getInteger(params, "sort", 0));
		this.setShowMenus(ParamUtils.getEnum(params, "showInMenus", Constant.values()));
		this.setIconClass(ParamUtils.getString(params, "iconClass", ""));
	}
	
	/**
	 * @return
	 */
	public App pureEntity(){
		App app = new App();
		app.setId(this.getId());
		app.setName(this.name);
		app.setStateName(this.stateName);
		app.setTemplateUrl(this.templateUrl);
		app.setCtlName(this.ctlName);
		app.setCtlUrl(this.ctlUrl);
		app.setSort(this.sort);
		app.setShowMenus(this.showMenus);
		app.setIconClass(this.iconClass);
		return app;
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

	/**
	 * @param showMenus the showMenus to set
	 */
	protected void setShowMenus(Constant showMenus) {
		this.showMenus = showMenus;
	}

	/**
	 * @param iconClass the iconClass to set
	 */
	protected void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
