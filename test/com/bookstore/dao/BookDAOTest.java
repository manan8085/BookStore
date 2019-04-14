package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Core Java");
		category.setCategoryId(3);

		newBook.setCategory(category);
		newBook.setTitle("Effective Java(2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		// newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "E:\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);

		assertTrue(createdBook.getBookId() > 0);
	}

	@Test

	public void testUpdateBook() throws IOException, ParseException {
		Book newBook = new Book();
		newBook.setBookId(1);

		Category category = new Category("Java Basics");
		category.setCategoryId(1);

		newBook.setCategory(category);
		newBook.setTitle("Effective Java(3rd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(40f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "E:\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		newBook.setImage(imageBytes);

		Book updatedBook = bookDao.update(newBook);

		assertEquals(updatedBook.getTitle(), "Effective Java(3rd Edition)");
	}

	@Test(expected = EntityNotFoundException.class)

	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);

	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		bookDao.delete(bookId);
		assertTrue(true);
	}

	@Test()
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}

	@Test()
	public void testGetBookSuccess() {
		Integer bookId = 3;
		Book book = bookDao.get(bookId);
		assertNotNull(book);
	}

	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();

		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + " - " + aBook.getAuthor());
		}
		assertFalse(listBooks.isEmpty());
	}

	@Test
	public void testCreate2ndBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Core Java");
		category.setCategoryId(3);

		newBook.setCategory(category);
		newBook.setTitle("Java 8 in Action");
		newBook.setAuthor("Alan Mycroft");
		newBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);

		String imagePath = "E:\\books\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);

		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book book = bookDao.findByTitle(title);

		assertNull(book);
	}

	@Test
	public void testFindByTitleExist() {
		String title = "Java 8 in Action";
		Book book = bookDao.findByTitle(title);

		System.out.println(book.getAuthor());
		assertNotNull(book);
	}

	@Test
	public void testCount() {

		long totalBooks = bookDao.count();
	    assertEquals(2,totalBooks); 
	}
}
