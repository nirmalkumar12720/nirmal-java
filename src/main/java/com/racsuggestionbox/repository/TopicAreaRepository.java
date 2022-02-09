package com.racsuggestionbox.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.racsuggestionbox.entity.TopicAreaBO;

/**
 * @author Kishore.r
 *
 */
public interface TopicAreaRepository extends JpaRepository<TopicAreaBO, Integer> {

	
	//Create Object for topicAreaRepository which is "topicAreaRepository" and Invokes  findTopicAreaIsDeleted() method.
	/**
	 * @param categoryId
	 * @return
	 */
	@Query(value="SELECT n FROM TopicAreaBO n WHERE n.category.categoryId=?1 AND n.isDeleted=0  ")
	public List<TopicAreaBO> findTopicAreaIsDeleted(int categoryId);

	@Query("SELECT n FROM TopicAreaBO n WHERE n.topicAreaName=?1 ")
	public TopicAreaBO findByTopicAreaName(String topicAreaName);	
	
}
