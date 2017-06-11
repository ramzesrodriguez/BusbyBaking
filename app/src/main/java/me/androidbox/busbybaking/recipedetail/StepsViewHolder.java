package me.androidbox.busbybaking.recipedetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.StepsAdapter;
import me.androidbox.busbybaking.model.Steps;
import timber.log.Timber;

/**
 * Created by steve on 6/11/17.
 */

public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tvDescription) TextView tvDescription;
    private StepsAdapter stepsAdapter;

    public StepsViewHolder(View itemView, StepsAdapter stepsAdapter) {
        super(itemView);
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

    }
}
