package com.fancytank.ognia.muzeumwojska.api.model;

import android.support.annotation.DrawableRes;

import com.fancytank.ognia.muzeumwojska.R;

public enum Category {
    TANK(R.drawable.ic_tank),
    PLANE(R.drawable.ic_fighter_plane),
    BTR(R.drawable.ic_armored_vehicle),
    PISTOL(R.drawable.ic_automatic_gun),
    LAUNCHER(R.drawable.ic_rocket_launch),
    SATELLITE(R.drawable.ic_military_satellites);

    int drawableRes;

    Category(@DrawableRes int drawableRes) {
        this.drawableRes = drawableRes;
    }
}