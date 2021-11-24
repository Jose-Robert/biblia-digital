package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.VersesTO;
import br.com.api.bibliadigital.model.Verse;
import br.com.api.bibliadigital.service.VersesService;
import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerVersesEndpointApi;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersesServiceImpl implements VersesService {

    @Autowired
    private DigitalBibleConsumerVersesEndpointApi versesEndpoint;

    @Override
    public VersesTO findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter) {
        String versesJson = versesEndpoint.getAllVersesAndDetailsOfChapter(version, abbrev, chapter);
        Gson gson = new Gson();
        return gson.fromJson(versesJson, VersesTO.class);
    }

    @Override
    public Verse findVerseByChapter(String version, String abbrev, Integer chapter, Integer number) {
        String verseJson = versesEndpoint.getVerseByChapter(version, abbrev, chapter, number);
        Gson gson = new Gson();
        return gson.fromJson(verseJson, Verse.class);
    }
}
