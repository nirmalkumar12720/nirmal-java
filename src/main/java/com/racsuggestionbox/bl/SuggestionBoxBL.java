package com.racsuggestionbox.bl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.racsuggestionbox.entity.FilterBO;
import com.racsuggestionbox.entity.SuggestionBO;
import com.racsuggestionbox.dl.SuggestionBoxDL;

@Component
@Configuration
public class SuggestionBoxBL {

	@Autowired
	SuggestionBoxDL suggestiondl;

	public SuggestionBoxBL(SuggestionBoxDL suggestiondl) {
		super();
		this.suggestiondl = suggestiondl;
	}

	// getting all category details
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getCategoryDetails() method.

	/**
	 * @return
	 */
	public Map<Integer, String> getCategoryDetails() {
		return this.suggestiondl.getCategoryDetails();
	}

	// getting all topicArea details
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getTopicAreaDetails(categoryId) method.
	/**
	 * @param categoryId
	 * @return
	 */
	public Map<Integer, String> getTopicAreaDetails(int categoryId) {
		return this.suggestiondl.getTopicAreaDetails(categoryId);
	}

	// getting all topic details
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getTopicDetails(topicAreaId) method.
	/**
	 * @param topicAreaId
	 * @return
	 */
	public Map<Integer, String> getTopicDetails(int topicAreaId) {
		return this.suggestiondl.getTopicDetails(topicAreaId);
	}

	// getting total count
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getSuggestionCount() method.

	/**
	 * @return
	 */
	public Integer getSuggestionCount() {
		return this.suggestiondl.getSuggestionCount();
	}

	// getting list of suggestions details
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getSuggestionDetails(pageIndex) method.

	public List<SuggestionBO> getSuggestionDetails(int pageIndex) {
		return this.suggestiondl.getSuggestionDetails(pageIndex);
	}

	// save suggestion details
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// saveSuggestionDetails(suggestionBO) method.

	/**
	 * @param suggestionBO
	 * @param categoryId
	 * @param topicAreaId
	 * @param topicId
	 * @return
	 */
	public boolean saveSuggestionDetails(SuggestionBO suggestionBO, int categoryId, int topicAreaId, int topicId) {
		suggestionBO.setCreatedDate(LocalDateTime.now());
		suggestionBO.setCreatedBy("Kishore Raju");
		suggestionBO.setStatus("Open");
		return this.suggestiondl.saveSuggestionDetails(suggestionBO, categoryId, topicAreaId, topicId);

//		CategoryBO category = this.suggestiondl.fetchCategoryName(suggestionBO.getCategoryName());
//		TopicAreaBO topicArea = this.suggestiondl.fetchTopicAreaName(suggestionBO.getTopicAreaName());
//		TopicBO topic = this.suggestiondl.fetchTopicName(suggestionBO.getTopicName());			    
//	    suggestionBO.setCategory(category);
//	    suggestionBO.setTopicArea(topicArea);
//	    suggestionBO.setTopic(topic);

	}

	// suggestion in ascending order
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getSuggestionDetailsInOrder(pageIndex) method.
	/**
	 * @param pageIndex
	 * @return
	 */
	public List<SuggestionBO> getSuggestionDetailsInOrder(int pageIndex) {
		return this.suggestiondl.getSuggestionDetailsInOrder(pageIndex);
	}

	// getting filter count
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getFilterCount(FilterBO) method.\

	/**
	 * @param filterBO
	 * @return
	 */
	public Integer getFilterCount(FilterBO filterBO) {
		return this.suggestiondl.getFilterCount(filterBO);
	}

	// getting filtered data
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getSuggestionListUsingFilter(pageIndex,FilterBO) method.

	/**
	 * @param filterBO
	 * @param pageIndex
	 * @return
	 */
	public List<SuggestionBO> getSuggestionListUsingFilter(FilterBO filterBO, int pageIndex) {
		return this.suggestiondl.getSuggestionListUsingFilter(filterBO, pageIndex);
	}

	// getting details in excel format
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getDetailsByExcelFormat() method.

	/**
	 * @return
	 */
	public List<SuggestionBO> getDetailsByExcelFormat() {
		return this.suggestiondl.getDetailsByExcelFormat();
	}

}
