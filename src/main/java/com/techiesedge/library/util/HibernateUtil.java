package com.techiesedge.library.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		
		try {
			
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("Hibernate initialized successfully!");
			
		}catch(HibernateException e) {
			
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	
	

}
