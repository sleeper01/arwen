/**
 * 
 */
package com.dm.common.domain.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.dm.common.constants.SessionConstants;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity{

	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;
	
	@Column
	private String creatorId;
	
	@Column
	private Date createDate;
	
	@Column
	private String updaterId;
	
	@Column
	private Date updateDate;

	public String getId() {
		return this.id;
	}

	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return the updaterId
	 */
	public String getUpdaterId() {
		return updaterId;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.IEntity#toMap()
	 */
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("id", id);
		return res;
	}
	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.IEntity#initCreateInfo(java.util.Map)
	 */
	protected void initCreateInfo(Map<Object, Object> params) {
		this.setCreatorId(ParamUtils.getString(params, SessionConstants.LOGIN_ACCOUNT_ID, ""));
		this.setCreateDate(new Date());
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.IEntity#initUpdateInfo(java.util.Map)
	 */
	protected void initUpdateInfo(Map<Object, Object> params) {
		this.setUpdaterId(ParamUtils.getString(params, SessionConstants.LOGIN_ACCOUNT_ID, ""));
		this.setUpdateDate(new Date());
	}
	
	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.IEntity#caseCade(java.util.Map)
	 */
	public void caseCade(Map<Object, Object> params) {
//		this.setId(ParamUtils.getString(params, "id", null));
		if(null == this.getId()){
			this.initCreateInfo(params);
		}else{
			this.initUpdateInfo(params);
		}
	}

	/**
	 * @param id
	 */
	protected void setId(String id) {
		this.id = id;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	protected void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @param createDate the createDate to set
	 */
	protected void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param updaterId the updaterId to set
	 */
	protected void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	protected void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
