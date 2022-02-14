package com.paacrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paac.entity.CategoryBO;


/**
 * @author Kishore.r
 *
 */
public interface CategoryRepository extends JpaRepository<CategoryBO, Integer> {
	
	//Create Object for categoryRepository which is "categoryRepository" and Invokes   findCategoryIsDeleted() method.
	/**
	 * @return
	 */
	@Query(value="SELECT n FROM CategoryBO n WHERE n.isDeleted=0  ")
	public List<CategoryBO> findCategoryIsDeleted();
		
	@Query("SELECT n FROM CategoryBO n WHERE n.categoryName=?1 ")
	public CategoryBO findByCategoryName(String categoryName);
	
}
