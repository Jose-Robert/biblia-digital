package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Book;
import br.com.api.bibliadigital.service.BookService;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BooksRestController {

    @Autowired
    private BookService bookService;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    @ApiOperation(value = "Buscar Livros", notes = "Esse metódo busca todos os livros")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks(HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        var books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @ApiOperation(value = "Buscar Livro Específico", notes = "Esse metódo busca um livros específico")
    @GetMapping(value = "/{abbrev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookByAbbrev(@PathVariable(name = "abbrev") String abbrev,
                                                HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        var book = bookService.findBookByAbbrev(abbrev);
        return ResponseEntity.ok().body(book);
    }

}
