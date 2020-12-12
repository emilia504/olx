package org.example;

import java.io.IOException;

public interface OfferDetailsProcessor {

    OfferDetails getOfferDetails(String link) throws IOException;

}
