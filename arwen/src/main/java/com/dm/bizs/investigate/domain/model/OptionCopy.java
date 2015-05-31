/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dm.common.domain.model.AbstractEntity;
import com.dm.common.domain.model.Constant;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question_option_copy")
public class OptionCopy extends AbstractEntity{

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
	
	public void copy(Option option){
		this.setNum(option.getNum());
		this.setContent(option.getContent());
		this.setRemarkable(option.getRemarkable());
		this.setOptionId(option.getId());
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
}
