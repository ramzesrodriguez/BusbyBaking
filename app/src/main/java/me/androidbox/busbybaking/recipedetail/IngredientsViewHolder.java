package me.androidbox.busbybaking.recipedetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.IngredientsAdapter;
import me.androidbox.busbybaking.model.Ingredients;
import timber.log.Timber;

import static java.lang.String.valueOf;

/**
 * Created by steve on 6/11/17.
 */

public class IngredientsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvMeasure) TextView tvMeasure;
    @BindView(R.id.tvQuantity) TextView tvQuantity;

    private IngredientsAdapter ingredientsAdapter;

    public IngredientsViewHolder(View itemView, IngredientsAdapter ingredientsAdapter) {
        super(itemView);
        this.ingredientsAdapter = ingredientsAdapter;
        itemView.setOnClickListener(IngredientsViewHolder.this);
    }

    public static IngredientsViewHolder newInstance(View itemView, IngredientsAdapter ingredientsAdapter) {
        return new IngredientsViewHolder(itemView, ingredientsAdapter);
    }

    public void populateData(@NonNull Ingredients ingredients) {
        tvName.setText(ingredients.getIngredient());
        tvMeasure.setText(ingredients.getMeasure());
        tvQuantity.setText(valueOf(ingredients.getQuantity()));
    }

    @Override
    public void onClick(View v) {
        Timber.d("onClick: %d", getAdapterPosition());
        Ingredients ingredients = ingredientsAdapter.getIngredient(getAdapterPosition());
    }
}
