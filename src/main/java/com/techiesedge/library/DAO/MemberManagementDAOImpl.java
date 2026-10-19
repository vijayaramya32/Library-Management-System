package com.techiesedge.library.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import com.techiesedge.library.Entity.IssueManagement;
import com.techiesedge.library.Entity.MemberManagement;
import com.techiesedge.library.service.Validations;
import com.techiesedge.library.util.HibernateUtil;

public class MemberManagementDAOImpl implements MemberManagementDAO{
	
	
	@Override
	public boolean register(MemberManagement member) {
		
		Transaction tx  = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			tx = session.beginTransaction();
			
			session.persist(member);
			
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
	
	@Override
	
	public List<MemberManagement> viewAllMembers() throws HibernateException{
		
		String hql = "From MemberManagement WHERE status = 'active'";
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			List<MemberManagement> members = session.createQuery(hql,MemberManagement.class).getResultList();
			
			return members;
			
			
		}
	}
	
	@Override
	
	public List<MemberManagement> searchMember(int memberID, String name, String email,String phone) throws HibernateException{
		
		String hql = "From MemberManagement WHERE 1=1";
		
		if(memberID > 0) {
			
			hql += "AND memberID = :memberID";

		}
		
		else if(name != null && !name.isBlank()) {
			
			hql += "AND memberName = :name";
		}
		
		else if(email != null && !email.isBlank()) {
			
			hql += "AND email = :email";
		}
		
		else if(phone != null && !phone.isBlank()) {
			
			hql += "AND phone = :phone";
		}
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			Query<MemberManagement> member = session.createQuery(hql,MemberManagement.class);
			
			
			if(memberID > 0) {
				
				member.setParameter("memberID" ,memberID);

			}
			
			if(name != null && !name.isBlank()) {
				
				member.setParameter("name" ,name);
			}
			
			if(email != null && !email.isBlank()) {
				
				member.setParameter("email" ,email);
			}
			
			if(phone != null && !phone.isBlank()) {
				
				member.setParameter("phone" ,phone);

			}
			
			List<MemberManagement> members = member.getResultList();
			
			return members;
		}
	}
	
	@Override
//	public boolean updateMembers(int memberId,String memberName,String email,String phone,String address){
	public boolean updateMembers(int memberId,String phone,String address){

		
		Transaction tx = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			tx = session.beginTransaction();
			
			MemberManagement member = session.get(MemberManagement.class, memberId);
			
//			if(memberName != null && !memberName.isBlank()) {
//				
//				member.setMemberName(memberName);
//			}
//			
//			if(email != null && !email.isBlank()) {
//				
//				member.setEmail(email);
//			}
			
			if(phone != null && !phone.isBlank()) {
				
				member.setPhone(phone);

			}
			
			if(address != null && !address.isBlank()) {
				
				member.setAddress(address);
			}
			
			tx.commit();
			
			return true;
		}catch(HibernateException e) {
			
			if(tx != null) {
				
				tx.rollback();
				e.printStackTrace();
			}
			
			return false;
		}
	}
	
	@Override
	public boolean deleteMember(int id) {
		
		Transaction tx = null;
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			tx = session.beginTransaction();
			
			MemberManagement member = session.get(MemberManagement.class, id);
			
			Validations.validateMember(member);
			
			String hql = "FROM IssueManagement WHERE member.memberID = :id and returnDate IS NULL";
			
			 IssueManagement issue = session.createQuery(hql, IssueManagement.class).setParameter("id", id) .setMaxResults(1).uniqueResult();			
			if(issue != null) {
				
				throw new IllegalArgumentException("you have to submit before deleting your account");
			}
			
			else {
				
				member.setStatus("inactive");
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
	
	@Override
	public MemberManagement getMemberById(long memberID) throws HibernateException {

	    try (Session session = HibernateUtil.getSessionfactory().openSession()) {

	        MemberManagement member = session.get(MemberManagement.class, memberID);

	        return member;

	    } catch (HibernateException e) {
	        throw e;
	    }
	    
	   
	    
	}
	
	@Override
	public MemberManagement getMemberByEmail(String email) throws HibernateException{
		
		try(Session session = HibernateUtil.getSessionfactory().openSession()){
			
			String hql = "FROM MemberManagement WHERE email = :email";

	        return session.createQuery(hql, MemberManagement.class)
	                .setParameter("email", email)
	                .uniqueResult();
			
		}catch(HibernateException e) {
			
			throw e;
		}
		
		
	}

	

}
