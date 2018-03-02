package edu.mum.cs544;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao implements IBookDao {
	private static int idCount = 1;
	private Map<Integer, Book> books = new HashMap<>();

	public BookDao() {
		add(new Book("Spring MVC 3", "111-1111111110", "Jame Smith", 23.99, LocalDate.of(2018, Month.APRIL, 12)));
		add(new Book("Spring Boot for Dummy", "111-1111111111", "Jane Doe", 88.99,  LocalDate.of(2018, Month.AUGUST, 15)));
	}

	@Override
	public List<Book> getAll() {
		return new ArrayList<Book>(books.values());
	}

	@Override
	public void add(Book book) {
		synchronized (books) {
			book.setId(idCount);
			books.put(idCount, book);
			idCount++;			
		}
	}

	@Override
	public Book get(int id) {
		Book result = books.get(id);

		if (result == null) {
			throw new NoSuchResourceException("Book", id);
		}

		return result;
	}

	@Override
	public void update(int bookId, Book book) {
		books.put(bookId, book);
	}

	@Override
	public void delete(int bookId) {
		Book removed = books.remove(bookId);
		if (removed == null) {
			throw new NoSuchResourceException("Book", bookId);
		}
	}
}
