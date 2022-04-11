package com.nisar.core.review.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;
 
@Entity
@Table(name ="reviews", indexes = {@Index(name="review_unique_idx",unique = true,columnList = "productId,reviewId")})
public class ReviewEntity {

	@Id
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	private int productId;
	private int reviewId;
	private String author;
	private String subject;
	private String content;
	public ReviewEntity() {
		
	}
	public ReviewEntity(int i, int j, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
