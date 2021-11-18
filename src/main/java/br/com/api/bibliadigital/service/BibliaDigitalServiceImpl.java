package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliaDigitalServiceImpl implements BibliaDigitalService {


    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public Book findBookByAbbrev(String abbrev) {
        return null;
    }
}
