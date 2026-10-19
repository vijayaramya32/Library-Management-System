package com.techiesedge.library.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.techiesedge.library.Entity.MemberManagement;

public interface MemberService {
	
	boolean register(MemberManagement member);
	
	List<MemberManagement> viewAllMembers();
	
	List<MemberManagement> searchMember(int memberID, String name, String email,String phone) ;
	
//	boolean updateMembers(int memberId,String memberName,String email,String phone,String address);
	
	boolean updateMembers(int memberId,String phone,String address);

	
	boolean deleteMember(int id) ;

	MemberManagement getMemberByEmail(String email);
}
