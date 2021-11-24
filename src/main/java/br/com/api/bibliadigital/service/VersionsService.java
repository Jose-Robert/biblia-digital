package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.Versions;

import java.util.List;

public interface VersionsService {

    List<Versions> findAllVersions();
}
