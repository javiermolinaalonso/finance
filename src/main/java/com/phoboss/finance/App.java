package com.phoboss.finance;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.phoboss.finance.entities.Dividend;
import com.phoboss.finance.entities.Split;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Split.class).addAnnotatedClass(Dividend.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Dividend d = new Dividend("PHB4", new Date(), 10d);
		Split split = new Split("PHB4", new Date(), 3, 2);
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.persist(split);
		s.persist(d);
		t.commit();
		s.close();
	}
}
