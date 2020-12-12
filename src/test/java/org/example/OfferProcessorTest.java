package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class OfferProcessorTest {

    @Test
    void olxOfferProcessorTest() throws IOException {
        String link = "https://www.olx.pl/oferta/mieszkanie-dwupokojowe-przy-skm-gdynia-orlowo-CID3-IDHJJGz.html#2244b69db2;promoted";
        OlxOfferProcessor olxOfferProcessor = new OlxOfferProcessor();
        OfferDetails offerDetails = olxOfferProcessor.getOfferDetails(link);
        System.out.println(offerDetails.toString());
    }
}