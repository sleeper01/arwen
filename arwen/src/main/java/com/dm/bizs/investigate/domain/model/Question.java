/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_question")
public class Question extends StatusEntity {

	@Column
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@Column
	private String subject;
	
	@Column
	private Double score;
	
	@Column
	private Timestamp updateTime;
	
	@Column
	private String firLevelLabel;
	
	@Column
	private String secLevelLabel;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
	private Set<Option> options = new HashSet<>();
	
	/**
	 * @return the type
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the options
	 */
	public Set<Option> getOptions() {
		return options;
	}

	/**
	 * @return the firLevelLabel
	 */
	public String getFirLevelLabel() {
		return firLevelLabel;
	}

	/**
	 * @return the secLevelLabel
	 */
	public String getSecLevelLabel() {
		return secLevelLabel;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object,Object> res = super.toMap();
		res.put("type", this.type);
		res.put("subject", this.subject);
		res.put("score", this.score);
		res.put("updateTime",this.updateTime);
		res.put("firLevelLabel", this.firLevelLabel);
		res.put("secLevelLabel", this.secLevelLabel);
		Set<Map<Object,Object>> optionArray = new TreeSet<>(new Comparator<Map<Object,Object>>() {
			@Override
			public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
				boolean isInt = false;
				try {
					Integer.valueOf(o1.get("num").toString());
					isInt = true;
				} catch (Exception e) {
					isInt = false;
				}
				if(!isInt){
					if(!o1.get("num").equals(o2.get("num")))
						return o1.get("num").toString().compareTo(o2.get("num").toString());
				}else{
					Integer n1 = Integer.valueOf(o1.get("num").toString());
					Integer n2 = Integer.valueOf(o2.get("num").toString());
					if(!n1.equals(n2))
						return n1.compareTo(n2);
				}
				return o1.get("id").toString().compareTo(o2.get("id").toString());
			}
		});
		if(!CollectionUtils.isEmpty(this.options)){
			for(Option op : this.options){
				optionArray.add(op.toMap());
			}
		}
		res.put("options", optionArray);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setType(ParamUtils.getEnum(params, "type", QuestionType.values()));
		this.setSubject(ParamUtils.getString(params, "subject", ""));
		this.setScore(ParamUtils.getDouble(params, "score", 0.0));
		this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		this.setFirLevelLabel(ParamUtils.getString(params, "firLevelLabel", ""));
		this.setSecLevelLabel(ParamUtils.getString(params, "secLevelLabel", ""));
	}
	
	public void addOption(Map<Object,Object> params){
		Option option = new Option();
		option.caseCade(params);
		this.addOption(option);
	}
	
	public void addOption(Option option){
		if(option != null){
			option.setQuestion(this);
			option.setSort(this.options.size()+1);
			this.options.add(option);
		}
	}

	/**
	 * @param type the type to set
	 */
	protected void setType(QuestionType type) {
		this.type = type;
	}

	/**
	 * @param score the score to set
	 */
	protected void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	protected void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @param subject the subject to set
	 */
	protected void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param options the options to set
	 */
	protected void setOptions(Set<Option> options) {
		this.options = options;
	}

	/**
	 * @param firLevelLabel the firLevelLabel to set
	 */
	protected void setFirLevelLabel(String firLevelLabel) {
		this.firLevelLabel = firLevelLabel;
	}

	/**
	 * @param secLevelLabel the secLevelLabel to set
	 */
	protected void setSecLevelLabel(String secLevelLabel) {
		this.secLevelLabel = secLevelLabel;
	}

	public static enum QuestionType{
		
		/**
		 * 单选
		 */
		SINGLE_SELECTION,
		
		/**
		 * 多选
		 */
		MULTI_SELECTIION,
		
		/**
		 * 主观
		 */
		SUBJECTIVITY,
		
		/**
		 * 排序
		 */
		SORT,
		
		/**
		 *评价 
		 */
		MARK,
		
		/**
		 * 打分 
		 */
		GRADE
		;
	}
}
