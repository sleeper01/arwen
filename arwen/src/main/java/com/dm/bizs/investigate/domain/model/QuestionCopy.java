package com.dm.bizs.investigate.domain.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

import com.dm.bizs.investigate.domain.model.Question.QuestionType;
import com.dm.common.domain.model.ISortable;
import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;
import com.dm.common.utils.SerializeUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question_copy")
public class QuestionCopy extends StatusEntity implements ISortable{

	@Column
	private String num;
	
	@ManyToOne(targetEntity = Topic.class,cascade=CascadeType.PERSIST)
	private Topic topic;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "questionCopy",orphanRemoval=true)
	private Set<OptionCopy> options = new HashSet<>();
	
	@Column
	private String subject;
	
	@Column
	private Double score;
	
	@Column
	private String questionId;
	
	@Column
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@Column
	private Integer sort;

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
	 * @return the type
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param question
	 */
	public void copy(Question question,Map<Object, Object> params){
		this.setSubject(question.getSubject());
		this.setScore(question.getScore());
		this.setQuestionId(question.getId());
		this.setType(question.getType());
		for(Option op : question.getOptions()){
			OptionCopy oc = new OptionCopy();
			oc.setQuestionCopy(this);
			oc.copy(op);
			this.options.add(oc);
		}
		this.setNum(ParamUtils.getString(params, "num", ""+(this.getTopic().getQuestions().size()+1)));
		this.setSort(ParamUtils.getInteger(params, "sort", this.getTopic().getQuestions().size()+1));
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object,Object> res = super.toMap();
		res.put("num", this.num);
		res.put("subject", this.getSubject());
		res.put("score", this.getScore());
		res.put("type", this.getType());
		if(!CollectionUtils.isEmpty(this.options)){
			Set<OptionCopy> sortedQuestion = new ParamUtils.SortedArrayBuilder<OptionCopy>().build();
			sortedQuestion.addAll(this.options);
			res.put("options", SerializeUtils.convertEntitiesToMaps(sortedQuestion));
		}
		return res;
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

	/**
	 * @param type the type to set
	 */
	protected void setType(QuestionType type) {
		this.type = type;
	}

	/**
	 * @param sort the sort to set
	 */
	protected void setSort(Integer sort) {
		this.sort = sort;
	}
}
