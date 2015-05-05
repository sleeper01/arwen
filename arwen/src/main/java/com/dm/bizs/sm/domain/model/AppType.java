/**
 * 
 */
package com.dm.bizs.sm.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "tbl_sm_app_type")
public class AppType extends StatusEntity {

	@Column(unique = true)
	private String name;

	@Column
	private Integer sort;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "appType")
	private Set<App> apps = new HashSet<>();
	
	@Column
	private String iconClass;

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
	 * @return the iconClass
	 */
	public String getIconClass() {
		return iconClass;
	}

	/**
	 * @param params
	 */
	public void addApp(Map<Object,Object>params){
		App app = new App();
		app.caseCade(params);
		this.addApp(app);
	}
	
	/**
	 * @param app
	 */
	public void addApp(App app){
		if(app != null){
			app.setAppType(this);
			this.apps.add(app);
		}
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		res.put("sort", this.sort);
		res.put("iconClass", this.iconClass);
		List<Map<Object,Object>> apps = new ArrayList<>();
		for(App app : this.apps){
			apps.add(app.toMap());
		}
		res.put("apps", apps);
		return res;
	}
	
	public Map<Object, Object> toEnableMap() {
		if(Status.ENABLE.equals(this.getStatus())){
			Map<Object, Object> res = super.toMap();
			res.put("name", this.name);
			res.put("sort", this.sort);
			res.put("iconClass", this.iconClass);
			List<Map<Object,Object>> apps = new ArrayList<>();
			for(App app : this.apps){
				Map<Object, Object> _app = app.toEnableMap();
				if(_app != null){
					apps.add(_app);
				}
			}
			res.put("apps", apps);
			return res;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
		this.setSort(ParamUtils.getInteger(params, "sort", 0));
		this.setIconClass(ParamUtils.getString(params, "iconClass", ""));
	}
	
	/**
	 * @return
	 */
	public AppType pureEntity(Set<App> apps){
		AppType type = new AppType();
		type.setId(this.getId());
		type.setName(this.name);
		type.setSort(this.sort);
		type.setIconClass(this.iconClass);
		type.setApps(apps);
		return type;
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

	/**
	 * @param iconClass the iconClass to set
	 */
	protected void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
