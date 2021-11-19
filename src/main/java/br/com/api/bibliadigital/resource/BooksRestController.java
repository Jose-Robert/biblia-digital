package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Book;
import br.com.api.bibliadigital.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BooksRestController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks() {
        var books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/{abbrev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookByAbbrev(@PathVariable(name = "abbrev") String abbrev) {
        var book = bookService.findBookByAbbrev(abbrev);
        return ResponseEntity.ok().body(book);
    }

}
