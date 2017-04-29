package com.fancytank.ognia.muzeumwojska.api.model;

public class DisplayUnit {
    private final String desc;
    private final String imgUri;

    public DisplayUnit(String imgUrl, String desc) {
        this.desc = desc;
        this.imgUri = imgUrl;
    }
}
