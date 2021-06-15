package com.azure.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.demo.entity.LibraryEvent;
import com.azure.demo.jpa.LibraryEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class LibraryEventsService {
	
	private Logger LOGGER = LoggerFactory.getLogger(LibraryEventsService.class);
	

	@Autowired
	private LibraryEventsRepository repository;
	
	public void processLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		
		LOGGER.info("Event: {}", libraryEvent);
		

		switch(libraryEvent.getLibraryEventType()) {
			case NEW:
				save(libraryEvent);
				break;
			case UPDATE:
				validate(libraryEvent);
				save(libraryEvent);
				break;
			default:
				LOGGER.info("Invalid libraryEventType");
		}
		
	}
	
	
	private void save(LibraryEvent libraryEvent) {
		// needed to set value in mapping column
		libraryEvent.getBook().setLibraryEvent(libraryEvent);
		repository.save(libraryEvent);
		LOGGER.info("Sucessfully persisted the libraryEvent {}",libraryEvent);
	}
	

	private void validate(LibraryEvent libraryEvent) {
		if(libraryEvent.getLibraryEventId() == null) {
			throw new IllegalArgumentException("Library Event Id is missing");
		}
		
		Optional<LibraryEvent> libraryEventOptional = repository.findById(libraryEvent.getLibraryEventId());
		
		if(!libraryEventOptional.isPresent()) {
			throw new IllegalArgumentException("Not a valid libraryEvent!");
		}
		
		LOGGER.info("Validation is successful for {}", libraryEventOptional.get());
	}
	
	

	
	

	
}
