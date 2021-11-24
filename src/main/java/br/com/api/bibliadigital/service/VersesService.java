package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.VersesTO;
import br.com.api.bibliadigital.model.Verse;

public interface VersesService {

    VersesTO findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter);
    Verse findVerseByChapter(String version, String abbrev, Integer chapter, Integer number);
}
