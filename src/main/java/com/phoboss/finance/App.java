package com.phoboss.finance;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.phoboss.finance.entities.Price;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Price.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Price p = new Price(new Date(), 10d, 11d, 11d, 12d, 9d, 1123123123d, 10d, "PHB");
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.persist(p);
		t.commit();
		s.close();
		
	}
}
