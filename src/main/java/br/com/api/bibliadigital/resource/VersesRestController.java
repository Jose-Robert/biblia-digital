package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.dto.VersesRequestTO;
import br.com.api.bibliadigital.model.dto.VersesResponseTO;
import br.com.api.bibliadigital.model.dto.VersesV2;
import br.com.api.bibliadigital.model.Verse;
import br.com.api.bibliadigital.service.VersesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/verses", produces = MediaType.APPLICATION_JSON_VALUE)
public class VersesRestController {

    @Autowired
    private VersesService versesService;

    @GetMapping(value = "/{version}/{abbrev}/{chapter}")
    public ResponseEntity<VersesV2> findAllVersesAndDetailsOfChapter(@PathVariable("version") String version,
                                                                     @PathVariable("abbrev") String abbrev,
                                                                     @PathVariable("chapter") Integer chapter) {
        VersesV2 versesTO = versesService.findAllVersesAndDetailsOfChapter(version, abbrev, chapter);
        return ResponseEntity.ok().body(versesTO);
    }

    @GetMapping(value = "/{version}/{abbrev}/{chapter}/{number}")
    public ResponseEntity<Verse> findVerseByChapter(@PathVariable("version") String version,
                                                    @PathVariable("abbrev") String abbrev,
                                                    @PathVariable("chapter") Integer chapter,
                                                    @PathVariable("number") Integer number) {
        Verse verse = versesService.findVerseByChapter(version, abbrev, chapter, number);
        return ResponseEntity.ok().body(verse);
    }

    @GetMapping(value = "/{version}/random")
    public ResponseEntity<Verse> findVerseRandom(@PathVariable("version") String version) {
        Verse verse = versesService.findVerseRandom(version);
        return ResponseEntity.ok().body(verse);
    }

    @GetMapping(value = "/{version}/{abbrev}/random")
    public ResponseEntity<Verse> findVerseByBookRandom(@PathVariable("version") String version,
                                                 @PathVariable("abbrev") String abbrev) {
        Verse verse = versesService.findVerseByBookRandom(version, abbrev);
        return ResponseEntity.ok().body(verse);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<VersesResponseTO> searchByWord(@RequestBody VersesRequestTO request) {
        VersesResponseTO response = versesService.searchByWord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
