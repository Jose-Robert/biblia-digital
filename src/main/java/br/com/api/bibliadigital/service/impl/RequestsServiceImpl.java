package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.integration.ConsumerRequestsEndpoints;
import br.com.api.bibliadigital.model.PeriodRequests;
import br.com.api.bibliadigital.service.RequestsService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class RequestsServiceImpl implements RequestsService {

    private final ConsumerRequestsEndpoints requestsEndpoints;
    private final Gson gson;

    public RequestsServiceImpl(ConsumerRequestsEndpoints requestsEndpoints, Gson gson) {
        this.requestsEndpoints = requestsEndpoints;
        this.gson = gson;
    }

    @Override
    public Object findAllRequestsPeriod(String range) {
        String ranges = requestsEndpoints.getAllPeriodRequests(range);
        return gson.fromJson(ranges, Object.class);
    }

    @Override
    public PeriodRequests findRequisitionsByPeriod(String range) {
        String ranges = requestsEndpoints.getRequisitionsByPeriod(range);
        return gson.fromJson(ranges, PeriodRequests.class);
    }
}
