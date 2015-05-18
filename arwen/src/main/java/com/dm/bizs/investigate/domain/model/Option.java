/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

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
@Table(name="tbl_invest_question_option")
public class Option extends StatusEntity {
	
	@Column
	private String num;
	
	@Column
	private String content;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Constant remarkable = Constant.NO;
	
	@ManyToOne(targetEntity = Question.class,cascade=CascadeType.PERSIST)
	private Question question;

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the remarkable
	 */
	public Constant getRemarkable() {
		return remarkable;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("num", this.num);
		res.put("content", this.content);
		res.put("remarkable", this.remarkable);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setNum(ParamUtils.getString(params, "num", ""));
		this.setContent(ParamUtils.getString(params, "content", ""));
		this.setRemarkable(ParamUtils.getEnum(params, "remarkable", Constant.values()));
	}

	/**
	 * @param num the num to set
	 */
	protected void setNum(String num) {
		this.num = num;
	}

	/**
	 * @param content the content to set
	 */
	protected void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param remarkable the remarkable to set
	 */
	protected void setRemarkable(Constant remarkable) {
		this.remarkable = remarkable;
	}

	/**
	 * @param question the question to set
	 */
	protected void setQuestion(Question question) {
		this.question = question;
	}
}
