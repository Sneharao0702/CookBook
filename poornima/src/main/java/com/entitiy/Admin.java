package com.entitiy;

	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.Column;

	@Entity
	public class Admin {

	    @Id
	    @Column(nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    // Getters and setters
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}


