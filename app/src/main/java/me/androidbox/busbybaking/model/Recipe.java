package me.androidbox.busbybaking.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by steve on 5/24/17.
 */
@Parcel
public class Recipe {
    int id;
    String name;
    List<Ingredients> ingredients;
    List<Steps> steps;
    int servings;
    String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
