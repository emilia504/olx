package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OlxOfferProcessorTest {

    @Test
    void olxOfferWithPhotoTest() throws IOException {
        String link = "https://www.olx.pl/oferta/mieszkanie-dwupokojowe-przy-skm-gdynia-orlowo-CID3-IDHJJGz.html#2244b69db2;promoted";
        OlxOfferProcessor olxOfferProcessor = new OlxOfferProcessor();
        OfferDetails offerDetails = olxOfferProcessor.getOfferDetails(link);
        assertNotNull(offerDetails);
        System.out.println(offerDetails.toString());
    }

    @Test
    void olxOfferWithoutPhotoTest() throws IOException {
        String link = "https://www.olx.pl/oferta/mieszkanie-w-zamian-za-drobne-prace-porzadkowe-w-domu-CID3-IDFQz71.html#f3eb41946d";
        OlxOfferProcessor olxOfferProcessor = new OlxOfferProcessor();
        OfferDetails offerDetails = olxOfferProcessor.getOfferDetails(link);
        assertNotNull(offerDetails);
        System.out.println(offerDetails.toString());
    }

}