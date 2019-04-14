package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {

		Users user1=new Users();
		user1.setEmail("you@gmail.com");
		user1.setFullName("Mr President");
		user1.setPassword("power");
		
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");

		EntityManager entityManager=createEntityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		createEntityManagerFactory.close();
		
	
		System.out.println("A users object was persisted");
	}

}
