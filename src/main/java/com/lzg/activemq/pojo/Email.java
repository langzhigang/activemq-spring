package com.lzg.activemq.pojo;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable{

	private static final long serialVersionUID = -4964587708316775849L;
	
	private String title;
	private Date sendDate;
	private String content;
	private int wordCount;
	
	public Email() {}
	
	public Email(String title, Date sendDate, String content, int wordCount) {
		super();
		this.title = title;
		this.sendDate = sendDate;
		this.content = content;
		this.wordCount = wordCount;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	@Override
	public String toString() {
		return "title: "+title+" sendDate: "+sendDate+" content: "+content+" wordCount: "+wordCount;
	}
}
