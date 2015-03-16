package org.koushik.hibernate;

import java.util.List;
import org.javabrains.koushik.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

public class HibernateTest 
{

	public static void main(String [] args)
	{
		UserDetails user1 = new UserDetails(1, "First User");
		UserDetails user2 = new UserDetails(2, "Second User");
		UserDetails user3 = new UserDetails(3, "Third User");
		
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.getTransaction().commit();
		
		Query query = session.createQuery("from UserDetails");
        List list = query.list();
        for(int i = 0; i < list.size(); i++)
        {
        	UserDetails us = (UserDetails)list.get(i);
        	System.out.println(us.getUserId() + " " + us.getUserName());
        }
        	
	}
}
