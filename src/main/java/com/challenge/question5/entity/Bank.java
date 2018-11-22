package com.challenge.question5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank {
	
	public Bank() {		
	}
	
	public Bank(Long identifier, String name) {
		super();
		this.identifier = identifier;
		this.name = name;
	}

	@Id
	private Long identifier;
	
	private String name;
	
	
	public Long getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
