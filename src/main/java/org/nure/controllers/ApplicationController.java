package org.nure.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
public class ApplicationController {
	
	private ModelAndView prepareMainView() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/")
	public ModelAndView home() {
		return prepareMainView();
	}
	
	@RequestMapping("/{path:^(?:(?!^images$|^js$|^css$).)*$}/**")
	public ModelAndView anyPath() {
		return prepareMainView();
	}
}
