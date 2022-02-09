function jsExceptionHandling(e, methodAndFileName) {
	var jsExceptionXhr = new XMLHttpRequest();
	jsExceptionXhr.open("PUT", "https://nirmal-docker.azurewebsites.net/jsExceptions/"+e+"/"+methodAndFileName, true);
	jsExceptionXhr.send();
	jsExceptionXhr.onreadystatechange = function(){
		
	};
}