package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.Book;

import java.util.List;

public interface BibliaDigitalService {

    List<Book> findAllBooks();
    Book findBookByAbbrev(String abbrev);
}
