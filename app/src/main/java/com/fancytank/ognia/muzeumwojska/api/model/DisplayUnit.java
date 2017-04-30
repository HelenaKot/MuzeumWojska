package com.fancytank.ognia.muzeumwojska.api.model;

import android.support.annotation.DrawableRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayUnit implements Serializable{
    private final String id;
    private final String title;
    private Category image;
    private List<DisplayParagraph> desc = new ArrayList<>();
    //todo coordintes

    public DisplayUnit(String id, String title) {
        this.id = id;
        this.title = title;
        image = Category.TANK;
    }

    public DisplayUnit(String id, String title, Category image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public List<DisplayParagraph> getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public boolean hasImage() {
        return image != null;
    }

    public
    @DrawableRes
    int getImage() {
        return image.drawableRes;
    }
}
