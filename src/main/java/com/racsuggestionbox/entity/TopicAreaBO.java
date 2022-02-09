package com.racsuggestionbox.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;



@Entity
@Builder
@Table(name = "topicArea")
public class TopicAreaBO {
	
	public int getTopicAreaId() {
		return topicAreaId;
	}



	public void setTopicAreaId(int topicAreaId) {
		this.topicAreaId = topicAreaId;
	}



	public String getTopicAreaName() {
		return topicAreaName;
	}



	public void setTopicAreaName(String topicAreaName) {
		this.topicAreaName = topicAreaName;
	}



	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public int getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}



	public CategoryBO getCategory() {
		return category;
	}



	public void setCategory(CategoryBO category) {
		this.category = category;
	}



	public List<TopicBO> getTopic() {
		return topic;
	}



	public void setTopic(List<TopicBO> topic) {
		this.topic = topic;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int topicAreaId;	
	String topicAreaName;
	LocalDateTime createdDate;
	String createdBy;
	String modifiedBy;
	LocalDateTime modifiedDate;
	int isDeleted;
	
	

	
	@ManyToOne(fetch = FetchType.EAGER,optional = false )
	@JoinColumn(name="categoryId",nullable = false)	
	@JsonIgnore
	private  CategoryBO category;
	
	@OneToMany(mappedBy = "topicArea",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private  List<TopicBO> topic;



	public TopicAreaBO() {
		super();
	}



	public TopicAreaBO(int topicAreaId, String topicAreaName, LocalDateTime createdDate, String createdBy,
			String modifiedBy, LocalDateTime modifiedDate, int isDeleted, CategoryBO category, List<TopicBO> topic) {
		super();
		this.topicAreaId = topicAreaId;
		this.topicAreaName = topicAreaName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isDeleted = isDeleted;
		this.category = category;
		this.topic = topic;
	}
	

}
