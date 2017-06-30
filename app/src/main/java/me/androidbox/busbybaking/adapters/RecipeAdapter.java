package me.androidbox.busbybaking.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.services.RecipeService;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {
    private List<Recipe> recipeList = Collections.emptyList();

    private Context context;

    public RecipeAdapter() {
        recipeList = new ArrayList<>();
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        final RecipeListViewHolder recipeListViewHolder = new RecipeListViewHolderFactory().createViewHolder(viewGroup);

        recipeListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("onClick %d", recipeListViewHolder.getAdapterPosition());

                final Intent intent = Henson.with(context)
                        .gotoRecipeDetailActivity()
                        .recipe(getRecipe(recipeListViewHolder.getAdapterPosition()))
                        .build();

                context.startActivity(intent);

                RecipeService.startActionRecipeGet(context);

            }
        });

        return recipeListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder recipeListViewHolder, int i) {
        recipeListViewHolder.populateDate(recipeList.get(i));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public Recipe getRecipe(int position) {
        return recipeList.get(position);
    }

    public void fillRecipeData(List<Recipe> recipes) {
        recipeList.clear();
        recipeList.addAll(recipes);
        notifyDataSetChanged();
    }
}
