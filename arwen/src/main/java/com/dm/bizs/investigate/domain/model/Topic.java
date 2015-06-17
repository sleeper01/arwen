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

import org.springframework.util.CollectionUtils;

import com.dm.common.domain.model.ISortable;
import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_topic")
public class Topic extends StatusEntity implements ISortable{

	@Column
	private String num;
	
	@Column
	private String subject;
	
	@Column
	private Integer sort;
	
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

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("num", this.num);
		res.put("subject", this.subject);
		res.put("sort", this.sort);
		if(!CollectionUtils.isEmpty(this.questions)){
			Set<QuestionCopy> sortedQuestion = new ParamUtils.SortedArrayBuilder<QuestionCopy>().build();
			for(QuestionCopy copy : this.questions){
				if(Status.ENABLE.equals(copy.getStatus())){
					sortedQuestion.add(copy);
				}
			}
			res.put("questions", SerializeUtils.convertEntitiesToMaps(sortedQuestion));
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setNum(ParamUtils.getString(params, "num", ""));
		this.setSubject(ParamUtils.getString(params, "subject", ""));
		this.setSort(ParamUtils.getInteger(params, "sort", 0));
	}
	
	/**
	 * @param question
	 * @param params
	 */
	public void addQuestion(Question question,Map<Object, Object> params){
		QuestionCopy qc = new QuestionCopy();
		qc.setTopic(this);
		qc.copy(question, params);
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

	/**
	 * @param sort the sort to set
	 */
	protected void setSort(Integer sort) {
		this.sort = sort;
	}
}
