package com.azure.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;




@Entity
public class Book {
	
	@Id
	@NotNull(message = "Id is mandatory")
	private Integer bookId;
	
//	@NotBlank(message = "Book name is mandatory")
	private String bookName;
	
//	@NotBlank(message = "Author is mandatory")
	private String bookAuthor;
	
	@OneToOne
	@JoinColumn(name = "libraryEventId")
	private LibraryEvent libraryEvent;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	@JsonBackReference
	public LibraryEvent getLibraryEvent() {
		return libraryEvent;
	}

	public void setLibraryEvent(LibraryEvent libraryEvent) {
		this.libraryEvent = libraryEvent;
	}
	
	
}
