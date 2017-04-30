package com.fancytank.ognia.muzeumwojska.api.model;

public class DisplayParagraph {
    private final String desc;
    private final String imgUri;

    public DisplayParagraph(String imgUrl, String desc) {
        this.desc = desc;
        this.imgUri = imgUrl;
    }
}
