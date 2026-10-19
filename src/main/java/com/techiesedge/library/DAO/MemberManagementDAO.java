package com.techiesedge.library.DAO;

import java.util.List;


import com.techiesedge.library.Entity.MemberManagement;

public interface MemberManagementDAO {
	
	boolean register(MemberManagement member);
	
	List<MemberManagement> viewAllMembers();
	
	List<MemberManagement> searchMember(int memberID, String name, String email,String phone) ;
	
//	boolean updateMembers(int memberId,String memberName,String email,String phone,String address);
	
	boolean updateMembers(int memberId,String phone,String address);

	
	boolean deleteMember(int id) ;
	
	MemberManagement getMemberById(long memberID);

	MemberManagement getMemberByEmail(String email);
}
