package com.techiesedge.library.DAO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.Entity.IssueManagement;
import com.techiesedge.library.Entity.MemberManagement;
import com.techiesedge.library.service.Validations;
import com.techiesedge.library.util.HibernateUtil;

public class IssueManagementDAOImpl implements IssueManagementDAO {

	@Override
	public boolean issueBook(List<Long> bookIDs, long memberID) {

		Transaction tx = null;

		try (Session session = HibernateUtil.getSessionfactory().openSession()) {

			tx = session.beginTransaction();
			
			for(long bookID : bookIDs) {

				String hql = "FROM IssueManagement i " + "WHERE i.member.memberID = :memberID "
						+ "AND i.book.bookID = :bookID " + "AND i.returnDate IS NULL";
	
				IssueManagement existing = session.createQuery(hql, IssueManagement.class)
						.setParameter("memberID", memberID).setParameter("bookID", bookID).uniqueResult();
	
				if (existing != null) {
	
					throw new IllegalArgumentException("Book already issued to you");
	
				}
	
				else {
	
					BookManagement book = session.get(BookManagement.class, bookID);
	
					Validations.validateBook(book);
	
					MemberManagement member = session.get(MemberManagement.class, memberID);
	
					Validations.validateMember(member);
					
					IssueManagement newIssue = new IssueManagement();
	
					book.setAvailableQuantity(book.getAvailableQuantity() - 1);
	
					Validations.validateBookQuantity(book.getAvailableQuantity());
	
					newIssue.setBook(book);
	
					newIssue.setMember(member);
	
					newIssue.setIssueDate(LocalDate.now());
	
					newIssue.setReturnDate(null);
	
					newIssue.setStatus("issued");
	
					newIssue.setDueDate(LocalDate.now().plusDays(7));
	
					session.persist(newIssue);
	
				}
				
			}
			
			tx.commit();
			
			return true;
		} catch (Exception e) {

			throw e;

		}

	}
	
	@Override 
	
//	boolean returnBook(long memberID,long issueID);  
	public boolean returnBook(long memberID,List<Long> issueIDs) {
	  
		  Transaction tx = null;
	  
		  try(Session session = HibernateUtil.getSessionfactory().openSession()){
	  
		  tx = session.beginTransaction();
		  
		  for(long issueID : issueIDs) {
	  
			  	String hql ="FROM IssueManagement i WHERE i.member.memberID = :memberID and i.issueID = :issueID and i.returnDate IS NULL";
	  
			  		IssueManagement issue = session.createQuery(hql,IssueManagement.class).setParameter("memberID",memberID).setParameter("issueID", issueID).uniqueResult();
	  
			  		if (issue == null) {
	  
			  				throw new IllegalArgumentException("Book not found");
	  
			  		} if(!issue.getStatus().equalsIgnoreCase("Issued")) {
	  
			  			return false; 
	  
			  		}
	  
			  		issue.setReturnDate(LocalDate.now()); 
			  		
			  		issue.setStatus("Returned");
	  
			  		issue.setDueDate(null);
	  
			  		BookManagement b = issue.getBook();
	  
			  		b.setAvailableQuantity(b.getAvailableQuantity() + 1);
	  
			  		Validations.validateBookQuantity(b.getAvailableQuantity());
	  
			  		
			  		
		  }
		  
		  tx.commit();
	  
		  return true;
	  
		  }catch(HibernateException e) {
	  
			  if(tx != null) {
	  
				  tx.rollback(); 
	           
	      }
	  
	  e.printStackTrace();
	  
	  return false;
	  
	  }
	  
}
	 

	/*
	 * @Override public boolean returnBook(List<Integer> issueID) {
	 * 
	 * Transaction tx = null;
	 * 
	 * try(Session session = HibernateUtil.getSessionfactory().openSession()){
	 * 
	 * tx = session.beginTransaction();
	 * 
	 * for (Integer issueId : issueID) {
	 * 
	 * IssueManagement issue = session.get(IssueManagement.class, issueId);
	 * 
	 * if (issue != null && issue.getStatus().equalsIgnoreCase("Issued")) {
	 * 
	 * issue.setReturnDate(LocalDate.now());
	 * 
	 * issue.setStatus("Returned");
	 * 
	 * issue.setDueDate(null);
	 * 
	 * BookManagement b = issue.getBook();
	 * 
	 * b.setAvailableQuantity(b.getAvailableQuantity() + 1);
	 * 
	 * Validations.validateBookQuantity(b.getAvailableQuantity());
	 * 
	 * 
	 * 
	 * 
	 * } }
	 * 
	 * tx.commit();
	 * 
	 * return true;
	 * 
	 * }catch(HibernateException e) {
	 * 
	 * if(tx != null) {
	 * 
	 * tx.rollback(); }
	 * 
	 * e.printStackTrace();
	 * 
	 * 
	 * } return false;
	 * 
	 * }
	 */
	@Override
	public List<Object[]> viewIssuedBooks() {

		try (Session session = HibernateUtil.getSessionfactory().openSession()) {

			String hql = "select issueID, member,book,issueDate,dueDate,returnDate,status FROM IssueManagement i ORDER BY i.issueID";

			List<Object[]> issuedBooks = session.createQuery(hql, Object[].class).getResultList();

			return issuedBooks;
		}
	}

}
