package com.practicaldime.quest.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Question {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column(name="que_content")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date createdOn;
    @ElementCollection
    @CollectionTable(name="Choices", joinColumns=@JoinColumn(name="que_id"))
    @Column(name="choice")
    private Set<String> choices = new HashSet<>();
    @ElementCollection
    @CollectionTable(name="Choices", joinColumns=@JoinColumn(name="que_id"))
    @Column(name="clue")
    private Set<String> clues = new HashSet<>();
    @ElementCollection
    @CollectionTable(name="Choices", joinColumns=@JoinColumn(name="que_id"))
    @Column(name="trick")
    private Set<String> tricks = new HashSet<>();
    @ManyToOne(cascade= {CascadeType.PERSIST } )
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private Profile createdBy;
    @OneToOne(cascade= {CascadeType.ALL } )
    @JoinColumn(name = "fk_answer", nullable = false, updatable = false)
    private Answer answer;

    public Question() {
    	super();
    }
    
    public Question(String content, String answer) {
    	super();
    	this.content = content;
    	this.answer = new Answer(answer);
    }

	public Question(String content, Set<String> choices, Set<String> clues, Profile createdBy, Answer answer) {
		super();
		this.content = content;
		this.choices = choices;
		this.clues = clues;
		this.createdBy = createdBy;
		this.answer = answer;
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

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Set<String> getChoices() {
		return choices;
	}

	public void setChoices(Set<String> choices) {
		this.choices = choices;
	}

	public Set<String> getClues() {
		return clues;
	}

	public void setClues(Set<String> clues) {
		this.clues = clues;
	}

	public Set<String> getTricks() {
		return tricks;
	}

	public void setTricks(Set<String> tricks) {
		this.tricks = tricks;
	}

	public Profile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Profile createdBy) {
		this.createdBy = createdBy;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
