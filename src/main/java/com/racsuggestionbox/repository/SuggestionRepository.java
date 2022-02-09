package com.racsuggestionbox.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.racsuggestionbox.entity.SuggestionBO;

/**
 * @author Kishore.r
 *
 */
public interface SuggestionRepository extends JpaRepository<SuggestionBO, Integer> {

	// Create Object for suggestionBoxRepository which is "suggestionBoxRepository"
	// and Invokes findCountIsDeleted() method.
	/**
	 * @return
	 */
	@Query(value = "SELECT count(n) FROM SuggestionBO n WHERE n.isDeleted=0")
	public Integer findCountIsDeleted();

//	"Pageable pageable = PageRequest.of(pageindex, limit);
//	Create Object for suggestionBoxRepository which is ""suggestionBoxRepository"" and Invokes  findSuggestionIsDeleted(pageable)  method."

	/**
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT n FROM SuggestionBO n WHERE n.isDeleted=0 ORDER BY createdDate DESC")
	public List<SuggestionBO> findSuggestionIsDeleted(Pageable pageable);

//	"Pageable pageable = PageRequest.of(pageindex, limit,sort.by('suggestionId').ascending());
//	Create Object for suggestionBoxRepository which is ""suggestionBoxRepository"" and Invokes  findSuggestionDetailsInDeleted(pageable)  method."

	/**
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT n FROM SuggestionBO n WHERE n.isDeleted=0")
	public List<SuggestionBO> findSuggestionDetailsIsDeleted(Pageable pageable);

	// Create Object for suggestionBoxRepository which is "suggestionBoxRepository"
	// and Invokes findDetailsByExcelFormatIsDeleted() method.

	/**
	 * @return
	 */
	@Query(value = "SELECT n FROM SuggestionBO n WHERE n.isDeleted=0")
	public List<SuggestionBO> findDetailsByExcelFormatIsDeleted();

}
