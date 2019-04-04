package com.practicaldime.quest.entity;

import java.util.Date;

public class QueFilter {

	private Long authorId;
	private Date from;
	private Date until;
	private Integer start;
	private Integer limit;
	private String content;
	
	public QueFilter() {
		super();
	}

	public QueFilter(Long authorId, Date from, Date until, Integer start, Integer limit, String content) {
		super();
		this.authorId = authorId;
		this.from = from;
		this.until = until;
		this.start = start;
		this.limit = limit;
		this.content = content;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
