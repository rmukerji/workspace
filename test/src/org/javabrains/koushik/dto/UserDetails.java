package org.javabrains.koushik.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //treat this class as an entity class
public class UserDetails 
{
	@Id //treates userId as primary key
	private int userId;
	private String userName;
	public UserDetails(int id, String name)
	{
		userId = id;
		userName = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}


