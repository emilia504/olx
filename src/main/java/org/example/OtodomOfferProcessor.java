package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class OtodomOfferProcessor implements OfferDetailsProcessor {

    public OfferDetails getOfferDetails(String link) throws IOException {
        Document document = Jsoup.connect(link).get();
        Map<String, String> details = getDetails(document);
        String location = getLocation(document);
        String floor = getFloor(details);
        String area = getArea(details);
        String rooms = getRooms(details);
        Integer rentAdditional = getRentAdditional(details);
        String description = getDescription(document);
        List<String> photos = getOfferPhotos(document);
        return OfferDetails.builder()
                .area(area)
                .description(description)
                .floor(floor)
                .rooms(rooms)
                .rentAdditional(rentAdditional)
                .location(location)
                .photos(photos)
                .build();
    }

    private Map<String, String> getDetails(Document document) {
        Element details = document.getElementsByClass("css-2wxlkt").first();
        Elements elements = details.getElementsByAttribute("title");
        Map<String, String> map = new HashMap<>();
        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
            map.put(it.next().text(), it.next().text());
        }
        return map;
    }

    private String getDescription(Document document) {
        Element element = document.getElementsByTag("p").first();
        return element.text();
    }

    private String getRooms(Map<String, String> details) {
        String rooms = "";
        if (details.containsKey("Liczba pokoi:")) {
            rooms = details.get("Liczba pokoi:");
        }
        return rooms;
    }

    private String getArea(Map<String, String> details) {
        String area = "";
        if (details.containsKey("Powierzchnia:")) {
            area = details.get("Powierzchnia:");
        }
        return area;
    }

    private String getFloor(Map<String, String> details) {
        String floor = "";
        if (details.containsKey("Piętro:")) {
            floor = details.get("Piętro:");
        }
        return floor;
    }

    private Integer getRentAdditional(Map<String, String> details) {
        String rentAdditional = "";
        if (details.containsKey("Czynsz - dodatkowo:")) {
            rentAdditional = details.get("Czynsz - dodatkowo:");
            String price = rentAdditional.substring(0, rentAdditional.length() - 3)
                    .replace(" ", "");
            return Integer.parseInt(price);
        }
        return 0;
    }

    private List<String> getOfferPhotos(Document document) {
        Element element = document.getElementById("__NEXT_DATA__");
        String script = element.html();
        int index;
        index = script.indexOf("\"images\":[{");
        String images = script.substring(index);
        index = images.indexOf("}]");
        images = images.substring(0, index);
        String[] imagesItems = images.split(",");
        List<String> photos = new ArrayList<>();
        for (int i = 3; i < imagesItems.length; i = i + 5) {
            String[] largeItem = imagesItems[i].split("\":\"");
            String link = largeItem[1].split(";")[0];
            photos.add(link);
        }
        return photos;
    }

    private String getLocation(Document document) {
        Element element = document.getElementsByAttributeValue("aria-label", "Adres").first();
        return element.text();
    }

}
