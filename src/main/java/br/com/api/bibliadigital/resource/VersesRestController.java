package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.VersesTO;
import br.com.api.bibliadigital.model.Verse;
import br.com.api.bibliadigital.service.VersesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/verses")
public class VersesRestController {

    @Autowired
    private VersesService versesService;

    @GetMapping(value = "/{version}/{abbrev}/{chapter}")
    public ResponseEntity<VersesTO> findAllVersesAndDetailsOfChapter(@PathVariable("version") String version,
                                                                     @PathVariable("abbrev") String abbrev,
                                                                     @PathVariable("chapter") Integer chapter) {
        VersesTO versesTO = versesService.findAllVersesAndDetailsOfChapter(version, abbrev, chapter);
        return ResponseEntity.ok().body(versesTO);
    }

    @GetMapping(value = "/{version}/{abbrev}/{chapter}/{number}")
    public ResponseEntity<Verse> findAllVersesAndDetailsOfChapter(@PathVariable("version") String version,
                                                                  @PathVariable("abbrev") String abbrev,
                                                                  @PathVariable("chapter") Integer chapter,
                                                                  @PathVariable("number") Integer number) {
        Verse verse = versesService.findVerseByChapter(version, abbrev, chapter, number);
        return ResponseEntity.ok().body(verse);
    }
}
