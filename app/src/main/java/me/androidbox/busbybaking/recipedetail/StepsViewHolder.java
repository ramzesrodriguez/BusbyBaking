package me.androidbox.busbybaking.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.StepsAdapter;
import me.androidbox.busbybaking.model.Steps;
import me.androidbox.busbybaking.recipe.Henson;
import timber.log.Timber;

/**
 * Created by steve on 6/11/17.
 */

public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tvDescription) TextView tvDescription;
    private StepsAdapter stepsAdapter;
    private Context context;

    public StepsViewHolder(View itemView, StepsAdapter stepsAdapter) {
        super(itemView);

        context = itemView.getContext();

        ButterKnife.bind(StepsViewHolder.this, itemView);

        this.stepsAdapter = stepsAdapter;
        itemView.setOnClickListener(StepsViewHolder.this);
    }

    public static StepsViewHolder newInstance(View itemView, StepsAdapter stepsAdapter) {
        return new StepsViewHolder(itemView, stepsAdapter);
    }

    public void populateData(@NonNull Steps steps) {
        tvDescription.setText(steps.getShortDescription());
    }

    @Override
    public void onClick(View v) {
        Timber.d("Item: %d", getAdapterPosition());
        Steps steps = stepsAdapter.getSteps(getAdapterPosition());

        final Intent intent = Henson.with(context)
                .gotoRecipeVideoStepsActivity()
                .steps(steps)
                .build();

        context.startActivity(intent);
    }
}
