package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Versions;
import br.com.api.bibliadigital.service.VersionsService;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/versions", produces = MediaType.APPLICATION_JSON_VALUE)
public class VersionsRestController {

    @Autowired
    private VersionsService versionsService;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    @ApiOperation(value = "Buscar todas as versões", notes = "Esse metódo busca todas as versões da biblía disponível")
    @GetMapping
    public ResponseEntity<List<Versions>> findAllVersions(HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        List<Versions> versions = versionsService.findAllVersions();
        return ResponseEntity.ok().body(versions);
    }
}
