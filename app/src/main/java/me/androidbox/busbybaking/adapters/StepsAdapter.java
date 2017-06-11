package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import me.androidbox.busbybaking.model.Steps;
import me.androidbox.busbybaking.recipedetail.StepsViewHolder;

/**
 * Created by steve on 6/11/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsViewHolder> {
    private List<Steps> stepsList = Collections.emptyList();

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(StepsViewHolder stepsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
