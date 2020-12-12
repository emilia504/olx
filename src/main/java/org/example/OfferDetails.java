package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class OfferDetails {

    private String title;
    private Integer price;
    private String description;
    private String floor;
    private Integer rentAdditional;
    private String rooms;
    private List<String> photos;
    private String area;

}
