package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.Versions;
import br.com.api.bibliadigital.service.VersionsService;
import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerVersionsEndpointApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionsServiceImpl implements VersionsService {

    @Autowired
    private DigitalBibleConsumerVersionsEndpointApi versionsEndpoint;

    @Override
    public List<Versions> findAllVersions() {
        String jsonVersions = versionsEndpoint.getVersions();
        Gson gson = new Gson();
        return gson.fromJson(jsonVersions, new TypeReference<List<Versions>>(){}.getType());
    }
}
