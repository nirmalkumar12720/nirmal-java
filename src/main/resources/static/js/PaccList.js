var pageIndex;
var xhttp = new XMLHttpRequest();
var tbody = document.getElementById("tbody");
var suggestionCount;
var category;
var suggestionObj;
var xhrCategory = new XMLHttpRequest();
var xhrTopicArea = new XMLHttpRequest();
var xhrTopic = new XMLHttpRequest();
var topic;
var suggestionDetails = new XMLHttpRequest();
var clearTopicArea;
var totalpages;
var page;
var xhrGetFilterSuggestionDetails;
var xhrGetFilterSuggestionCount;
var firstPageNumber = 1;
var lastPageNumber = 10;
var limit = 10;
var filterPagination = false;
var sortingInOrders = false;
var filterData;
var firstPageNo = 1;
window.onload = onLoadCalls();
var rowFilterCount;
var filterSuggestionCount;
var rowCount;
var firstNo = 1;
var suggestionCount;


//onload function
function onLoadCalls() {
	categoryDropdown();
	suggestionCount();
}


//After submit button clicked getting count

function suggestionCount() {
	//submit();
	try {
		var xhrGetSuggestionCount = new XMLHttpRequest();
		xhrGetSuggestionCount.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/suggestionCount", true);
		xhrGetSuggestionCount.send();
		xhrGetSuggestionCount.onreadystatechange = function() {

			if (xhrGetSuggestionCount.readyState == 4 && xhrGetSuggestionCount.status == 200) {
				suggestionCount = JSON.parse(this.responseText);
				if (suggestionCount <= 0) {
					document.getElementById("suggestiontable").innerHTML = "<span class='suggestionDetails'> No Suggestion Details </span>";
				}
				pageIndex = 0;
				totalpages = Math.ceil(suggestionCount / 10);
				document.getElementById("totalRecords").innerText = suggestionCount + " Records";
				page = 1;

				getSuggestionDetails(pageIndex); //processResponseSuggestionDetails
				rowCount = $('#suggestiontable tr').length;
				document.getElementById("numberOfRecords").innerHTML = firstNo + "-" + rowCount;

				document.getElementById("backNumber").innerText = totalpages;
				document.getElementById("frontNumber").innerText = "1";


				document.getElementById("numberOfRecords").innerText = firstPageNumber + "-" + limit;
				if (document.getElementById("frontNumber").innerText == document.getElementById("backNumber").innerText) {
					disableRightButtton();
				}
			}
		};
	}
	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-suggestionCount()");
	}

}


//right & left  nav code here

//right navigation

function rightNavigationButton() {
	try {
		document.getElementById("leftButton").disabled = false;

		if (totalpages > pageIndex) {
			pageIndex++;
			document.getElementById("backNumber").innerText = totalpages;
			document.getElementById("frontNumber").innerText = Number(pageIndex) + 1;
			if ((document.getElementById("searchBar").value=="")&&(document.getElementById("category").selectedIndex == 0) && (document.getElementById("topicArea").selectedIndex == 0) && (document.getElementById("topic").selectedIndex == 0)) {
				document.getElementById("numberOfRecords").innerHTML = Number(((pageIndex + 1) - 1) * limit + 1) + "-" + Number(((pageIndex + 1) * limit) > suggestionCount ? suggestionCount : ((pageIndex + 1) * limit));
			}
			else {
				document.getElementById("numberOfRecords").innerHTML = Number(((pageIndex + 1) - 1) * limit + 1) + "-" + Number(((pageIndex + 1) * limit) > filterSuggestionCount ? filterSuggestionCount : ((pageIndex + 1) * limit));
			}
			//document.getElementById("frontNumber").innerText= totalpages;
			document.getElementById("suggestiontable").innerText = "";
			if (filterPagination == true) {
				getSuggestionListUsingFilter();
			}
			else {
				getSuggestionDetails();
			}

		}

		if (document.getElementById("frontNumber").innerText == document.getElementById("backNumber").innerText) {
			disableRightButtton();
		}
		else {
			document.getElementById("rightButton").disabled = false;
		}
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-rightNavigationButton()");
	}

}

//left navigation

