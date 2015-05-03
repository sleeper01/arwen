/**
 * 
 */
package com.dm.bizs.sm.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.sm.dao.AppTypeDao;
import com.dm.bizs.sm.domain.model.AppType;
import com.dm.common.dao.AbstractDao;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 * 
 */
@Service("appTypeService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class AppTypeService extends AbstractService<AppType> {
	
	@Autowired
	private AppTypeDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		AppType type = new AppType();
		type.caseCade(params);
		super.create(type);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		AppType type = super.getEntity(params);
		type.caseCade(params);
		super.update(type);
	}

	/**
	 * @param params
	 */
	public void addApp(Map<Object, Object> params){
		AppType type = super.get(ParamUtils.getString(params, "appTypeId", ""));
		if(type == null)
			throw new MyRuntimeException(String.format("id 为：%s的实体不存在.", ParamUtils.getString(params, "appTypeId", "")));
		type.addApp(params);
		super.update(type);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		Set<AppType> set = this.initSortedSet();
		set.addAll(dao.getAppTypes());
		return SerializeUtils.convertEntitiesToMaps(set);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<AppType> getDao() {
		return dao;
	}
	
	private Set<AppType> initSortedSet(){
		Set<AppType> appTypes = new TreeSet<>(new Comparator<AppType>() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(AppType o1, AppType o2) {
				return o1.getSort().compareTo(o2.getSort());
			}
		});
		return appTypes;
	}
}
