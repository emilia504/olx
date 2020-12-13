package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class OlxRestController {

    @Autowired
    OfferProcessor offerProcessor;

    @RequestMapping("/")
    public List<OfferOnList> getOfferList() throws IOException {
        return offerProcessor.getOffersPage(OfferProcessor.baseURL);
    }

}
