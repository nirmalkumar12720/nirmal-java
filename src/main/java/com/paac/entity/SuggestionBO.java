package com.paac.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "suggestion")
public class SuggestionBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int suggestionId;
	String firstName;
	String lastName;
	String jobTitle;
	String racEmail;
	String status;
	public int getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(int suggestionId) {
		this.suggestionId = suggestionId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getRacEmail() {
		return racEmail;
	}

	public void setRacEmail(String racEmail) {
		this.racEmail = racEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
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

	public TopicAreaBO getTopicArea() {
		return topicArea;
	}

	public void setTopicArea(TopicAreaBO topicArea) {
		this.topicArea = topicArea;
	}

	public TopicBO getTopic() {
		return topic;
	}

	public void setTopic(TopicBO topic) {
		this.topic = topic;
	}

	String suggestion;
	
	@JsonFormat(pattern = "MM/dd/yyyy h:mm a")	
	LocalDateTime createdDate;
	String createdBy;
	String modifiedBy;
	int isDeleted;

	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER,optional = false )
	@JoinColumn(name="categoryId",nullable = false)	
	//@JsonIgnore
	CategoryBO category;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="topicAreaId",nullable = false)
	//@JsonIgnore
	TopicAreaBO topicArea;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="topicId",nullable = false)
	//@JsonIgnore
	TopicBO topic;
	


}
