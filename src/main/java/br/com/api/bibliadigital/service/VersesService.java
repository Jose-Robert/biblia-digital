package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.dto.VersesRequestTO;
import br.com.api.bibliadigital.model.dto.VersesResponseTO;
import br.com.api.bibliadigital.model.dto.VersesV2;
import br.com.api.bibliadigital.model.Verse;

public interface VersesService {

    VersesV2 findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter);
    Verse findVerseByChapter(String version, String abbrev, Integer chapter, Integer number);
    Verse findVerseRandom(String version);
    Verse findVerseByBookRandom(String version, String abbrev);
    VersesResponseTO searchByWord(VersesRequestTO request);
}
