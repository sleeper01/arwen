/**
 * 
 */
package com.dm.bizs.investigate.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dm.common.domain.model.Constant;
import com.dm.common.domain.model.StatusEntity;
import com.dm.common.utils.ParamUtils;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_invest_questionaire")
public class Questionaire extends StatusEntity {

	@Column
	private String title;
	
	@Column
	private String subtitle;
	
	@Column
	private String desp;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Constant nameless;
	
	@Column
	private Date expireDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "questionaire")
	private Set<Topic> topics = new HashSet<>();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @return the desp
	 */
	public String getDesp() {
		return desp;
	}

	/**
	 * @return the nameless
	 */
	public Constant getNameless() {
		return nameless;
	}

	/**
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @return the topics
	 */
	public Set<Topic> getTopics() {
		return topics;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#toMap()
	 */
	@Override
	public Map<Object, Object> toMap() {
		Map<Object, Object> res = super.toMap();
		res.put("title", this.title);
		res.put("subtitle", this.subtitle);
		res.put("desp", this.desp);
		res.put("nameless", this.nameless);
		res.put("expireDate", this.expireDate);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dm.common.domain.model.StatusEntity#caseCade(java.util.Map)
	 */
	@Override
	public void caseCade(Map<Object, Object> params) {
		super.caseCade(params);
		this.setTitle(ParamUtils.getString(params, "title", ""));
		this.setSubtitle(ParamUtils.getString(params, "subtitle", ""));
		this.setDesp(ParamUtils.getString(params, "desp", ""));
		this.setNameless(ParamUtils.getEnum(params, "nameless", Constant.values()));
		this.setExpireDate(ParamUtils.getDate(params, "expireDate", new Date()));
	}
	
	/**
	 * @param params
	 */
	public void addTopic(Map<Object,Object> params){
		Topic topic = new Topic();
		topic.caseCade(params);
		this.addTopic(topic);
	}
	
	/**
	 * @param topic
	 */
	public void addTopic(Topic topic){
		if(topic != null){
			topic.setQuestionaire(this);
			this.topics.add(topic);
		}
	}

	/**
	 * @param title the title to set
	 */
	protected void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	protected void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @param desp the desp to set
	 */
	protected void setDesp(String desp) {
		this.desp = desp;
	}

	/**
	 * @param nameless the nameless to set
	 */
	protected void setNameless(Constant nameless) {
		this.nameless = nameless;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	protected void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @param topics the topics to set
	 */
	protected void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
}
