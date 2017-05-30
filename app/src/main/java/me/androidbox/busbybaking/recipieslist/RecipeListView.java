package me.androidbox.busbybaking.recipieslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.model.Recipe;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListView
        extends MvpFragment<RecipeListPresenterContract, RecipeListPresenterImp>
        implements RecipeListViewContract {

    public static final String TAG = RecipeListView.class.getSimpleName();

    @Inject RecipeListPresenterContract recipeListPresenterContract;

    public RecipeListView() {}

    public static RecipeListView newInstance() {
        return new RecipeListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BusbyBakingApplication)getActivity().getApplication())
                .createRecipeListComponent()
                .inject(RecipeListView.this);

        if(recipeListPresenterContract != null) {
            Timber.d("recipeListPresenterContract != null");
        }
        else {
            Timber.e("recipeListPresenterContract == null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BusbyBakingApplication)getActivity().getApplication())
                .releaseRecipeListComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_list_view, container, false);
    }

    @Override
    public RecipeListPresenterImp createPresenter() {
        return (RecipeListPresenterImp)recipeListPresenterContract;
    }

    @Override
    public void displayData(List<Recipe> recipeList) {
        Timber.d("displayData: %d", recipeList.size());
    }

    @Override
    public void displayError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }
}
