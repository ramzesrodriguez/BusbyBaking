package me.androidbox.busbybaking.recipieslist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;
import me.androidbox.busbybaking.services.RecipeService;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;

    @BindView(R.id.tvRecipeName) TextView tvRecipeName;
    @BindView(R.id.tvQuantity) TextView tvQuantity;
    private RecipeAdapter recipeAdapter;

    public RecipeListViewHolder(View itemView, RecipeAdapter recipeAdapter) {
        super(itemView);
        this.recipeAdapter = recipeAdapter;
        context = itemView.getContext();

        ButterKnife.bind(RecipeListViewHolder.this, itemView);

        itemView.setOnClickListener(RecipeListViewHolder.this);
    }

    public static RecipeListViewHolder newInstance(View itemView, RecipeAdapter recipeAdapter) {

        return new RecipeListViewHolder(itemView, recipeAdapter);
    }

    public void populateDate(Recipe recipe) {
        tvRecipeName.setText(recipe.getName());
        final String quantity = "Quantity: " + recipe.getServings();
        Timber.d(recipe.getName());
        tvQuantity.setText(quantity);
    }

    @Override
    public void onClick(View v) {
        Timber.d("onClick %d", getAdapterPosition());
        Recipe recipe = recipeAdapter.getRecipe(getAdapterPosition());

        final Intent intent = Henson.with(context)
                .gotoRecipeDetailActivity()
                .recipe(recipe)
                .build();

        context.startActivity(intent);

        RecipeService.startActionRecipeGet(context);

    }
}
