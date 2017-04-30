package com.fancytank.ognia.muzeumwojska.api.model;

import android.support.annotation.DrawableRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayUnit implements Serializable {
    final String id;
    final String title;
    Category image;
    List<DisplayParagraph> desc = new ArrayList<>();
    //todo coordintes
    public String coordinates;

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

    public String getCoordinates() {
        return coordinates;
    }

    public void addDesc(DisplayParagraph paragraph) {
        desc.add(paragraph);
    }

    public
    @DrawableRes
    int getImage() {
        return image.drawableRes;
    }
}
