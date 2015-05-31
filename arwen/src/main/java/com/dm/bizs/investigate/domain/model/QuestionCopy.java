/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question_copy")
public class QuestionCopy extends StatusEntity {

	@Column
	private String num;
	
	@ManyToOne(targetEntity = Topic.class,cascade=CascadeType.PERSIST)
	private Topic topic;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "questionCopy")
	private Set<OptionCopy> options = new HashSet<>();
	
	@Column
	private String subject;
	
	@Column
	private Double score;
	
	@Column
	private String questionId;

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @return the options
	 */
	public Set<OptionCopy> getOptions() {
		return options;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @return the questionId
	 */
	public String getQuestionId() {
		return questionId;
	}
	
	/**
	 * @param question
	 */
	public void copy(Question question,Map<Object, Object> params){
		this.setSubject(question.getSubject());
		this.setScore(question.getScore());
		this.setQuestionId(question.getId());
		for(Option op : question.getOptions()){
			OptionCopy oc = new OptionCopy();
			oc.copy(op);
			oc.setQuestionCopy(this);
			this.options.add(oc);
		}
		this.setNum(ParamUtils.getString(params, "num", ""));
	}

	/**
	 * @param num the num to set
	 */
	protected void setNum(String num) {
		this.num = num;
	}

	/**
	 * @param topic the topic to set
	 */
	protected void setTopic(Topic topic) {
		this.topic = topic;
	}

	/**
	 * @param options the options to set
	 */
	protected void setOptions(Set<OptionCopy> options) {
		this.options = options;
	}

	/**
	 * @param subject the subject to set
	 */
	protected void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param score the score to set
	 */
	protected void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @param questionId the questionId to set
	 */
	protected void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
}
