package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OtodomOfferProcessorTest {

    @Test
    void otodomOfferProcessorTest() throws IOException {
        String link = "https://www.otodom.pl/pl/oferta/polecam-na-wynajem-mieszkanie-2-pokojowe-na-lsm-ID2ZLR6.html#fcd425816e";
        OtodomOfferProcessor otodomOfferProcessor = new OtodomOfferProcessor();
        OfferDetails offerDetails = otodomOfferProcessor.getOfferDetails(link);
        assertNotNull(offerDetails);
        System.out.println(offerDetails.toString());
    }

}