package com.paac.dl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.paac.entity.CategoryBO;
import com.paac.entity.FilterBO;
import com.paac.entity.SuggestionBO;
import com.paac.entity.TopicAreaBO;
import com.paac.entity.TopicBO;
import com.paacrepository.CategoryRepository;
import com.paacrepository.SuggestionRepository;
import com.paacrepository.TopicAreaRepository;
import com.paacrepository.TopicRepository;

@Component
@Configuration
public class SuggestionBoxDL {

	@Autowired
	CategoryRepository categoryrepo;

	@Autowired
	TopicAreaRepository topicarearepo;

	@Autowired
	TopicRepository topicrepo;

	@Autowired
	SuggestionRepository suggestionrepo;

	@Value("${limit}")
	int limit;

	// getting category details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getCategoryDetails() method.

	/**
	 * @return
	 */
	// getting category details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getCategoryDetails() method.

	/**
	 * @return
	 */
	public Map<Integer, String> getCategoryDetails() {
		List<CategoryBO> category = this.categoryrepo.findCategoryIsDeleted();
		Map<Integer, String> categorys = new HashMap<Integer, String>();

		for (CategoryBO each : category) {
			categorys.put(each.getCategoryId(), each.getCategoryName());
		}
		return categorys;
	}

	// getting topicArea details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getTopicAreaDetails(categoryId) method.

	/**
	 * @param categoryId
	 * @return
	 */
	public Map<Integer, String> getTopicAreaDetails(int categoryId) {
		List<TopicAreaBO> topicArea = this.topicarearepo.findTopicAreaIsDeleted(categoryId);
		Map<Integer, String> topicAreas = new HashMap<Integer, String>();

		for (TopicAreaBO each : topicArea) {
			topicAreas.put(each.getTopicAreaId(), each.getTopicAreaName());
		}

		return topicAreas;
	}

	// getting topic details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getTopicDetails(topicAreaId) method.
	/**
	 * @param topicAreaId
	 * @return
	 */
	public Map<Integer, String> getTopicDetails(int topicAreaId) {
		List<TopicBO> topic = this.topicrepo.findTopicIsDeleted(topicAreaId);

		Map<Integer, String> topics = new HashMap<Integer, String>();

		for (TopicBO each : topic) {
			topics.put(each.getTopicId(), each.getTopicName());
		}
		return topics;
	}

	// suggestion count
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getSuggestionCount() method.
	/**
	 * @return
	 */
	public Integer getSuggestionCount() {
		Integer suggestionCount = this.suggestionrepo.findCountIsDeleted();
		return suggestionCount;
	}

	// getting suggestionDetails
	// Create Object for suggestionBoxBL which is suggestionBoxBL and Invokes
	// getSuggestionDetails(pageIndex) method.

