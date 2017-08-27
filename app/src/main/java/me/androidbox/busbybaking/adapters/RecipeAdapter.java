package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.utils.Constants;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {
    private List<Recipe> recipeList = Collections.emptyList();
    private Map<Integer, RecipeListViewHolderFactory> viewHolderFactories;
    private RecipeItemClickListener recipeItemClickListener;

    @Inject
    public RecipeAdapter(RecipeItemClickListener recipeItemClickListener, Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        this.recipeList = new ArrayList<>();
        this.viewHolderFactories = viewHolderFactories;
        this.recipeItemClickListener = recipeItemClickListener;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        /* Inject the viewholder */
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        final RecipeListViewHolder recipeListViewHolder = viewHolderFactories.get(Constants.RECIPE_LIST).createViewHolder(view);

        recipeListViewHolder.itemView.setOnClickListener(itemView -> {
            Timber.d("onClick %d", recipeListViewHolder.getAdapterPosition());
            recipeItemClickListener.onRecipeItemClick(getRecipe(recipeListViewHolder.getAdapterPosition()), viewGroup.getContext());
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

    private Recipe getRecipe(int position) {
        return recipeList.get(position);
    }

    public void fillRecipeData(List<Recipe> recipes) {
        recipeList.clear();
        recipeList.addAll(recipes);
        notifyItemRangeInserted(0, recipeList.size());
    }
}
