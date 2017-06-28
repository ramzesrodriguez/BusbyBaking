package me.androidbox.busbybaking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.IRecipeListViewHolderFactory;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeListViewHolder> implements View.OnClickListener {
    private List<Recipe> recipeList = Collections.emptyList();

    private Context context;

    public RecipeAdapter() {
        recipeList = new ArrayList<>();
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();

        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.recipe_item, viewGroup, false);

        // final RecipeListViewHolder recipeListViewHolder = RecipeListViewHolder.newInstance(view);

        final RecipeListViewHolder recipeListViewHolder = new IRecipeListViewHolderFactory().createViewHolder(viewGroup);

        recipeListViewHolder.itemView.setOnClickListener(RecipeAdapter.this);

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

    @Override
    public void onClick(View v) {
        Timber.d("onClick");

/*
        final Intent intent = Henson.with(context)
                .gotoRecipeDetailActivity()
                .recipe(recipe)
                .build();

        context.startActivity(intent);

        RecipeService.startActionRecipeGet(context);
*/

    }
}