	/**
	 * @param pageIndex
	 * @return
	 */
	public List<SuggestionBO> getSuggestionDetails(int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex, limit);
		List<SuggestionBO> suggestionDetails = this.suggestionrepo.findSuggestionIsDeleted(pageable);
		return suggestionDetails;
	}

	// saving suggestion details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// saveSuggestionDetails(suggestionBO) method.

	/**
	 * @param suggestionBO
	 * @param categoryId
	 * @param topicAreaId
	 * @param topicId
	 * @return
	 */
	public boolean saveSuggestionDetails(SuggestionBO suggestionBO, int categoryId, int topicAreaId, int topicId) {
		Optional<CategoryBO> categoryData = this.categoryrepo.findById(categoryId);
		Optional<TopicAreaBO> topicAreaData = this.topicarearepo.findById(topicAreaId);
		Optional<TopicBO> topicData = this.topicrepo.findById(topicId);
		suggestionBO.setCategory(categoryData.get());
		suggestionBO.setTopicArea(topicAreaData.get());
		suggestionBO.setTopic(topicData.get());
		this.suggestionrepo.save(suggestionBO);
		return true;
	}

	// getting suggestionDetails InOrder
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getSuggestionDetailsInOrder(pageIndex) method.
	/**
	 * @param pageIndex
	 * @return
	 */
	public List<SuggestionBO> getSuggestionDetailsInOrder(int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex, limit, Sort.by("suggestionId").ascending());
		List<SuggestionBO> suggestionDetailsInOrder = this.suggestionrepo.findSuggestionDetailsIsDeleted(pageable);
		return suggestionDetailsInOrder;
	}

	@Autowired
	EntityManager em;

	// getting filtered data
	/**
	 * @param filterBO
	 * @return
	 */
	public Query getFilter(FilterBO filterBO) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SuggestionBO> cq = cb.createQuery(SuggestionBO.class);
		Root<SuggestionBO> suggestion = cq.from(SuggestionBO.class);
		List<Predicate> predicates = new ArrayList<>();

		if (!filterBO.getTopicAreaName().isEmpty()) {
			predicates.add(cb.equal(suggestion.get("topicArea").get("topicAreaName"), filterBO.getTopicAreaName()));

		}

		if (!filterBO.getTopicName().isEmpty()) {
			predicates.add(cb.equal(suggestion.get("topic").get("topicName"), filterBO.getTopicName()));

		}
if(filterBO.getSearchText()!=null) {
	predicates.add(cb.or(
			// idCriteria,
			cb.like(cb.lower(suggestion.get("topicArea").get("topicAreaName")),
			"%" + filterBO.getSearchText().toLowerCase() + "%"),
			cb.like(cb.lower(suggestion.get("topic").get("topicName")),
			"%" + filterBO.getSearchText().toLowerCase() + "%"),
			cb.like(cb.lower(suggestion.get("suggestionId").as(String.class)),
			"%" + filterBO.getSearchText().toLowerCase() + "%"),
			cb.like(cb.lower(suggestion.get("firstName")),
			"%" + filterBO.getSearchText().toLowerCase() + "%")));
}
		predicates.add(cb.equal(suggestion.get("isDeleted"), 0));
		Predicate[] arr = predicates.stream().toArray(Predicate[]::new);
		cq.orderBy(cb.desc(suggestion.get("createdDate")));
		cq.where(arr);
		Query query = em.createQuery(cq);
		return query;
	}

	// filter count
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getFilterCount(FilterBO) method.

	/**
	 * @param filterBO
	 * @return
	 */
	public Integer getFilterCount(FilterBO filterBO) {
		Query query = this.getFilter(filterBO);
		return query.getResultList().size();
	}

	// filter details
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getSuggestionListUsingFilter(pageIndex,FilterBO) method.

	/**
	 * @param filterBO
	 * @param pageIndex
	 * @return
	 */
	public List<SuggestionBO> getSuggestionListUsingFilter(FilterBO filterBO, int pageIndex) {
		Query query = this.getFilter(filterBO);
		List<SuggestionBO> suggestion = query.setFirstResult(pageIndex * limit).setMaxResults(limit).getResultList();
		return suggestion;
	}

	// getting data in excel
	// Create Object for suggestionBoxDL which is suggestionBoxDL and Invokes
	// getDetailsByExcelFormat() method.

	/**
	 * @return
	 */
	public List<SuggestionBO> getDetailsByExcelFormat() {
		List<SuggestionBO> suggestionList = this.suggestionrepo.findDetailsByExcelFormatIsDeleted();
		return suggestionList;
	}

//	public CategoryBO fetchCategoryName(String categoryName) {
//	
//		return this.categoryrepo.findByCategoryName(categoryName);
//	}
//
//	public TopicAreaBO fetchTopicAreaName(String topicAreaName) {
//		return this.topicarearepo.findByTopicAreaName(topicAreaName);
//	}
//
//	public TopicBO fetchTopicName(String topicName) {
//		return this.topicrepo.findByTopicName(topicName);
//	}
}
