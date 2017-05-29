package me.androidbox.busbybaking.recipieslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import me.androidbox.busbybaking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListView extends MvpFragment<RecipeListPresenterContract, RecipeListPresenterImp> {
    public static final String TAG = RecipeListView.class.getSimpleName();

    public RecipeListView() {}

    public static RecipeListView newInstance() {
        return new RecipeListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_list_view, container, false);
    }

    @Override
    public RecipeListPresenterImp createPresenter() {
        return new RecipeListPresenterImp();
    }
}
