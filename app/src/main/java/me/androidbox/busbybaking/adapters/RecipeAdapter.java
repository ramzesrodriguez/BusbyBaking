package me.androidbox.busbybaking.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.MainActivity;
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
    private MainActivity mainActivity;

    public RecipeAdapter(Activity activity, Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        this.recipeList = new ArrayList<>();
        this.viewHolderFactories = viewHolderFactories;
        this.mainActivity = (MainActivity)activity;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /* Inject the viewholder */
        final RecipeListViewHolder recipeListViewHolder = viewHolderFactories.get(Constants.RECIPE_LIST).createViewHolder(viewGroup);

        recipeListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("onClick %d", recipeListViewHolder.getAdapterPosition());
                mainActivity.onRecipeItemClick(getRecipe(recipeListViewHolder.getAdapterPosition()));
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
        notifyItemRangeInserted(0, recipeList.size());
    }
}
