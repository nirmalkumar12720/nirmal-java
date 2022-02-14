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
var newSuggest = new XMLHttpRequest();
window.onload = onLoadCalls();


function onLoadCalls() {
	categoryDropdown();   //fetch category
	//submitButtonClicked();

}




// fetcing the category in dropdown

function categoryDropdown() {
	try{
		xhrCategory.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/category", true);
	xhrCategory.onreadystatechange = processResponseCategory;
	xhrCategory.send(null);
	}
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-categoryDropdown()");
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
var categoryId;
function ff(obj){
	categoryId = $(obj).find('option:selected').attr('id');
}


//fetch topicArea in dropdown
function topicAreaDropdown() {
	try{
		var selectedCategory = document.getElementById('category').options[document.getElementById('category').selectedIndex].id;
	categoryId = selectedCategory;
	xhrTopicArea.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/topicArea/" + categoryId, true);
	xhrTopicArea.onreadystatechange = processResponseTopicArea;
	xhrTopicArea.send(null);
	}
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-topicAreaDropdown()");
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
		
//		
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
	
	try{
		var selectedTopicArea = document.getElementById('topicArea').options[document.getElementById('topicArea').selectedIndex].id;
	    topicAreaId = selectedTopicArea;
		
	xhrTopic.open("GET", "https://nirmal-docker.azurewebsites.net/suggestion/topic/"+topicAreaId, true);
	xhrTopic.onreadystatechange = processResponseTopic;
	xhrTopic.send(null);
	}
	
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-topicDropdown()");
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
var topicId;
function functionTopic(){
	topicId = document.getElementById('topic').options[document.getElementById('topic').selectedIndex].id;
}






// save suggestion detsils in db
var tableData = document.getElementById("suggestiontable");

function saveSuggestionDetails(){	
	try{
	var save_firstName = document.getElementById("firstName").value;
	var save_lastName = document.getElementById("lastName").value;
	var save_jobTitle = document.getElementById("jobTitle").value;
	var save_racEmail = document.getElementById("racEmail").value;
	var save_suggestion = document.getElementById("suggestion").value;
//	var save_category = document.getElementById("category").value;
//	var save_topicArea = document.getElementById("topicArea").value;
//	var save_topic = document.getElementById("topic").value;

	
	 
	var data = {
		"firstName":save_firstName,"lastName":save_lastName,"jobTitle":save_jobTitle,"suggestion":save_suggestion,"racEmail":save_racEmail
	};
	
	newSuggest.open("POST","https://nirmal-docker.azurewebsites.net/suggestion/saveSuggestion/"+categoryId+"/"+topicAreaId+"/"+topicId,true);
	newSuggest.setRequestHeader("Content-Type","application/json");
	newSuggest.send(JSON.stringify(data));
	newSuggest.onreadystatechange = saveSuggestionResponse;
	//clearSuggestion();
	window.location="SuggestionList.html";
	//window.reload();
	}
	
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-saveSuggestionDetails()");
	}
		
}

function saveSuggestionResponse(){
	if(suggestionDetails.readyState ==4 && suggestionDetails.status ==200){
	
           
}
}

function clearSuggestion(){
	try{
	document.getElementById("firstName").value="";
	document.getElementById("lastName").value="";
	document.getElementById("jobTitle").value="";
	document.getElementById("racEmail").value="";
	document.getElementById("category").value="";
	document.getElementById("topicArea").value="";
	document.getElementById("topic").value="";
	document.getElementById("suggestion").value="";
	}
	
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-clearSuggestion()");
	}
}



document.getElementById("lastName").disabled = true;
document.getElementById("jobTitle").disabled = true;
document.getElementById("racEmail").disabled = true;
document.getElementById("category").disabled = true;
document.getElementById("topicArea").disabled = true;
document.getElementById("topic").disabled = true;
document.getElementById("suggestion").disabled = true;
//document.getElementById("start_button").disabled = true;

      

// validation alert message for firstName
function onBlurFirstName() {

	var error = document.getElementById("onBlurFirstName");
	if (document.getElementById("firstName").value == "") {

		// Changing content and color of content

		error.textContent = "Please enter the firstName";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		document.getElementById("lastName").disabled = false;
		return false;
	}

}

// validation alert message for lastName
function onBlurLastName() {

	var error = document.getElementById("onBlurLastName");
	if (document.getElementById("lastName").value == "") {

		// Changing content and color of content

		error.textContent = "Please enter the lastName";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		document.getElementById("jobTitle").disabled = false;
		return false;
	}

}

// validation alert message for jobTitle
function onBlurJobTitle() {

	var error = document.getElementById("onBlurJobTitle");
	if (document.getElementById("jobTitle").value == "") {

		// Changing content and color of content

		error.textContent = "Please enter the jobTitle";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		 document.getElementById("racEmail").disabled = false;
		return false;
	}

}

