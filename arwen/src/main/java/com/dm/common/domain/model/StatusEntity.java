/**
 * 
 */
package com.dm.common.domain.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@MappedSuperclass
public class StatusEntity extends AbstractEntity {

	@Column
	@Enumerated(EnumType.STRING)
	private Status status = Status.ENABLE;
	
	public static enum Status{
		ENABLE,
		DISABLE;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.AbstractEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("status", this.status);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.AbstractEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		this.setStatus(ParamUtils.getEnum(params, "status", Status.values()));
		super.caseCade(params);
	}
	
	/**
	 * 
	 */
	public void disable(){
		this.setStatus(Status.DISABLE);
	}
	
	/**
	 * 
	 */
	public void enable(){
		this.setStatus(Status.ENABLE);
	}

	/**
	 * @param status the status to set
	 */
	protected void setStatus(Status status) {
		this.status = status;
	}
}
