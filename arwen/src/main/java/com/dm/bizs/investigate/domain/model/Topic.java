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
@Table(name="tbl_invest_topic")
public class Topic extends StatusEntity {

	@Column
	private String num;
	
	@Column
	private String subject;
	
	@ManyToOne(targetEntity = Questionaire.class,cascade=CascadeType.PERSIST)
	private Questionaire questionaire;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "topic")
	private Set<QuestionCopy> questions = new HashSet<>();

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the questionaire
	 */
	public Questionaire getQuestionaire() {
		return questionaire;
	}

	/**
	 * @return the questions
	 */
	public Set<QuestionCopy> getQuestions() {
		return questions;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		return super.toMap();
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setNum(ParamUtils.getString(params, "num", ""));
		this.setSubject(ParamUtils.getString(params, "subject", ""));
	}
	
	/**
	 * @param question
	 * @param params
	 */
	public void addQuestion(Question question,Map<Object, Object> params){
		QuestionCopy qc = new QuestionCopy();
		qc.copy(question, params);
		qc.setTopic(this);
		this.questions.add(qc);
	}

	/**
	 * @param num the num to set
	 */
	protected void setNum(String num) {
		this.num = num;
	}

	/**
	 * @param subject the subject to set
	 */
	protected void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param questionaire the questionaire to set
	 */
	protected void setQuestionaire(Questionaire questionaire) {
		this.questionaire = questionaire;
	}

	/**
	 * @param questions the questions to set
	 */
	protected void setQuestions(Set<QuestionCopy> questions) {
		this.questions = questions;
	}
}
