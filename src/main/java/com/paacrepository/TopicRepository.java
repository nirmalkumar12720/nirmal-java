package com.paacrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paac.entity.TopicBO;



/**
 * @author Kishore.r
 *
 */
public interface TopicRepository extends JpaRepository<TopicBO, Integer> {

//	Create Object for TopicRepository which is "TopicRepository" and Invokes  findTopicIsDeleted() method.
	/**
	 * @param topicAreaId
	 * @return
	 */
	@Query(value="SELECT n FROM TopicBO n WHERE n.topicArea.topicAreaId=?1 AND n.isDeleted=0 ")
	public List<TopicBO> findTopicIsDeleted(int topicAreaId);

	
	@Query("SELECT n FROM TopicBO n WHERE n.topicName=?1 ")
	public TopicBO findByTopicName(String topicName);	
	

}
