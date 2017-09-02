package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 6/7/17.
 */
@AutoFactory(implementing = IRecipeListViewHolderFactory.class)
public class RecipeListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecipeName) public TextView tvRecipeName;
    @BindView(R.id.tvQuantity) public TextView tvQuantity;

    public RecipeListViewHolder(View view) {
        super(view);

        ButterKnife.bind(RecipeListViewHolder.this, view);
    }

    public void populateDate(Recipe recipe) {
        tvRecipeName.setText(recipe.getName());
        final String quantity = "Quantity: " + recipe.getServings();
        tvQuantity.setText(quantity);
    }
}
