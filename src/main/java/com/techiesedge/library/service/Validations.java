package com.techiesedge.library.service;

import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.Entity.IssueManagement;
import com.techiesedge.library.Entity.MemberManagement;

public class Validations {
	
	public static void validateBookTitle(String bookTitle) {
		
		if(bookTitle == null || bookTitle.isBlank()) {
			
			throw new IllegalArgumentException("book title shouldn't be empty");
		}
	}
	
	public static void validateIsbn(String ISBN) {
		
		if(ISBN == null || ISBN.isBlank()) {
			
			throw new IllegalArgumentException("Isbn shouldn't be empty");
		}
		
		BookService bookCon = new BookServiceImpl();
		
		BookManagement book = bookCon.getBookByISBN(ISBN);
		
		if(book != null) {
			
			throw new IllegalArgumentException("Book already exists");
		}
			
			
	}
	
	public static void validateBook(BookManagement book) {
		
		if(book == null) {
			
			throw new IllegalArgumentException("Book not found");
		}
	}
	
	public static void validateMember(MemberManagement member) {
		
		if(member == null) {
			
			throw new IllegalArgumentException("member not found");
		}
	}
	
	public static void validateAuthor(String author) {
		
		if(author == null || author.isBlank()) {
			
			throw new IllegalArgumentException("author shouldn't be empty");
		}
	}
	
	public static void validateBookQuantity(long bookQuantity) {
		
		if(bookQuantity <= 0) {
			
			throw new IllegalArgumentException("BookQuantity shouldn't be zero");
		}
	}
	
	public static void validateMemberName(String memberName) {
		
		if(memberName == null || memberName.isBlank()) {
			
			throw new IllegalArgumentException("member name shouldn't be empty");
		}
	}
	
	 public static void validateEmail(String email) {
	    	
	        if (email == null || email.isBlank()) {
	            throw new IllegalArgumentException(
	                    "Email should not be empty");
	        }

	        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	            throw new IllegalArgumentException(
	                    "Invalid email format");
	        }
	        
	        MemberService b = new MemberServiceImpl();
	        
	        MemberManagement member = b.getMemberByEmail(email);
	        
	        if(member != null) {
	        	
	        	throw new IllegalArgumentException("email already exists");
	        }
	        

	    }
	 
	 public static void validatePhone(String phone) {
	        if (phone == null || !phone.matches("[6-9][0-9]{9}")) {
	            throw new IllegalArgumentException(
	                    "Invalid phone number");
	        }
	    }
	 
	 public static void validateAddress(String address) {
		 
		 if(address == null || address.isBlank()) {
			 
			 throw new IllegalArgumentException("address shouldn't be empty");
		 }
	 }
	


}
