package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class BooksRestController {

    @Autowired
    private DigitalBibleConsumerApi consumerApi;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBooks() {
        var books = consumerApi.getBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(name = "/{abbrev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookForAbbrev(@PathVariable(name = "abbrev") String abbrev) {
        var book = consumerApi.getBookForAbbrev(abbrev);
        return ResponseEntity.ok().body(book);
    }

}
