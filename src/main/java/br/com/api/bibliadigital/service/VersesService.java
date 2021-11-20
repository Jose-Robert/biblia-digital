package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.VersesTO;

public interface VersesService {

    VersesTO findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter);
}
