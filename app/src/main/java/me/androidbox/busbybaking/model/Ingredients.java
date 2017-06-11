package me.androidbox.busbybaking.model;

import org.parceler.Parcel;

/**
 * Created by steve on 5/24/17.
 */
@Parcel
public class Ingredients {
    private float quantity;
    private String measure;
    private String ingredient;

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
