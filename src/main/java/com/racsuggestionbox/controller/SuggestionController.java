package com.racsuggestionbox.controller;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.racsuggestionbox.entity.FilterBO;
import com.racsuggestionbox.entity.SuggestionBO;
import com.racsuggestionbox.excel.SuggestionExcelExporter;
import com.racsuggestionbox.bl.SuggestionBoxBL;

@RestController
@CrossOrigin(origins = "*")
public class SuggestionController {

	@Autowired
	SuggestionBoxBL suggestionbl;

	
// For fetching all the category
//	Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: getCategoryDetails()             Type: GET,
//	path: /suggestion/category

	/**
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/category")
	public ResponseEntity<Map<Integer, String>> getCategoryDetails(HttpServletRequest httpRequest) throws IOException {
		try {
			Map<Integer, String> category = this.suggestionbl.getCategoryDetails();
			return ResponseEntity.status(HttpStatus.OK).body(category);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

	// For fetching all the topicArea
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method:  getTopicAreaDetails(categoryId)            Type: GET,
//	path: /suggestion/topicArea/{categoryId}


	/**
	 * @param categoryId
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/topicArea/{categoryId}")
	public ResponseEntity<Map<Integer, String>> getTopicAreaDetails(@PathVariable("categoryId") int categoryId,
			HttpServletRequest httpRequest) throws IOException {
		try {
			Map<Integer, String> topicArea = this.suggestionbl.getTopicAreaDetails(categoryId);
			return ResponseEntity.status(HttpStatus.OK).body(topicArea);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;

	}

	// For fetching all the topic
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: getTopicDetails(topicAreaId)                               Type: GET,
//	path: /suggestion/topic/{topicAreaId}


	/**
	 * @param topicAreaId
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/topic/{topicAreaId}")
	public ResponseEntity<Map<Integer, String>> getTopicDetails(@PathVariable("topicAreaId") int topicAreaId,
			HttpServletRequest httpRequest) throws IOException {
		try {
			Map<Integer, String> topic = this.suggestionbl.getTopicDetails(topicAreaId);
			return ResponseEntity.status(HttpStatus.OK).body(topic);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();

		}
		return null;
	}

	// save suggestion details
//	Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method:  saveSuggestionDetails()            Type: POST,
//	path: /suggestion/saveSuggestion/{categoryId}/{topicAreaId}/{topicId}
//	RequestBody:suggestionBO
//	pathvariable:categoryId,topicAreaId,topicId

	/**
	 * @param categoryId
	 * @param topicAreaId
	 * @param topicId
	 * @param suggestionBO
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/suggestion/saveSuggestion/{categoryId}/{topicAreaId}/{topicId}")
	public ResponseEntity<Boolean> saveSuggestionDetails(@PathVariable("categoryId") int categoryId,
			@PathVariable("topicAreaId") int topicAreaId, @PathVariable("topicId") int topicId,
			@RequestBody SuggestionBO suggestionBO, HttpServletRequest httpRequest) throws IOException {

		try {
			boolean saveSuggestion = this.suggestionbl.saveSuggestionDetails(suggestionBO, categoryId, topicAreaId,
					topicId);
			return ResponseEntity.status(HttpStatus.OK).body(saveSuggestion);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

	// getting total count
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: suggestionCount()            Type: GET,
//	path: /suggestion/suggestionCount"

	/**
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/suggestionCount")
	public ResponseEntity<Integer> getSuggestionCount(HttpServletRequest httpRequest) throws IOException {
		try {
			Integer suggestionCount = this.suggestionbl.getSuggestionCount();
			return ResponseEntity.status(HttpStatus.OK).body(suggestionCount);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();

		}
		return null;
	}

	// get all suggestion details
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: getSuggestionDetails()            Type: GET,
//	path: /suggestion/suggestionDetails"

	/**
	 * @param pageIndex
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/suggestionDetails/{pageIndex}")
	public ResponseEntity<List<SuggestionBO>> getSuggestionDetails(@PathVariable("pageIndex") int pageIndex,
			HttpServletRequest httpRequest) throws IOException {
		try {
			List<SuggestionBO> suggestionDetails = this.suggestionbl.getSuggestionDetails(pageIndex);
			return ResponseEntity.status(HttpStatus.OK).body(suggestionDetails);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

// Ascending order based on id
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: sortingInOrder()            Type: GET,
//	path: /suggestion/suggestionInOrder"

	/**
	 * @param pageIndex
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/suggestion/suggestionInorder/{pageIndex}")
	public ResponseEntity<List<SuggestionBO>> getSuggestionDetailsInOrder(@PathVariable("pageIndex") int pageIndex,
			HttpServletRequest httpRequest) throws IOException { // getSuggestionInOrder
		try {
			List<SuggestionBO> suggestionDetailsInOrder = this.suggestionbl.getSuggestionDetailsInOrder(pageIndex);
			return ResponseEntity.status(HttpStatus.OK).body(suggestionDetailsInOrder);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

	// get filter count
//	Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: applyFilterButton()            Type: POST
//	path: /suggestion/filteredCount

	/**
	 * @param filterBO
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/suggestion/filterCount")
	public ResponseEntity<Integer> getFilterCount(@RequestBody FilterBO filterBO, HttpServletRequest httpRequest)
			throws IOException {
		try {
			Integer filterCount = this.suggestionbl.getFilterCount(filterBO);
			return ResponseEntity.status(HttpStatus.OK).body(filterCount);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

	// getting Filtered details
//	Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: applyFilterButton(PageIndex)            Type: GET,
//	path: /suggestion/filteredDetails/{pageIndex}

	/**
	 * @param filterBO
	 * @param pageIndex
	 * @param httpRequest
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/suggestion/filterDetails/{pageIndex}")
	public ResponseEntity<List<SuggestionBO>> getSuggestionListUsingFilter(@RequestBody FilterBO filterBO,
			@PathVariable("pageIndex") int pageIndex, HttpServletRequest httpRequest) throws IOException {
		try {
			List<SuggestionBO> suggestionDetailsUsingFilter = this.suggestionbl.getSuggestionListUsingFilter(filterBO,
					pageIndex);
			return ResponseEntity.status(HttpStatus.OK).body(suggestionDetailsUsingFilter); // filterData
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		return null;
	}

	// export all data to excel
//	"Create an HTTP request object and make an Ajax call to suggestionController where,
//	Method: exportToExcel()            Type: GET,
//	path: /suggestion/export/excel"

	/**
	 * @param response
	 * @param httpRequest
	 * @throws IOException
	 */
	@GetMapping("/suggestion/export/excel")
	public void exportToExcel(HttpServletResponse response, HttpServletRequest httpRequest) throws IOException {
		try {
			response.setContentType("application/octet-stream");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=suggestions_" + currentDateTime + ".xlsx";
			response.setHeader(headerKey, headerValue);
			List<SuggestionBO> listSuggestions = suggestionbl.getDetailsByExcelFormat();
			SuggestionExcelExporter excelExporter = new SuggestionExcelExporter(listSuggestions);
			excelExporter.export(response);
		} catch (Exception e) {
			//globalExceptionHandler.writeToExceptionFile(e, httpRequest);
			e.printStackTrace();
		}
		// return null;
	}

}
