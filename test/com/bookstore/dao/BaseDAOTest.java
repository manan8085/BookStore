package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {
	protected static EntityManagerFactory createEntityManagerFactory;
	protected static EntityManager entityManager;
	
	public static void setUpBeforeClass() throws Exception{
		createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");

		entityManager = createEntityManagerFactory.createEntityManager();
	}
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		createEntityManagerFactory.close();
	}
}
