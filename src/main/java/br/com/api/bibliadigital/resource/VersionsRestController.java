package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Versions;
import br.com.api.bibliadigital.service.VersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/versions", produces = MediaType.APPLICATION_JSON_VALUE)
public class VersionsRestController {

    @Autowired
    private VersionsService versionsService;

    @GetMapping
    public ResponseEntity<List<Versions>> findAllVersions() {
        List<Versions> versions = versionsService.findAllVersions();
        return ResponseEntity.ok().body(versions);
    }
}
