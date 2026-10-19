package com.techiesedge.library.service;

import java.util.List;

import com.techiesedge.library.DAO.MemberManagementDAO;
import com.techiesedge.library.DAO.MemberManagementDAOImpl;
import com.techiesedge.library.Entity.MemberManagement;

public class MemberServiceImpl implements MemberService{
	
	MemberManagementDAO member = new MemberManagementDAOImpl();
	
	
	@Override
	public boolean register(MemberManagement m) {
		
		return member.register(m);
	}
	
	@Override
	public List<MemberManagement> viewAllMembers(){
		
		return member.viewAllMembers();
	}
	
	@Override
	public List<MemberManagement> searchMember(int memberID, String name, String email,String phone){
		
		return member.searchMember(memberID, name, email, phone);
	}
	
	@Override
	public boolean updateMembers(int memberId,String phone,String address) {
		
		return member.updateMembers(memberId, phone, address);
	}
	
	@Override
	public boolean deleteMember(int id) {
		
		return member.deleteMember(id);
	}
	
	@Override
	public MemberManagement getMemberByEmail(String email) {
		
		return member.getMemberByEmail(email);
	}



}
