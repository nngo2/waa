package edu.mum.cs544;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Book {
	private int id;
	
	@NotNull
	private String title;
	
	@Pattern(regexp="\\d{3}-\\d{10}")
	private String ISBN;
	
	@NotNull
	private String author;
	
	@NotNull
	@Min(value=1)
	private double price;
	
	@PastDateConstraint
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate publishedDate;

	public Book() {
		super();
	}

	public Book(String title, String iSBN, String author, double price, LocalDate publishedDate) {
		super();
		this.title = title;
		this.ISBN = iSBN;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return this.ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

}
