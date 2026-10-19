package com.techiesedge.library.service;

import java.util.List;

import com.techiesedge.library.DAO.IssueManagementDAO;
import com.techiesedge.library.DAO.IssueManagementDAOImpl;
import com.techiesedge.library.Entity.IssueManagement;

public class IssueManagementServiceImpl implements IssueManagementService{
	
	IssueManagementDAO i = new IssueManagementDAOImpl();
	
//	public boolean issueBook(IssueManagement issue,long bookID,long memberID) {
	
	public boolean issueBook(List<Long> bookIDs,long memberID) {

		return i.issueBook(bookIDs, memberID);
	}
	
//	public boolean returnBook(long memberID,long issueID) {
//		
//		return i.returnBook(memberID,issueID);
//	}
	
	public boolean returnBook(long memberID,List<Long> issueIDs) {
		
		return i.returnBook(memberID,issueIDs);
		
	}
	
//	public boolean returnBook(List<Integer> issueID) {
//		
//		 return i.returnBook(issueID);
//	}
//	
	public List<Object[]> viewIssuedBooks(){
		
		return i.viewIssuedBooks();
	}

}
