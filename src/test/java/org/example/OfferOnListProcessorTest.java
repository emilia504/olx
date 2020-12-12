package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class OfferOnListProcessorTest {

    @Test
    void getOffersTest() throws IOException {
        OfferProcessor offerProcessor = new OfferProcessor();
        offerProcessor.getOffersPage(OfferProcessor.baseURL);
    }

}