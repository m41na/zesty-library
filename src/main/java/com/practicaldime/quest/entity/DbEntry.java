package com.practicaldime.quest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DbEntry {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String eventType;
	private String payload;
	@ManyToOne
	@JoinColumn(name="done_by")
	private Profile initiator;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	public DbEntry() {
		super();
	}

	public DbEntry(String eventType, String payload, Profile initiator, Date dateTime) {
		super();
		this.eventType = eventType;
		this.payload = payload;
		this.initiator = initiator;
		this.dateTime = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Profile getInitiator() {
		return initiator;
	}

	public void setInitiator(Profile initiator) {
		this.initiator = initiator;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