// validation alert message for RACEmail
function onBlurRACEmail() {

	var error = document.getElementById("onBlurRACEmail");
	if (document.getElementById("racEmail").value == "") {

		// Changing content and color of content

		error.textContent = "Please enter the RACEmail";
		error.style.color = "red";
		//return false;
	}
	else if (document.getElementById("racEmail").value) {


		var filter = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		if (!filter.test(racEmail.value)) {
			//document.getElementById('racEmail').style.borderColor = "red";
			error.innerHTML = "<p style='color:red'>" + "Enter a vaild Email</p>";
			//return false;
		}
		else {
		error.textContent = "";
		//document.getElementById("category").style.borderColor="lightgrey";
		document.getElementById("category").disabled = false;
		//return false;
	}

	}

}

// validation alert message for category
function onBlurCategory() {

	var error = document.getElementById("onBlurCategory");
	if (document.getElementById("category").selectedIndex == 0) {

		// Changing content and color of content

		error.textContent = "Please select the category";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		document.getElementById("topicArea").disabled = false;
		return false;
	}

}

// validation alert message for topicArea
function onBlurTopicArea() {

	var error = document.getElementById("onBlurTopicArea");
	if (document.getElementById("topicArea").selectedIndex == 0) {
		// Changing content and color of content       
		error.textContent = "Please select the topicArea";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		document.getElementById("topic").disabled = false;
		return false;
	}

}

// validation alert message for topic
function onBlurTopic() {

	var error = document.getElementById("onBlurTopic");
	if (document.getElementById("topic").selectedIndex == 0) {
		// Changing content and color of content       
		error.textContent = "Please select the topic";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		document.getElementById("suggestion").disabled = false;
		return false;
	}

}

// validation alert message for suggestion
function onBlurSuggestion() {

	var error = document.getElementById("onBlurSuggestion");
	if (document.getElementById("suggestion").value == "") {
		// Changing content and color of content        
		error.textContent = "Please enter the suggestion";
		error.style.color = "red";
		return false;
	}
	else {
		error.textContent = "";
		//document.getElementById("submit_buttonss").disabled = false;
		return false;
	}

}

document.getElementById("start_button").addEventListener("click",function(){
submit();	
	
});




var firstNameerror = document.getElementById("onBlurFirstName");
var lastNameError = document.getElementById("onBlurLastName");
var jobTitleError = document.getElementById("onBlurJobTitle");
var racEmailError = document.getElementById("onBlurRACEmail");
var CategoryError = document.getElementById("onBlurCategory");
var topicAreaError = document.getElementById("onBlurTopicArea");
var topicError = document.getElementById("onBlurTopic");
function submit(){
	try{
  //  var error = document.getElementById("onBlurFirstName");
	if (document.getElementById("firstName").value == "") {

		// Changing content and color of content

		firstNameerror.textContent = "Please enter the firstName";
		firstNameerror.style.color = "red";
		
		//return false;
	}
	else {
		firstNameerror.textContent = "";
	
		//return false;
	}
	
	
//	var error = document.getElementById("onBlurLastName");
	if (document.getElementById("lastName").value == "") {

		// Changing content and color of content

		lastNameError.textContent = "Please enter the lastName";
		lastNameError.style.color = "red";
		//  document.getElementById("jobTitle").disabled = true;
		//return false;
	}
	else {
		lastNameError.textContent = "";
		
		//return false;
	}
    
    
	if (document.getElementById("jobTitle").value == "") {

		// Changing content and color of content

		jobTitleError.textContent = "Please enter the jobTitle";
		jobTitleError.style.color = "red";
		
		//return false;
	}
	else {
		jobTitleError.textContent = "";
		// document.getElementById("racEmail").disabled = false;
		//return false;
	}

    
	if (document.getElementById("racEmail").value == "") {

		// Changing content and color of content

		racEmailError.textContent = "Please enter the RACEmail";
		racEmailError.style.color = "red";
	
		//return false;
	}
	else {
		racEmailError.textContent = "";
		
		//return false;
	}
   

	if (document.getElementById("category").selectedIndex == 0) {

		// Changing content and color of content

		CategoryError.textContent = "Please select the category";
		CategoryError.style.color = "red";
		//return false;
	}
	else {
		CategoryError.textContent = "";
		//return false;
	}
	
	
	if (document.getElementById("topicArea").selectedIndex == 0) {
		// Changing content and color of content       
		topicAreaError.textContent = "Please select the topicArea";
		topicAreaError.style.color = "red";
		//return false;
	}
	else {
		topicAreaError.textContent = "";
		//return false;
	}
	
	if (document.getElementById("topic").selectedIndex == 0) {
		// Changing content and color of content       
		topicError.textContent = "Please select the topic";
		topicError.style.color = "red";
		//return false;
	}
	else {
		topicError.textContent = "";
		//return false;
	}
	var suggestionError = document.getElementById("onBlurSuggestion");
	if (document.getElementById("suggestion").value == "") {
		// Changing content and color of content        
		suggestionError.textContent = "Please enter the suggestion";
		suggestionError.style.color = "red";
		//return false;
	}
	else {
		suggestionError.textContent = "";
		//return false;
	}
	//saveSuggestionDetails();
	}
  
	catch(e){
		jsExceptionHandling(e, "RAC-Suggestion.js-submit()");
	}

  }
