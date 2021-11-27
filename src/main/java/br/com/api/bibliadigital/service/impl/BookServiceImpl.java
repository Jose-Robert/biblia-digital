package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.Book;
import br.com.api.bibliadigital.service.BookService;
import br.com.api.bibliadigital.integration.ConsumerBooksEndpoints;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private ConsumerBooksEndpoints booksEndpoints;
    private final Gson gson;

    public BookServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<Book> findAllBooks() {
        var booksJson = booksEndpoints.getBooks();
        return gson.fromJson(booksJson, new TypeToken<List<Book>>(){}.getType());
    }

    @Override
    public Book findBookByAbbrev(String abbrev) {
        var booksJson = booksEndpoints.getBookByAbbrev(abbrev.toLowerCase());
        return gson.fromJson(booksJson, Book.class);
    }
}
