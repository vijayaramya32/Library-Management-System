package com.techiesedge.library.DAO;

import java.time.LocalDate;
import java.util.List;

import com.techiesedge.library.Entity.IssueManagement;

public interface IssueManagementDAO {
	
//	boolean issueBook(IssueManagement issue,long bookID,long memberID);
	
	boolean issueBook(List<Long> bookIDs,long memberID);
	
	
//	boolean returnBook(long memberID,long issueID);
	
	boolean returnBook(long memberID,List<Long> issueIDs);
//	boolean returnBook(List<Integer> issueID);
	
	List<Object[]> viewIssuedBooks();
	
	


}
