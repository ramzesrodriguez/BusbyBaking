package me.androidbox.busbybaking.recipieslist;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.model.Recipe;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListView
        extends MvpFragment<RecipeListViewContract, RecipeListPresenterImp>
        implements RecipeListViewContract {

    public static final String TAG = RecipeListView.class.getSimpleName();

    @Inject RecipeListPresenterContract recipeListPresenterContract;

    @BindView(R.id.rvRecipeList) RecyclerView rvRecipeList;
    private Unbinder unbinder;
    private RecipeAdapter recipeAdapter;

    public RecipeListView() {}
    public static RecipeListView newInstance() {
        return new RecipeListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BusbyBakingApplication)getActivity()
                .getApplication())
                .getApplicationComponent()
                .inject(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
/*        ((BusbyBakingApplication)getActivity().getApplication())
                .releaseRecipeListComponent();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.recipe_list_view, container, false);

        unbinder = ButterKnife.bind(RecipeListView.this, view);

        if(recipeListPresenterContract != null) {
            Timber.d("recipeListPresenterContract != null");
            recipeListPresenterContract.retrieveAllRecipes();
        }
        else {
            Timber.e("recipeListPresenterContract == null");
        }

        return view;
    }

    private void setupDataBinding(List<Recipe> recipeList) {
        recipeAdapter = new RecipeAdapter(recipeList);
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRecipeList.setLayoutManager(layoutManager);
        rvRecipeList.setAdapter(recipeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public RecipeListPresenterImp createPresenter() {
        return (RecipeListPresenterImp)recipeListPresenterContract;
    }

    @Override
    public void displayRecipeData(List<Recipe> recipeList) {
        Timber.d("displayData: %d", recipeList.size());

        setupDataBinding(recipeList);
    }

    @Override
    public void displayRecipeError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }
}
