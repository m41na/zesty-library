package com.practicaldime.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practicaldime.library.dao.AuthorRepository;
import com.practicaldime.library.dao.BookRepository;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookRepository bookDao;
	@Autowired
	private AuthorRepository authorDao;
	
	@Override
	public void save(Author author) {
		authorDao.save(author);
	}

	@Override
	public List<Author> listAuthors() {
		return authorDao.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Book book) {
		Author author = authorDao.findOne(book.getAuthor().getId());
		book.setAuthor(author);
		bookDao.save(book);
	}

	@Override
	public List<Book> listBooks() {
		return bookDao.findAll();
	}

	@Override
	public Book findBook(Long id) {
		return bookDao.findOne(id);
	}

	@Override
	public int deleteBook(Long id) {
		return bookDao.delete(id);
	}

	@Override
	public int updateBook(int pages, Long id) {
		return bookDao.update(pages, id);
	}

	@Override
	public Long countBooks() {
		return bookDao.count();
	}

	@Override
	public Author findAuthor(Long id) {
		return authorDao.findOne(id);
	}

	@Override
	public Long countAuthors() {
		return authorDao.count();
	}
}
