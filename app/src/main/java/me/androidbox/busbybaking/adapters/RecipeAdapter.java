package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {
    private List<Recipe> recipeList = Collections.emptyList();

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.recipe_item, viewGroup, false);

        return RecipeListViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder recipeListViewHolder, int i) {
        recipeListViewHolder.populateDate(recipeList.get(i));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
