package com.racsuggestionbox.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;



@Data
@Entity
@Builder
@Table(name ="category")
public class CategoryBO {
	
	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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


	public List<SuggestionBO> getSuggestion() {
		return suggestion;
	}


	public void setSuggestion(List<SuggestionBO> suggestion) {
		this.suggestion = suggestion;
	}


	public List<TopicAreaBO> getTopicArea() {
		return topicArea;
	}


	public void setTopicArea(List<TopicAreaBO> topicArea) {
		this.topicArea = topicArea;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int categoryId;
	String categoryName;
	LocalDateTime createdDate;
	String createdBy;
	String modifiedBy;
	LocalDateTime modifiedDate;
	int isDeleted;
	
	
	@OneToMany(mappedBy = "suggestion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private  List<SuggestionBO> suggestion;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private  List<TopicAreaBO> topicArea;


	public CategoryBO() {
		super();
	}


	public CategoryBO(int categoryId, String categoryName, LocalDateTime createdDate, String createdBy,
			String modifiedBy, LocalDateTime modifiedDate, int isDeleted, List<SuggestionBO> suggestion,
			List<TopicAreaBO> topicArea) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isDeleted = isDeleted;
		this.suggestion = suggestion;
		this.topicArea = topicArea;
	}
	
}
