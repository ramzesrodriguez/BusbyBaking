package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.model.Ingredients;
import me.androidbox.busbybaking.recipedetail.IngredientsViewHolder;

/**
 * Created by steve on 6/11/17.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {
    private List<Ingredients> ingredientsList = Collections.emptyList();

    public IngredientsAdapter(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder ingredientsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
