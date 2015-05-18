/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question_label")
public class QuestionLabel extends StatusEntity {

	@Column
	private String name;
	
	@Column
	@Enumerated(EnumType.STRING)
	private QuestionLableLevel level;
	
	public static enum QuestionLableLevel{
		FIRST_LEVEL,
		SECOND_LEVEL;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the level
	 */
	public QuestionLableLevel getLevel() {
		return level;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("name", this.name);
		res.put("level", this.level.name());
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setName(ParamUtils.getString(params, "name", ""));
		this.setLevel(ParamUtils.getEnum(params, "level", QuestionLableLevel.values()));
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param level the level to set
	 */
	protected void setLevel(QuestionLableLevel level) {
		this.level = level;
	}
}
