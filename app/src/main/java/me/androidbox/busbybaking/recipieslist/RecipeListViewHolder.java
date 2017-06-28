package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */
@AutoFactory(implementing = IRecipeListViewHolderFactory.class)
public class RecipeListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecipeName) TextView tvRecipeName;
    @BindView(R.id.tvQuantity) TextView tvQuantity;

    public RecipeListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(RecipeListViewHolder.this, itemView);
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

    public void onClick(View v) {
        Timber.d("onClick %d", getAdapterPosition());
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
