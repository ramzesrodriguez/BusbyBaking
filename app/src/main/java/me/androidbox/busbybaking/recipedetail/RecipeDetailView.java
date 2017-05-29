package me.androidbox.busbybaking.recipedetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.androidbox.busbybaking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailView extends Fragment {
    public static final String TAG = RecipeDetailView.class.getSimpleName();

    public RecipeDetailView() {
        // Required empty public constructor
    }

    public static RecipeDetailView getInstance() {
        return new RecipeDetailView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_detail_view, container, false);
    }

}
