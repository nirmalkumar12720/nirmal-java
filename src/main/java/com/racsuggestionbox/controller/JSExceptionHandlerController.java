package com.racsuggestionbox.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class JSExceptionHandlerController {
	
	
	
	@PutMapping(path = "/jsExceptions/{jsException}/{jsMethod}")
	public void handleJsException(@PathVariable("jsException") Exception jsException,@PathVariable("jsMethod") String jsMethod,HttpServletRequest request) throws IOException {
		
		//globalExceptionHandler.writeJsExceptionFile(jsException, jsMethod, request);
	}
	

}
