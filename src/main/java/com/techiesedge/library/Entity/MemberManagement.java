package com.techiesedge.library.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_management")
public class MemberManagement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private long memberID;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	private String address;
	
	@Column(name = "membership_date")
	private LocalDate membershipDate;
	
	@Column(name = "status")
	private String status;
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
		
	}

	public MemberManagement() {
	}


	public long getMemberID() {
		return memberID;
	}


	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getMembershipDate() {
		return membershipDate;
	}

	public void setMembershipDate(LocalDate membershipDate) {
		this.membershipDate = membershipDate;
	}
	
	
}