function leftNavigationButton() {
	try {
		document.getElementById("rightButton").disabled = false;

		if (totalpages > pageIndex) {
			pageIndex--;
			document.getElementById("backNumber").innerText = totalpages;
			document.getElementById("frontNumber").innerText = Number(pageIndex) + 1;
			if((document.getElementById("searchBar").value=="")&&(document.getElementById("category").selectedIndex == 0) && (document.getElementById("topicArea").selectedIndex == 0) && (document.getElementById("topic").selectedIndex == 0)) {
				document.getElementById("numberOfRecords").innerHTML = Number(((pageIndex + 1) - 1) * limit + 1) + "-" + Number(((pageIndex + 1) * limit) > suggestionCount ? suggestionCount : ((pageIndex + 1) * limit));
			}
			else {
				document.getElementById("numberOfRecords").innerHTML = Number(((pageIndex + 1) - 1) * limit + 1) + "-" + Number(((pageIndex + 1) * limit) > filterSuggestionCount ? filterSuggestionCount : ((pageIndex + 1) * limit));
			}

			//document.getElementById("frontNumber").innerText= totalpages-1;
			document.getElementById("suggestiontable").innerText = "";
			if (filterPagination == true) {
				getSuggestionListUsingFilter();
			}
			else {
				getSuggestionDetails();
			}
		}
		if (document.getElementById("frontNumber").innerText == 1) {
			disableLeftButton();
		}
		else {
			document.getElementById("leftButton").disabled = false;
		}

	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-leftNavigationButton()");
	}

}


function disableRightButtton() {
	try {
		document.getElementById("rightButton").disabled = true;
	}

	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-disableRightButtton()");
	}
}

function disableLeftButton() {
	try {
		document.getElementById("leftButton").disabled = true;
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-disableRightButtton()");
	}
}








function getSuggestionDetails() {
	try {
		var xhrGetSuggestionDetails = new XMLHttpRequest();
		xhrGetSuggestionDetails.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/suggestionDetails/" + pageIndex, true);
		xhrGetSuggestionDetails.send();
		xhrGetSuggestionDetails.onreadystatechange = function() {

			if (xhrGetSuggestionDetails.readyState == 4 && xhrGetSuggestionDetails.status == 200) {
				var data = JSON.parse(this.responseText);
				displaySuggestionDetails(data);
			}
		};
	}

	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-getSuggestionDetails()");
	}
}


function displaySuggestionDetails(suggestionObj) {
	var rowLength = suggestionObj.length;

	for (var i = 0; i < rowLength; i++) {

		var tableRow = document.createElement('tr');
		tableRow.id = "tr" + i;

		var suggestionTd = document.createElement('td');
		suggestionTd.id = "suggestiondId" + i;
		suggestionTd.innerHTML = suggestionObj[i].suggestionId;
		tableRow.appendChild(suggestionTd);

		var submittedByTd = document.createElement('td');
		submittedByTd.id = "createdBy" + i;
		submittedByTd.innerHTML = suggestionObj[i].firstName;
		tableRow.appendChild(submittedByTd);

		var topicAreaNameTd = document.createElement('td');
		topicAreaNameTd.id = "topicAreaName" + i;
		topicAreaNameTd.innerHTML = suggestionObj[i].topicArea.topicAreaName;
		tableRow.appendChild(topicAreaNameTd);

		var topicNameTd = document.createElement('td');
		topicNameTd.id = "topicName" + i;
		topicNameTd.innerHTML = suggestionObj[i].topic.topicName;
		tableRow.appendChild(topicNameTd);

		var statusTd = document.createElement('td');
		statusTd.id = "status" + i;
		statusTd.innerHTML = suggestionObj[i].status;
		tableRow.appendChild(statusTd);

		var createdDateTd = document.createElement('td');
		createdDateTd.id = "createdDate" + i;
		createdDateTd.innerHTML = suggestionObj[i].createdDate;
		tableRow.appendChild(createdDateTd);

		document.getElementById("suggestiontable").appendChild(tableRow);
	}

}








//sorting  count

function sortedCount() {
	try {
		var xhrGetSuggestionCount = new XMLHttpRequest();
		xhrGetSuggestionCount.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/suggestionCount", true);
		xhrGetSuggestionCount.send();
		xhrGetSuggestionCount.onreadystatechange = function() {

			if (xhrGetSuggestionCount.readyState == 4 && xhrGetSuggestionCount.status == 200) {
				suggestionCount = JSON.parse(this.responseText);
				if (suggestionCount <= 0) {
					document.getElementById("suggestiontable").innerHTML = "<span> No Suggestion Details </span>"; // tbody in id
				}
				pageIndex = 0;
				totalpages = Math.ceil(suggestionCount / 10);
				page = 1;

				if (document.getElementById("frontNumber").innerText == document.getElementById("backNumber").innerText) {
					disableRightButtton();
				}
			}
		};
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-sortedCount()");
	}
}

