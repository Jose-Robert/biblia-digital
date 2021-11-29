package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.dto.VersesRequestTO;
import br.com.api.bibliadigital.model.dto.VersesResponseTO;
import br.com.api.bibliadigital.model.dto.VersesV2;
import br.com.api.bibliadigital.model.Verse;
import br.com.api.bibliadigital.service.VersesService;
import br.com.api.bibliadigital.integration.ConsumerVersesEndpoints;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersesServiceImpl implements VersesService {

    @Autowired
    private ConsumerVersesEndpoints versesEndpoints;
    private final Gson gson;

    public VersesServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public VersesV2 findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter) {
        String versesJson = versesEndpoints.getAllVersesAndDetailsOfChapter(version.toLowerCase(),
                abbrev.toLowerCase(), chapter);
        return gson.fromJson(versesJson, VersesV2.class);
    }

    @Override
    public Verse findVerseByChapter(String version, String abbrev, Integer chapter, Integer number) {
        String verseJson = versesEndpoints.getVerseByChapter(version.toLowerCase(),
                abbrev.toLowerCase(), chapter, number);
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public Verse findVerseRandom(String version) {
        String verseJson = versesEndpoints.getVerseRandom(version.toLowerCase());
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public Verse findVerseByBookRandom(String version, String abbrev) {
        String verseJson = versesEndpoints.getVerseByBookRandom(version.toLowerCase(), abbrev.toLowerCase());
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public VersesResponseTO searchByWord(VersesRequestTO request) {
        String json = versesEndpoints.searchByWord(request);
        return gson.fromJson(json, VersesResponseTO.class);
    }
}
