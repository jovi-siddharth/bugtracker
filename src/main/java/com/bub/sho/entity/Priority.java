package com.bub.sho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="priority")
@Audited
public class Priority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="priority")
	private Integer value;
	
	public Priority() {
		
	}

	public Priority(Integer priority) {
		super();
		this.value = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer priority) {
		this.value = priority;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
