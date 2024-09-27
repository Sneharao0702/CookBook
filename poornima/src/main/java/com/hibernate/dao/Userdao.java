package com.hibernate.dao;


import java.util.List;

import com.entitiy.User;

public interface Userdao{
    
	public void registeruser();
    
	public void viewuserById();
	public void edituser();
	List<User> getAlluser();
   
}

