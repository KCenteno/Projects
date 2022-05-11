package com.codingdojo.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="joys")
public class Joy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=2, message="Name must be more then 2 characters!")
	private String name;
	
	@NotEmpty
	@Size(min=3, message="Description must be more then 3 characters!")
	private String gender;
	
	@NotEmpty
	@Size(min=3, message="Description must be more then 3 characters!")
	private String origin;
	
	@NotEmpty
	@Column(columnDefinition="TEXT")
	@Size(min=10, message="Meaning is required! and at least 10 characters long!")
	private String meaning;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="join_table",
    		joinColumns = @JoinColumn(name="joy_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
    private List<User> users;

	public Joy() {
		super();
	}

	public Joy(@NotEmpty @Size(min = 2, message = "Name must be more then 2 characters!") String name,
			@NotEmpty @Size(min = 3, message = "Description must be more then 3 characters!") String gender,
			@NotEmpty @Size(min = 3, message = "Description must be more then 3 characters!") String origin,
			@NotEmpty @Size(min = 10, message = "Meaning is required! and at least 10 characters long!") String meaning,
			User user, List<User> users) {
		super();
		this.name = name;
		this.gender = gender;
		this.origin = origin;
		this.meaning = meaning;
		this.user = user;
		this.users = users;
	}

	public Joy(Long id, @NotEmpty @Size(min = 2, message = "Name must be more then 2 characters!") String name,
			@NotEmpty @Size(min = 3, message = "Description must be more then 3 characters!") String gender,
			@NotEmpty @Size(min = 3, message = "Description must be more then 3 characters!") String origin,
			@NotEmpty @Size(min = 10, message = "Meaning is required! and at least 10 characters long!") String meaning,
			Date createdAt, Date updatedAt, User user, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.origin = origin;
		this.meaning = meaning;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
