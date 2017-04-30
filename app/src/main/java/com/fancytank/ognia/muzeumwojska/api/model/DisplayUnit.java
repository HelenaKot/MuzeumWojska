package com.fancytank.ognia.muzeumwojska.api.model;

import java.util.ArrayList;
import java.util.List;

public class DisplayUnit {
    private final String id;
    private final String title;
    private List<DisplayParagraph> desc = new ArrayList<>();
    //todo coordintes

    public DisplayUnit(String id, String title) {
        this.id = id;
        this.title = title;
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
}
