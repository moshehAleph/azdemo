package com.azure.demo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.azure.demo.entity.LibraryEvent;


@Repository
public interface LibraryEventsRepository extends CrudRepository<LibraryEvent,Integer> {

	

}
