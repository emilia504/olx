package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OlxOfferProcessorTest {

    @Test
    void olxOfferWithPhotoTest() throws IOException {
        String link = "https://www.olx.pl/d/oferta/dwupokojowe-mieszkanie-46-mkw-przy-klifie-w-gdyni-CID3-IDHdGJn.html#827e686651;promoted";
        OlxOfferProcessor olxOfferProcessor = new OlxOfferProcessor();
        OfferDetails offerDetails = olxOfferProcessor.getOfferDetails(link);
        assertNotNull(offerDetails);
        System.out.println(offerDetails.toString());
    }

    @Test
    void olxOfferWithoutPhotoTest() throws IOException {
        String link = "https://www.olx.pl/d/oferta/wynajme-mieszkanie-gdynia-orlowo-CID3-IDImKGp.html#827e686651";
        OlxOfferProcessor olxOfferProcessor = new OlxOfferProcessor();
        OfferDetails offerDetails = olxOfferProcessor.getOfferDetails(link);
        assertNotNull(offerDetails);
        System.out.println(offerDetails.toString());
    }

}