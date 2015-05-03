/**
 * 
 */
package com.dm.bizs.sm.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dm.bizs.sm.dao.AccountDao;
import com.dm.bizs.sm.dao.RoleDao;
import com.dm.bizs.sm.domain.model.Account;
import com.dm.bizs.sm.domain.model.App;
import com.dm.bizs.sm.domain.model.AppType;
import com.dm.bizs.sm.domain.model.Role;
import com.dm.common.dao.AbstractDao;
import com.dm.common.domain.model.Constant;
import com.dm.common.domain.model.IEntity;
import com.dm.common.domain.model.StatusEntity.Status;
import com.dm.common.exception.MyRuntimeException;
import com.dm.common.service.AbstractService;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.PwdUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Service("accountService")
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class AccountService extends AbstractService<Account> {

	@Autowired
	private AccountDao dao;
	
	@Autowired
	private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#create(java.util.Map)
	 */
	@Override
	public void create(Map<Object, Object> params) throws MyRuntimeException {
		Account account = new Account();
		account.caseCade(params);
		super.create(account);
	}

	/**
	 * @param params
	 */
	public void updatePsw(Map<Object, Object> params){
		Account account = this.getEntity(params);
		account.updatePwd(params);
		super.update(account);
	}
	
	/**
	 * @param params
	 */
	public void addRole(Map<Object, Object> params){
		Account account = this.getEntity(params);
		Role role = roleDao.get(ParamUtils.getString(params, "roleId", ""));
		if(role == null){
			throw new MyRuntimeException(String.format("id为%s的角色不存在.", ParamUtils.getString(params, "roleId", "")));
		}
		account.addRole(role);
		super.update(account);
	}
	
	/**
	 * @param params
	 */
	public void removeRole(Map<Object, Object> params){
		Account account = this.getEntity(params);
		Role role = roleDao.get(ParamUtils.getString(params, "roleId", ""));
		if(role == null){
			throw new MyRuntimeException(String.format("id为%s的角色不存在.", ParamUtils.getString(params, "roleId", "")));
		}
		account.removeRole(role);
		super.update(account);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws MyRuntimeException {
		Account account = this.get(id);
		if(account != null){
			account.removeAllRoles();
			dao.delete(account);
		}
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getList(java.util.Map)
	 */
	@Override
	public Collection<Map<Object, Object>> getList(Map<Object, Object> params) {
		return SerializeUtils.convertEntitiesToMaps(dao.findAll());
	}
	
	/**
	 * @param params
	 * @return
	 */
	public Object getAccountAuth(Map<Object, Object> params){
		Account account = super.getEntity(params);
		Set<Role> roles = account.getRoles();
		Set<AppType> appTypes = this.initSortedSet();
		Set<App> apps = this.initSortedAppSet();
		for(Role role : roles){
			if(Status.DISABLE.equals(role.getStatus()))
				continue;
			if(!CollectionUtils.isEmpty(role.getApps())){
				for(App app : role.getApps()){
					if(Status.DISABLE.equals(app.getStatus()))
						continue;
					AppType type = this.getEntityFromSet(appTypes, app.getAppType().getId());
					if(type == null){
						type = app.getAppType().pureEntity(this.initSortedAppSet());
						appTypes.add(type);
					}
					if(Constant.YES.equals(app.getShowInMenus())){
						type.addApp(app.pureEntity());
					}
					apps.add(app.pureEntity());
				}
			}
		}
		Map<Object,Object> res = new HashMap<>();
		res.put("authTree", SerializeUtils.convertEntitiesToMaps(appTypes));
		res.put("authApps", SerializeUtils.convertEntitiesToMaps(apps));
		return res;
	}
	
	/**
	 * @param params
	 * @return
	 */
	public Map<Object,Object> login(Map<Object,Object> params){
		Map<Object,Object> res = new HashMap<>();
		Account account = dao.getAccount(new ParamUtils.ParamBuilder()
			.set("name", ParamUtils.getString(params, "name", ""))
			.set("status", Status.ENABLE)
			.build());
		if(account == null){
			res.put("msg", "账号不存在.");
			res.put("success", false);
			return res;
		}
		if(!account.getPwd().equals(PwdUtils.parseStrToMd5U32(ParamUtils.getString(params, "pwd", "")))){
			res.put("msg", "密码不正确.");
			res.put("success", false);
			return res;
		}
		res.put("success", true);
		res.put("account", account.toMap());
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#update(java.util.Map)
	 */
	@Override
	public void update(Map<Object, Object> params) throws MyRuntimeException {
		Account account = super.getEntity(params);
		account.caseCade(params);
		super.update(account);
	}

	/* (non-Javadoc)
	 * @see com.dm.common.service.AbstractService#getDao()
	 */
	@Override
	protected AbstractDao<Account> getDao() {
		return dao;
	}
	
	/**
	 * @return
	 */
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
	
	private Set<App> initSortedAppSet(){
		Set<App> apps = new TreeSet<>(new Comparator<App>() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(App o1, App o2) {
				return o1.getSort().compareTo(o2.getSort());
			}
		});
		return apps;
	}
	
	/**
	 * @param entities
	 * @param id
	 * @return
	 */
	private <T extends IEntity> T getEntityFromSet(Set<T> entities,String id){
		for(T t : entities){
			if(id.equals(t.getId()))
				return t;
		}
		return null;
	}
}
