package com.racsuggestionbox.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Builder
@Table(name = "topic")
public class TopicBO {
	
	public int getTopicId() {
		return topicId;
	}


	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}


	public String getTopicName() {
		return topicName;
	}


	public void setTopicName(String topicName) {
		this.topicName = topicName;
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


	public TopicAreaBO getTopicArea() {
		return topicArea;
	}


	public void setTopicArea(TopicAreaBO topicArea) {
		this.topicArea = topicArea;
	}


	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int topicId;
	String topicName;
	LocalDateTime createdDate;
	String createdBy;
	String modifiedBy;
	LocalDateTime modifiedDate;
	int isDeleted;

	
	
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false )
	@JoinColumn(name="topicAreaId",nullable = false)	
	@JsonIgnore
	private  TopicAreaBO topicArea;


	public TopicBO() {
		super();
	}


	public TopicBO(int topicId, String topicName, LocalDateTime createdDate, String createdBy, String modifiedBy,
			LocalDateTime modifiedDate, int isDeleted, TopicAreaBO topicArea) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isDeleted = isDeleted;
		this.topicArea = topicArea;
	}

	
	
}
