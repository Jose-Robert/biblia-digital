package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.Book;
import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DigitalBibleConsumerApi consumerApi;

    @Override
    public List<Book> findAllBooks() {
        var booksJson = consumerApi.getBooks();
        Gson gson = new Gson();
        return gson.fromJson(booksJson, new TypeToken<List<Book>>(){}.getType());
    }

    @Override
    public Book findBookByAbbrev(String abbrev) {
        var booksJson = consumerApi.getBookByAbbrev(abbrev);
        Gson gson = new Gson();
        return gson.fromJson(booksJson, Book.class);
    }
}
