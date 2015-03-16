package org.koushik.hibernate;


import java.io.IOException;
import java.util.List;
import org.javabrains.koushik.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class webApp extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		resp.getWriter().println("Hello World!");
		UserDetails user1 = new UserDetails(1, "First User");
		UserDetails user2 = new UserDetails(2, "Second User");
		UserDetails user3 = new UserDetails(3, "Third User");
		
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.getTransaction().commit();
		
		Query query = session.createQuery("from UserDetails where userId = 1");
        List list = query.list();
        //resp.getWriter().println("Second printing");
        for(int i = 0; i < list.size(); i++)
        {
        	UserDetails us = (UserDetails)list.get(i);
        	resp.getWriter().println(us.getUserId() + " " + us.getUserName());
        }
        resp.getWriter().println("Extracted info from the database!");
       
	}
}
