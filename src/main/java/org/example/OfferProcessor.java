package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class OfferProcessor {

    public static String baseURL = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/lublin/?search%5Bfilter_float_price%3Ato%5D=1300&search%5Border%5D=filter_float_price%3Aasc&search%5Bfilter_enum_floor_select%5D%5B0%5D=floor_0&search%5Bfilter_enum_floor_select%5D%5B1%5D=floor_1&search%5Bfilter_enum_floor_select%5D%5B2%5D=floor_2&search%5Bfilter_enum_floor_select%5D%5B3%5D=floor_3&search%5Bfilter_enum_floor_select%5D%5B4%5D=floor_4&search%5Bfilter_enum_floor_select%5D%5B5%5D=floor_5&search%5Bfilter_enum_floor_select%5D%5B6%5D=floor_6&search%5Bfilter_enum_floor_select%5D%5B7%5D=floor_7&search%5Bfilter_enum_floor_select%5D%5B8%5D=floor_8&search%5Bfilter_enum_floor_select%5D%5B9%5D=floor_9&search%5Bfilter_enum_floor_select%5D%5B10%5D=floor_10&search%5Bfilter_enum_floor_select%5D%5B11%5D=floor_11&search%5Bfilter_enum_rooms%5D%5B0%5D=one&search%5Bfilter_enum_rooms%5D%5B1%5D=two";
    private final List<OfferOnList> offerOnLists = new ArrayList<>();

    List<OfferOnList> getOffersPage(String pageLink) throws IOException {
        Document document = Jsoup.connect(pageLink).get();
        Elements elements = document.getElementsByClass("offer-wrapper");
        for (Element element : elements) {
            if (isPromotedList(element))
                continue;
            OfferOnList offerOnList = getOfferOnList(element);
            offerOnLists.add(offerOnList);
        }
        return offerOnLists;
    }

    public String getPageNumber(Document document) {
        Element pageNumber = document.getElementsByAttributeValue("data-cy", "page-link-current").first();
        return pageNumber.text();
    }

    private OfferOnList getOfferOnList(Element element) {
        String title = getOfferTitle(element);
        Integer price = getOfferPrice(element);
        String offerLink = getOfferLink(element);
        String imageLink = getImageLink(element);
        return OfferOnList.builder()
                .title(title)
                .price(price)
                .offerLink(offerLink)
                .imageLink(imageLink)
                .build();
    }

    private String getImageLink(Element element) {
        Element imgLink = element.getElementsByTag("img").first();
        if (imgLink == null)
            return null;
        return imgLink.attr("src");
    }

    private String getOfferLink(Element element) {
        Element elink = element.getElementsByAttribute("href").first();
        return elink.attr("href");
    }

    public OfferDetails getOffer(String offerLink) throws IOException {
        OfferDetailsProcessor offerDetailsProcessor = setOfferDetailsProcessor(offerLink);
        if (offerDetailsProcessor != null)
            return offerDetailsProcessor.getOfferDetails(offerLink);
        return null;
    }

    private OfferDetailsProcessor setOfferDetailsProcessor(String offerLink) {
        if (offerLink.startsWith("https://www.olx"))
            return new OlxOfferProcessor();
        if (offerLink.startsWith("https://www.otodom"))
            return new OtodomOfferProcessor();
        return null;
    }

    private Integer getOfferPrice(Element element) {
        Element priceElem = element.getElementsByClass("price").first();
        String priceText = priceElem.text();
        String price = priceText.substring(0, priceText.length() - 3)
                .replace(" ", "");
        return Integer.parseInt(price);
    }

    private String getOfferTitle(Element element) {
        Element titleElem = element.getElementsByClass("title-cell").first();
        Element title = titleElem.getElementsByTag("a").first();
        return title.text();
    }

    private boolean isPromotedList(Element element) {
        return element.child(0).classNames().contains("promoted-list");
    }

    public Optional<String> getNextPageLink(Document document) {
        Element element = document.getElementsByAttributeValue("data-cy", "page-link-next").last();
        return Optional.ofNullable(element.attr("href"))
                .filter(Predicate.not(String::isBlank));
    }

}
