package com.techiesedge.library.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_management")
public class IssueManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private long issueID;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberManagement member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookManagement book;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
    
    @Column(name = "due_date")
    private LocalDate dueDate;

    public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	private String status;

    public IssueManagement() {
    }

    public long getIssueID() {
        return issueID;
    }

    public MemberManagement getMember() {
        return member;
    }

    public void setMember(MemberManagement member) {
        this.member = member;
    }

    public BookManagement getBook() {
        return book;
    }

    public void setBook(BookManagement book) {
        this.book = book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}