//sorting inorder

function sortingInOrder() {

	sortedCount();

	var xhrGetSuggestionDetailsInOrder = new XMLHttpRequest();
	xhrGetSuggestionDetailsInOrder.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/suggestionInorder/" + pageIndex, true);
	xhrGetSuggestionDetailsInOrder.send();
	xhrGetSuggestionDetailsInOrder.onreadystatechange = function() {

		if (xhrGetSuggestionDetailsInOrder.readyState == 4 && xhrGetSuggestionDetailsInOrder.status == 200) {
			var data = JSON.parse(this.responseText);
			document.getElementById("suggestiontable").innerText = ""; //newly added
			displaySuggestionDetails(data);  //displaySuggestionInOrder

		}
	};
}

//function displaySuggestionInOrder(suggestionObj){
//	   var rowLength = suggestionObj.length;
//
//		document.getElementById("suggestiontable").innerText="";
//		for (var i = 0; i < rowLength; i++) {
//
//			var tableRow = document.createElement('tr');	
//			tableRow.id = "tr"+i;
//		   
//			
//			var suggestionTd = document.createElement('td');
//			suggestionTd.id = "suggestiondId"+i;
//			suggestionTd.innerHTML = suggestionObj[i].suggestionId;
//			tableRow.appendChild(suggestionTd);
//			
//			var submittedByTd= document.createElement('td');
//			submittedByTd.id = "createdBy"+i;
//			submittedByTd.innerHTML = suggestionObj[i].firstName;
//			tableRow.appendChild(submittedByTd);
//			
//			var topicAreaNameTd = document.createElement('td');
//			topicAreaNameTd.id = "topicAreaName"+i;
//			topicAreaNameTd.innerHTML = suggestionObj[i].topicArea.topicAreaName;
//			tableRow.appendChild(topicAreaNameTd);
//			
//			var topicNameTd = document.createElement('td');
//			topicNameTd.id = "topicName"+i;
//			topicNameTd.innerHTML = suggestionObj[i].topic.topicName;
//			tableRow.appendChild(topicNameTd);
//			
//			var statusTd = document.createElement('td');
//			statusTd.id = "status"+i;
//			statusTd.innerHTML = suggestionObj[i].status;
//			tableRow.appendChild(statusTd);
//			
//			var createdDateTd = document.createElement('td');
//			createdDateTd.id = "createdDate"+i;
//			createdDateTd.innerHTML = suggestionObj[i].createdDate;
//			tableRow.appendChild(createdDateTd);
//			
//			document.getElementById("suggestiontable").appendChild(tableRow);	
//}
//
//} 


//filter icon clicked
function filterIconClicked() {
	topicAreaDropdown();
	topicDropdown();
}


//filter count

function applyFilterButton() {

	try {
		filterPagination = true;

		var filter_topicArea = document.getElementById("topicArea").value;
		var filter_topic = document.getElementById("topic").value;

       var searchText=document.getElementById("searchBar").value;
       
		if (filter_topicArea == 0) {
			var filter_topicArea = document.getElementById("topicArea").value = "";
		}

		if (filter_topic == 0) {
			var filter_topic = document.getElementById("topic").value = "";
		}
    // if(document.getElementById("searchBar").value)


		var filter_Data = { "topicAreaName": filter_topicArea, "topicName": filter_topic ,"searchText":searchText};

		xhrGetFilterSuggestionCount = new XMLHttpRequest();
		xhrGetFilterSuggestionCount.open("POST", "https://nirmal-docker.azurewebsites.net/suggestion/filterCount", true);
		xhrGetFilterSuggestionCount.setRequestHeader("Content-Type", "application/json");
		xhrGetFilterSuggestionCount.send(JSON.stringify(filter_Data));
		xhrGetFilterSuggestionCount.onreadystatechange = getFilterCount;
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-applyFilterButton()");
	}

}

function getFilterCount() //filtercountresponse
{

	if (xhrGetFilterSuggestionCount.readyState == 4 && xhrGetFilterSuggestionCount.status == 200) {
		filterSuggestionCount = JSON.parse(this.responseText);

		if (filterSuggestionCount <= 0) {
			document.getElementById("suggestiontable").innerHTML = "<span> No Suggestion Details </span>";
		}
		else {
			pageIndex = 0;
			totalpages = Math.ceil(filterSuggestionCount / 10);
			page = 1;
			document.getElementById("totalRecords").innerText = filterSuggestionCount + " Records";


			//    getSuggestionListUsingFilter(0);

			getSuggestionListUsingFilter(pageIndex);
			

				document.getElementById("backNumber").innerText = totalpages;
				document.getElementById("frontNumber").innerText = "1";
				if (document.getElementById("frontNumber").innerText == document.getElementById("backNumber").innerText) {

					disableRightButtton();
				}


			//getSuggestionListUsingFilter(pageIndex); 


		}



	}
}



