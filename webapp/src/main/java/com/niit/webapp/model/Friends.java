package com.niit.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Friends implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8964429062481384366L;

	@Id @GeneratedValue(strategy =GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(name="generator", sequenceName="FRIENDS_SEQ", allocationSize = 1)
	private int id;
	
	@Column(name="INITIATOR_ID")
	private int initiatorId;
	
	@Column(name="FRIEND_ID")
	private int friendId;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(int initiatorId) {
		this.initiatorId = initiatorId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
