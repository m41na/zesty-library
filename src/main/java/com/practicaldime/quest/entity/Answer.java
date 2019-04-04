package com.practicaldime.quest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String content;
    private String reason;
    private String reference;
    private Integer points;

    public Answer() {
    	super();
    }

	public Answer(String answer) {
		super();
		this.content = answer;
	}

	public Answer(String answer, String reason, String reference, Integer points) {
		super();
		this.content = answer;
		this.reason = reason;
		this.reference = reference;
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String answer) {
		this.content = answer;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
