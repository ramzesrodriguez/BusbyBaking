package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tvRecipeName) TextView tvRecipeName;
    @BindView(R.id.tvQuantity) TextView tvQuantity;

    public RecipeListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(RecipeListViewHolder.this, itemView);


        itemView.setOnClickListener(RecipeListViewHolder.this);
    }

    public static RecipeListViewHolder newInstance(View itemView) {
        return new RecipeListViewHolder(itemView);
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

    }
}
