package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OtodomOfferProcessor implements OfferDetailsProcessor {

    public OfferDetails getOfferDetails(String link) throws IOException {
        Document document = Jsoup.connect(link).get();
        Elements elements = document.getElementsByClass("offer-details__param");
        String floor = getFloor(elements);
        String area = getArea(elements);
        String rooms = getRooms(elements);
        Integer rentAdditional = getRentAdditional(elements);
        String description = getDescription(document);
        List<String> photos = getOfferPhotos(document);
        return OfferDetails.builder()
                .area(area)
                .description(description)
                .floor(floor)
                .rooms(rooms)
                .rentAdditional(rentAdditional)
                .photos(photos)
                .build();
    }

    private String getDescription(Document document) {
        return document.getElementById("textContent").text();
    }

    private String getRooms(Elements elements) {
        Element element = elements.get(5).getElementsByClass("offer-details__value").first();
        return element.text();
    }

    private String getArea(Elements elements) {
        Element element = elements.get(4).getElementsByClass("offer-details__value").first();
        return element.text();
    }

    private String getFloor(Elements elements) {
        Element element = elements.get(1).getElementsByClass("offer-details__value").first();
        return element.text();
    }

    private Integer getRentAdditional(Elements elements) {
        Element element = elements.get(6).getElementsByClass("offer-details__value").first();
        String priceText = element.text();
        String price = priceText.substring(0, priceText.length() - 3)
                .replace(" ", "");
        return Integer.parseInt(price);
    }

    private List<String> getOfferPhotos(Document document) {
        Element element = document.getElementById("bigGallery");
        Elements elements = element.getElementsByAttribute("href");
        List<String> photos = new ArrayList<>();
        for (Element e : elements) {
            photos.add(e.attr("href"));
        }
        return photos;
    }
}
