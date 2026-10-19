package com.techiesedge.library.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.Entity.IssueManagement;
import com.techiesedge.library.service.Validations;
import com.techiesedge.library.util.HibernateUtil;

public class BookManagementDAOImpl implements BookManagementDAO{
	
	public boolean addBook(BookManagement book) {
		
		Transaction tx = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
		
			System.out.println("DAO reached");
	        System.out.println("Book = " + book.getBookTitle());
			tx = session.beginTransaction();
			
			
			session.persist(book);
			System.out.println("persist completed");
			
			tx.commit();
			System.out.println("transaction committed");
			
			return true;
			
		}catch(HibernateException e) {
			
			if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
           
            return false;
		}
			
		
	}
	
	public  List<BookManagement> viewAllBooks() throws HibernateException{
		
		
		String hql = "FROM BookManagement WHERE status = 'active'"                                                                                           ;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
		
			List<BookManagement> books = session.createQuery(hql,BookManagement.class).getResultList();
			
			return books;
		
		
		}

	}
	
	public List<BookManagement> filter(String ISBN, String title,String author,String category) throws HibernateException{
		
		List<BookManagement> books;
		
		String hql = "FROM BookManagement WHERE 1 = 1";
		
		if(ISBN != null && !ISBN.isBlank()) {
			
			hql += "and ISBN = :ISBN";
		}
		
		if(title != null && !title.isBlank()) {
			
			hql += "and bookTitle = :title";
		}
		
		if(author != null && !author.isBlank()) {
			
			hql += "and author = :author";
		}
		
		if(category != null && !category.isBlank()) {
			
			hql += "and category = :category";
		}
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			Query<BookManagement> query =
	                session.createQuery(hql, BookManagement.class);

	        if (ISBN != null && !ISBN.isBlank()) {
	            query.setParameter("ISBN", ISBN);
	        }

	        if (title != null && !title.isBlank()) {
	            query.setParameter("title", title);
	        }

	        if (author != null && !author.isBlank()) {
	            query.setParameter("author", author);
	        }

	        if (category != null && !category.isBlank()) {
	            query.setParameter("category", category);
	        }

	        return query.getResultList();			
			
		}
	
	}
	
	public boolean updateDetails(int id,String author,String category, double price, int quantity){
				
		Transaction tx = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			tx = session.beginTransaction();
			
			String bookHql = "FROM BookManagement WHERE bookID = :id";
			
			BookManagement book = session.createQuery(bookHql, BookManagement.class).setParameter("id", id).getSingleResult();
			
//			if(title != null && !title.isBlank()) {
//				
//				book.setBookTitle(title);
//			}
			
			if(author != null && !author.isBlank()) {
				
				book.setAuthor(author);;
			}
			
			if(category != null && !category.isBlank()) {
				
				book.setCategory(category);
			}
			
			if(price > 0) {
				
				book.setPrice(price);
			}
			
			if(quantity >= 0) {
				
				book.setAvailableQuantity(quantity);
			}
			
			tx.commit();
			
				
			return true;
			
		}catch(HibernateException e) {
			
			if(tx != null) {
				
				tx.rollback();
			}
		return false;
		
		}
		
	}
		

		
	public boolean deleteBook(int id){
		
		Transaction tx = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			tx = session.beginTransaction();
			
			BookManagement book = session.get(BookManagement.class,id);
			
			Validations.validateBook(book);
			
			String hql = "from IssueManagement i WHERE i.book.bookID = :id and i.returnDate IS NULL ";
			
			IssueManagement issue = session.createQuery(hql,IssueManagement.class).setParameter("id",id).uniqueResult();
			
			if(issue != null) {
				
				throw new IllegalArgumentException("book is at someone else");
				
			}
			
			book.setStatus("INACTIVE");
			
			tx.commit();
			
			return true;

		}catch(HibernateException e) {
			
			if(tx != null) {
				
				tx.rollback();
				
				e.printStackTrace();
			}
		}
		return false;
		
		
	}
	
	public BookManagement getBookById(long bookId){
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			
			BookManagement book = session.get(BookManagement.class, bookId);
			
			return book;
		}catch(HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public BookManagement getBookByISBN(String isbn) {

	    try (Session session = HibernateUtil.getSessionfactory().openSession()) {

	        String hql = "FROM BookManagement WHERE ISBN = :isbn";

	        return session.createQuery(hql, BookManagement.class)
	                .setParameter("isbn", isbn)
	                .uniqueResult();

	    } catch (HibernateException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	
}
