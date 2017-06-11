package me.androidbox.busbybaking.recipedetail;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.dart.Dart;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.IngredientsAdapter;
import me.androidbox.busbybaking.adapters.StepsAdapter;
import me.androidbox.busbybaking.model.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailView extends Fragment {
    public static final String TAG = RecipeDetailView.class.getSimpleName();
    public static final String RECIPE_KEY = "recipe_key";

    @BindView(R.id.rvIngredients) RecyclerView rvIngredients;
    @BindView(R.id.rvSteps) RecyclerView rvSteps;
    private Unbinder unbinder;

    private Recipe recipe;

    public RecipeDetailView() {
        // Required empty public constructor
    }

    public static RecipeDetailView getInstance(Bundle data) {
        RecipeDetailView recipeDetailView = new RecipeDetailView();
        recipeDetailView.setArguments(data);
        return recipeDetailView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Bundle bundle = getArguments();
        Parcelable parcelable = bundle.getParcelable(RECIPE_KEY);
        recipe = Parcels.unwrap(parcelable);

        final View view = inflater.inflate(R.layout.recipe_detail_view, container, false);
        unbinder = ButterKnife.bind(RecipeDetailView.this, view);

        setUpDataBinding();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpDataBinding() {
        setupIngredients();
        setupSteps();
    }

    private void setupIngredients() {
        final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvIngredients.setLayoutManager(layoutManager);
        rvIngredients.setAdapter(ingredientsAdapter);
    }

    private void setupSteps() {
        final StepsAdapter stepsAdapter = new StepsAdapter(recipe.getSteps());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvSteps.setLayoutManager(layoutManager);
        rvSteps.setAdapter(stepsAdapter);
    }
}