document.getElementById("searchBar").addEventListener("keyup",function()

{
	filterPagination=true;
	applyFilterButton();
});


//filter Details
function getSuggestionListUsingFilter(pageIndex) {

	try {
		var filter_topicArea = document.getElementById("topicArea").value;
		var filter_topic = document.getElementById("topic").value;


       var searchText= document.getElementById("searchBar").value;



		var filter_Data = { "searchText":searchText,"topicAreaName": filter_topicArea, "topicName": filter_topic };
		xhrGetFilterSuggestionDetails = new XMLHttpRequest();
		xhrGetFilterSuggestionDetails.open("POST", "https://nirmal-docker.azurewebsites.net/suggestion/filterDetails/" + pageIndex, true);
		xhrGetFilterSuggestionDetails.setRequestHeader("Content-Type", "application/json");
		
		xhrGetFilterSuggestionDetails.onreadystatechange = function() {

			if (xhrGetFilterSuggestionDetails.readyState == 4 && xhrGetFilterSuggestionDetails.status == 200) {
				filterData = JSON.parse(this.responseText);
				document.getElementById("suggestiontable").innerText = "";  //newly Added
				displaySuggestionDetails(filterData); //displayFilterSuggestionDetails
			}
			rowFilterCount = $('#suggestiontable tr').length;
				document.getElementById("numberOfRecords").innerHTML = firstPageNo + "-" + rowFilterCount;
		};
		xhrGetFilterSuggestionDetails.send(JSON.stringify(filter_Data));
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-getSuggestionListUsingFilter()");
	}
}


//function displayFilteredSuggestionDetails(suggestionObj){
//	
//	 var rowLength = suggestionObj.length;	
//     document.getElementById("suggestiontable").innerText="";
//     document.getElementById("totalRecords").innerText=filterSuggestionCount+" Records";
//     
//		for (var i = 0; i < rowLength; i++) {
//
//			var tableRow = document.createElement('tr');	
//			tableRow.id = "tr"+i;
//		   			
//			var suggestionTd = document.createElement('td');
//			suggestionTd.id = "suggestiondId"+i;
//			suggestionTd.innerHTML = suggestionObj[i].suggestionId;
//			tableRow.appendChild(suggestionTd);
//			
//			var submittedByTd= document.createElement('td');
//			submittedByTd.id = "createdBy"+i;
//			submittedByTd.innerHTML = suggestionObj[i].firstName;
//			tableRow.appendChild(submittedByTd);
//			
//			var topicAreaNameTd = document.createElement('td');
//			topicAreaNameTd.id = "topicAreaName"+i;
//			topicAreaNameTd.innerHTML = suggestionObj[i].topicArea.topicAreaName;
//			tableRow.appendChild(topicAreaNameTd);
//			
//			var topicNameTd = document.createElement('td');
//			topicNameTd.id = "topicName"+i;
//			topicNameTd.innerHTML = suggestionObj[i].topic.topicName;
//			tableRow.appendChild(topicNameTd);
//			
//			var statusTd = document.createElement('td');
//			statusTd.id = "status"+i;
//			statusTd.innerHTML = suggestionObj[i].status;
//			tableRow.appendChild(statusTd);
//			
//			var createdDateTd = document.createElement('td');
//			createdDateTd.id = "createdDate"+i;
//			createdDateTd.innerHTML = suggestionObj[i].createdDate;
//			tableRow.appendChild(createdDateTd);
//			
//			document.getElementById("suggestiontable").appendChild(tableRow);	
//}
//
//} 

function cancelButtonClicked() {
	window.reload();
}

//export to excel

function exportToExcel() {
	try {
		var xhrGetSuggestionDetailsInExcelFormat = new XMLHttpRequest();
		xhrGetSuggestionDetailsInExcelFormat.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/export/excel", true);
		xhrGetSuggestionDetailsInExcelFormat.send();
		xhrGetSuggestionDetailsInExcelFormat.onreadystatechange = function() {
			window.location = "http://localhost:8085/suggestion/export/excel";
		}

	}

	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-exportToExcel()");
	}
}

