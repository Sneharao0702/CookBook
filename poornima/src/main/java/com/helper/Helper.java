package com.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Helper {
	static SessionFactory sf;
	
	static {
		
		try {
			sf= new Configuration().configure().buildSessionFactory();
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}
	public static SessionFactory getSessionFactory() {
		return sf;
	}
}
