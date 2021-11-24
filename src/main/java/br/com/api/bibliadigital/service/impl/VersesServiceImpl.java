package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.dto.VersesRequest;
import br.com.api.bibliadigital.model.dto.VersesResponse;
import br.com.api.bibliadigital.model.dto.VersesV2;
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
    public VersesV2 findAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter) {
        String versesJson = versesEndpoint.getAllVersesAndDetailsOfChapter(version, abbrev, chapter);
        Gson gson = new Gson();
        return gson.fromJson(versesJson, VersesV2.class);
    }

    @Override
    public Verse findVerseByChapter(String version, String abbrev, Integer chapter, Integer number) {
        String verseJson = versesEndpoint.getVerseByChapter(version, abbrev, chapter, number);
        Gson gson = new Gson();
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public Verse findVerseRandom(String version) {
        String verseJson = versesEndpoint.getVerseRandom(version);
        Gson gson = new Gson();
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public Verse findVerseByBookRandom(String version, String abbrev) {
        String verseJson = versesEndpoint.getVerseByBookRandom(version, abbrev);
        Gson gson = new Gson();
        return gson.fromJson(verseJson, Verse.class);
    }

    @Override
    public VersesResponse searchByWord(VersesRequest request) {
        String json = versesEndpoint.searchByWord(request);
        Gson gson = new Gson();
        return gson.fromJson(json, VersesResponse.class);
    }
}