// fetcing the category in dropdown

function categoryDropdown() {
	try {
		xhrCategory.open("GET", "https://nirmal-docker.azurewebsites.net:8085/suggestion/category", true);
		xhrCategory.onreadystatechange = processResponseCategory;
		xhrCategory.send(null);
	}

	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-categoryDropdown()");
	}
}

function processResponseCategory() {
	if (xhrCategory.readyState == 4 && xhrCategory.status == 200) {

		var categoryDropdown = document.getElementById("category"); //clearcategroy
		category = JSON.parse(xhrCategory.responseText);

		for (var key in category) {
			var opt = document.createElement("option");
			opt.id = key;
			opt.innerHTML = category[key];
			categoryDropdown.options.add(opt);

		}
		categoryDropdown.appendChild(opt);
	};



}


//fetch topicArea in dropdown
function topicAreaDropdown() {

	try {
		var selectedCategory = document.getElementById('category').options[document.getElementById('category').selectedIndex].id;
		categoryId = selectedCategory;
		xhrTopicArea.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/topicArea/" + categoryId, true);
		xhrTopicArea.onreadystatechange = processResponseTopicArea;
		xhrTopicArea.send(null);
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-topicAreaDropdown()");
	}
}

function processResponseTopicArea() {
	if (xhrTopicArea.readyState == 4 && xhrTopicArea.status == 200) {


		var topicAreaDropdown = document.getElementById("topicArea");
		topicArea = JSON.parse(xhrTopicArea.responseText);

		//clear the data
		clearTopicArea = document.getElementById("topicArea");
		var topicAreaOptLength = clearTopicArea.options.length;

		for (i = topicAreaOptLength - 1; i > 0; i--) {
			clearTopicArea.options[i] = null;
		}


		for (var key in topicArea) {
			var opt = document.createElement("option");
			opt.id = key;
			opt.innerHTML = topicArea[key];
			topicAreaDropdown.options.add(opt);

		}
		topicAreaDropdown.appendChild(opt);
	};
}





//fetch topic in dropdown

function topicDropdown() {

	try {
		var selectedTopicArea = document.getElementById('topicArea').options[document.getElementById('topicArea').selectedIndex].id;
		topicAreaId = selectedTopicArea;

		xhrTopic.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/topic/" + topicAreaId, true);
		xhrTopic.onreadystatechange = processResponseTopic;
		xhrTopic.send(null);
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-topicDropdown()");
	}
}

function processResponseTopic() {
	if (xhrTopic.readyState == 4 && xhrTopic.status == 200) {

		var topicDropdown = document.getElementById("topic");
		topic = JSON.parse(xhrTopic.responseText);

		//clear the data
		clearTopic = document.getElementById("topic");
		var topicOptLength = clearTopic.options.length;

		for (i = topicOptLength - 1; i > 0; i--) {
			clearTopic.options[i] = null;
		}

		for (var key in topic) {
			var opt = document.createElement("option");
			opt.id = key;
			opt.innerHTML = topic[key];
			topicDropdown.options.add(opt);

		}

		topicDropdown.appendChild(opt);

	};
}





// validation alert message for category
function onBlurCategory() {

	try {
		var error = document.getElementById("onBlurCategory");
		if (document.getElementById("category").selectedIndex == 0) {

			// Changing content and color of content

			error.textContent = "Please select the category";
			error.style.color = "red";
			return false;
		}
		else {
			error.textContent = "";
			return false;
		}
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-onBlurCategory()");
	}

}

// validation alert message for topicArea
function onBlurTopicArea() {

	try {
		var error = document.getElementById("onBlurTopicArea");
		if (document.getElementById("topicArea").selectedIndex == 0) {
			// Changing content and color of content       
			error.textContent = "Please select the topicArea";
			error.style.color = "red";
			return false;
		}
		else {
			error.textContent = "";
			return false;
		}
	}



	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-onBlurTopicArea()");
	}

}

// validation alert message for topic
function onBlurTopic() {

	try {
		var error = document.getElementById("onBlurTopic");
		if (document.getElementById("topic").selectedIndex == 0) {
			// Changing content and color of content       
			error.textContent = "Please select the topic";
			error.style.color = "red";
			return false;
		}
		else {
			error.textContent = "";
			return false;
		}
	}


	catch (e) {
		jsExceptionHandling(e, "SuggestionList.js-onBlurTopic()");
	}
}
