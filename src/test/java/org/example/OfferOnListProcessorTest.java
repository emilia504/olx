package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferOnListProcessorTest {

    @Test
    void getOffersTest() throws IOException {
        OfferProcessor offerProcessor = new OfferProcessor();
        List<OfferOnList> offerList = offerProcessor.getOffersPage(OfferProcessor.baseURL);
        assertFalse(offerList.isEmpty());
    }

}