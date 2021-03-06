package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.PeriodRequests;
import br.com.api.bibliadigital.service.RequestsService;
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

@RestController
@RequestMapping(path = "/requests")
public class RequestsRestController {

    @Autowired
    private RequestsService requestsService;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    @ApiOperation(value = "Buscar todas requisições",
            notes = "Esse metódo busca todas requisições por periodo - Authenticated: Yes")
    @GetMapping(value = "/{range}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllRequestsPeriod(@PathVariable("range") String range,
                                                        HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        Object periodAllRequests = requestsService.findAllRequestsPeriod(range);
        return ResponseEntity.ok().body(periodAllRequests);
    }

    @ApiOperation(value = "Buscar requisições por quantia",
            notes = "Esse metódo busca requisições por quantia - Authenticated: Yes")
    @GetMapping(value = "/amount/{range}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PeriodRequests> findRequisitionsByPeriod(@PathVariable("range") String range,
                                                                   HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        PeriodRequests periodRequests = requestsService.findRequisitionsByPeriod(range);
        return ResponseEntity.ok().body(periodRequests);
    }
}
