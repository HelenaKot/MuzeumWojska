package com.fancytank.ognia.muzeumwojska.api.model;

import java.io.Serializable;

public class DisplayParagraph implements Serializable {
    private final String desc;
    private final String imgUri;

    public DisplayParagraph(String imgUrl, String desc) {
        this.desc = desc;
        this.imgUri = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgUri() {
        return imgUri;
    }
}
