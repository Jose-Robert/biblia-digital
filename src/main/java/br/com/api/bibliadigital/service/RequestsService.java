package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.PeriodRequests;

public interface RequestsService {

    Object findAllRequestsPeriod(String range);
    PeriodRequests findRequisitionsByPeriod(String range);
}
