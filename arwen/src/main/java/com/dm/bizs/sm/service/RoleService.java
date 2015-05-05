/**
 * 
 */
package com.dm.bizs.sm.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dm.bizs.sm.dao.AccountDao;
import com.dm.bizs.sm.dao.AppDao;
import com.dm.bizs.sm.dao.RoleDao;
import com.dm.bizs.sm.domain.model.Account;
import com.dm.bizs.sm.domain.model.App;
import com.dm.bizs.sm.domain.model.Role;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.GenericObjectCallBack;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Service("roleService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class RoleService extends AbstractService<Role> {

	@Autowired
	private RoleDao dao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AppDao appDao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		Role role = new Role();
		role.caseCade(params);
		super.create(role);
	}

	/**
	 * @param params
	 */
	public void addAccount(Map<Object, Object> params){
		Role role = this.getEntity(params);
		Account account = accountDao.get(ParamUtils.getString(params, "accountId", ""));
		if(account == null){
			throw new MyRuntimeException(String.format("id为%s的账号不存在.", ParamUtils.getString(params, "accountId", "")));
		}
		role.addAccount(account);
		super.update(role);
	}
	

	/**
	 * @param params
	 */
	public void removeAccount(Map<Object, Object> params){
		Role role = this.getEntity(params);
		Account account = accountDao.get(ParamUtils.getString(params, "accountId", ""));
		if(account == null){
			throw new MyRuntimeException(String.format("id为%s的账号不存在.", ParamUtils.getString(params, "accountId", "")));
		}
		role.removeAccount(account);
		super.update(role);
	}
	
	/**
	 * @param params
	 */
	public void addApp(Map<Object, Object> params){
		Role role = this.getEntity(params);
		App app = appDao.get(ParamUtils.getString(params, "appId", ""));
		if(app == null){
			throw new MyRuntimeException(String.format("id为%s的应用不存在.", ParamUtils.getString(params, "appId", "")));
		}
		role.addApp(app);
		super.update(role);
	}
	
	/**
	 * @param params
	 */
	public void addApps(Map<Object, Object> params){
		Role role = this.getEntity(params);
		role.removeAllApps();
		Set<App> apps = ParamUtils.getObjects(params, "apps", new GenericObjectCallBack<App>() {

			/* (non-Javadoc)
			 * @see com.dm.common.domain.model.GenericObjectCallBack#genericObjects(java.util.Map)
			 */
			@Override
			public App genericObjects(Map<Object, Object> params) {
				return appDao.get(ParamUtils.getString(params, "id", ""));
			}
		});
		for(App app : apps){
			role.addApp(app);
		}
		super.update(role);
	}

	/**
	 * @param params
	 */
	public void removeApp(Map<Object, Object> params){
		Role role = this.getEntity(params);
		App app = appDao.get(ParamUtils.getString(params, "appId", ""));
		if(app == null){
			throw new MyRuntimeException(String.format("id为%s的应用不存在.", ParamUtils.getString(params, "appId", "")));
		}
		role.removeApp(app);
		super.update(role);
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return SerializeUtils.convertEntitiesToMaps(dao.findAll());
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getEntityMap(java.lang.String)
	 */
	@Override
	public Map<Object, Object> getEntityMap(String id) {
		return super.getEntityMap(id);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Role> getDao() {
		return dao;
	}

}
