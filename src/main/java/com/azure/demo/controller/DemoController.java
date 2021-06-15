package com.azure.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.demo.entity.LibraryEvent;
import com.azure.demo.entity.LibraryEventType;
import com.azure.demo.service.LibraryEventsService;
import com.fasterxml.jackson.core.JsonProcessingException;



@RestController
@RequestMapping(value="libraryevents/v{version:[0-9]+}")
public class DemoController {
	
	private Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private LibraryEventsService libraryEventsService;
	
	@PostMapping("/create")
	public ResponseEntity<LibraryEvent> createNewLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException {
		
		LOGGER.info("Entered create library event operation");
		libraryEvent.setLibraryEventType(LibraryEventType.NEW);
		
		libraryEventsService.processLibraryEvent(libraryEvent);
		
		LOGGER.info("Completed create library event operation");
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}
}
