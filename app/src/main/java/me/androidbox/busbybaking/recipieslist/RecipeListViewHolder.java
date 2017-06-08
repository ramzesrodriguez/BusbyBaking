package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecipeName) TextView tvRecipeName;
    @BindView(R.id.tvQuantity) TextView tvQuantity;

    public RecipeListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(RecipeListViewHolder.this, itemView);
    }

    public static RecipeListViewHolder getInstance(View itemView) {
        return new RecipeListViewHolder(itemView);
    }

    public void populateDate(Recipe recipe) {
        tvRecipeName.setText(recipe.getName());
        final String quantity = "Quantity: " + recipe.getServings();
        tvQuantity.setText(quantity);
    }
}
