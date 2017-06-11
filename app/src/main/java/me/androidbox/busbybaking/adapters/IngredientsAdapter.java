package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.R;
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
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.ingredient_item, viewGroup, false);

        return IngredientsViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder ingredientsViewHolder, int i) {
        ingredientsViewHolder.populateData(ingredientsList.get(i));
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
