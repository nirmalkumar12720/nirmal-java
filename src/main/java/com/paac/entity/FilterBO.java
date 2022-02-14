package com.paac.entity;



import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FilterBO {
String topicAreaName;
String topicName;
String searchText;


public String getTopicAreaName() {
	return topicAreaName;
}


public void setTopicAreaName(String topicAreaName) {
	this.topicAreaName = topicAreaName;
}


public String getTopicName() {
	return topicName;
}


public void setTopicName(String topicName) {
	this.topicName = topicName;
}


public String getSearchText() {
	return searchText;
}


public void setSearchText(String searchText) {
	this.searchText = searchText;
}


public FilterBO() {
	super();
}


public FilterBO(String topicAreaName, String topicName, String searchText) {
	super();
	this.topicAreaName = topicAreaName;
	this.topicName = topicName;
	this.searchText = searchText;
}





}
