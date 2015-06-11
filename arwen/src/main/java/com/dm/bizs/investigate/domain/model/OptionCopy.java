package com.dm.bizs.investigate.domain.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dm.common.domain.model.AbstractEntity;
import com.dm.common.domain.model.Constant;
import com.dm.common.domain.model.ISortable;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question_option_copy")
public class OptionCopy extends AbstractEntity implements ISortable{

	@Column
	private String num;
	
	@Column
	private String content;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Constant remarkable = Constant.NO;
	
	@ManyToOne(targetEntity = QuestionCopy.class,cascade=CascadeType.PERSIST)
	private QuestionCopy questionCopy;
	
	@Column
	private String optionId;
	
	@Column
	private Integer sort;

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
	 * @return the remarkable
	 */
	public Constant getRemarkable() {
		return remarkable;
	}

	/**
	 * @return the questionCopy
	 */
	public QuestionCopy getQuestionCopy() {
		return questionCopy;
	}

	/**
	 * @return the optionId
	 */
	public String getOptionId() {
		return optionId;
	}
	
	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	public void copy(Option option){
		this.setNum(option.getNum());
		this.setContent(option.getContent());
		this.setRemarkable(option.getRemarkable());
		this.setOptionId(option.getId());
		this.setSort(option.getSort());
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.AbstractEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("num", this.num);
		res.put("content", this.content);
		res.put("remarkable", this.remarkable);
		res.put("sort", this.sort);
		return res;
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
	 * @param questionCopy the questionCopy to set
	 */
	protected void setQuestionCopy(QuestionCopy questionCopy) {
		this.questionCopy = questionCopy;
	}

	/**
	 * @param optionId the optionId to set
	 */
	protected void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	/**
	 * @param sort the sort to set
	 */
	protected void setSort(Integer sort) {
		this.sort = sort;
	}
}
