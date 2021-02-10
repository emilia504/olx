package org.example;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route
@StyleSheet("my.css")
public class OfferView extends VerticalLayout implements AfterNavigationObserver {

    Grid<OfferDetails> grid = new Grid<>();

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        List<OfferDetails> offers = new ArrayList();
        OfferDetails o1 = new OfferDetails("T1", 10, "D1", "1", 1, "1", null, "1", "1");
        OfferDetails o2 = new OfferDetails("T2", 20, "D2", "2", 2, "2", null, "2", "2");
        offers.add(o1);
        offers.add(o2);
        grid.setItems(offers);
    }

    @Autowired
    public OfferView(OfferProcessor offerProcessor) throws IOException {
        grid.addComponentColumn(this::getGridRow);
        setSizeFull();
        add(grid);
    }

    private HorizontalLayout getGridRow(OfferDetails offerDetails) {
        HorizontalLayout gridrow = new HorizontalLayout();
        gridrow.getThemeList().add("spacing-s");

        VerticalLayout prices = new VerticalLayout();

        String price = offerDetails.getPrice().toString();
        prices.add(new Span(price));
        if (offerDetails.getRentAdditional() != null) {
            price = offerDetails.getRentAdditional().toString();
            prices.add(new Span(price));
        }

        Image image = new Image("https://img.pakamera.net/i1/1/579/obrazy-i-plakaty-12320957_2087022579.jpg", "alt");
        image.addClassName("thumbnail");


        Span title = new Span(offerDetails.getTitle());
        title.addClassName("header");

        gridrow.add(image);
        gridrow.add(title);
        gridrow.add(prices);

        return gridrow;
    }

}
