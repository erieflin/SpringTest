package com.elliot.user;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private static final long serialVersionUID = 2396654715019746670L;
	
	String id;
	String username;
	String password;

	@JsonCreator
	public
	User(@JsonProperty("id") final String id, @JsonProperty("username") final String username,
			@JsonProperty("password") final String password) {
		super();
		this.id = requireNonNull(id);
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
	}

	
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@JsonIgnore
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
