package com.techiesedge.library.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_management")

public class BookManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookID;
	
	@Column(unique = true)
	private String ISBN;
	
	@Column(name = "book_title")
	private String bookTitle;
	
	private String author;
	
	private String category;
	
	private double price;
	
	@Column(name = "available_quantity")
	private long availableQuantity;
	
	@Column(name = "published_year")
	private int publishedYear;
	
	
	@Column(name = "status")
	private String status;
	
	public BookManagement() {
		
	}
	

	public long getBookID() {
		return bookID;
	}


	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	
	
	
	


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "BookManagement [bookID=" + bookID + ", ISBN=" + ISBN + ", bookTitle=" + bookTitle + ", author=" + author
				+ ", category=" + category + ", price=" + price + ", availableQuantity=" + availableQuantity
				+ ", publishedYear=" + publishedYear + "]";
	}


	
	

	
	
	


}
