package me.androidbox.busbybaking.recipieslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

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
    @Inject RecipeAdapter recipeAdapter;

    @BindView(R.id.rvRecipeList) RecyclerView rvRecipeList;
    private Unbinder unbinder;

    public RecipeListView() {}

    public static RecipeListView newInstance() {
        return new RecipeListView();
    }

    @Override
    @NonNull
    public RecipeListPresenterImp createPresenter() {
        return (RecipeListPresenterImp)recipeListPresenterContract;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BusbyBakingApplication)getActivity().getApplication())
                .getRecipeListComponent().inject(this);
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
        final View view = inflater.inflate(R.layout.recipe_list_view, container, false);

        unbinder = ButterKnife.bind(RecipeListView.this, view);

        setupDataBinding();
        getAllRecipes();

        return view;
    }

    private void setupDataBinding() {
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRecipeList.setLayoutManager(layoutManager);
        rvRecipeList.setAdapter(recipeAdapter);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    void fillAdapter(List<Recipe> recipeList) {
        recipeAdapter.fillRecipeData(recipeList);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    void getAllRecipes() {
        recipeListPresenterContract.retrieveAllRecipes();
    }

    @Override
    public void displayRecipeData(List<Recipe> recipeList) {
        Timber.d("displayData: %d", recipeList.size());

        fillAdapter(recipeList);
    }

    @Override
    public void displayRecipeError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}











































/*

public class OptInNotificationViewFragment
        extends BaseMvpFragment<OptInNotificationViewContract, OptInNotificationPresenterImp>
        implements OptInNotificationViewContract {

    public static final String TAG = OptInNotificationViewFragment.class.getSimpleName();

    @Inject OptInFragmentListener optInFragmentListener;
    @Inject OptInNotificationPresenterImp optInNotificationPresenter;
    @Inject IBaseActivityNavigator activityNavigator;

    public OptInNotificationViewFragment() {}

    public static OptInNotificationViewFragment newInstance() {
        return new OptInNotificationViewFragment();
    }

    @Override
    public OptInNotificationPresenterImp createPresenter() {
        return optInNotificationPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.traveler_notification_opt_in;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R2.id.button_traveler_turn_on_notification)
    public void turnOnNotification() {
        optInNotificationPresenter.displayApplicationSettingsNotification();
    }

    @OnClick(R2.id.button_traveler_not_now)
    public void enableNotNowForNotifications(){
        optInNotificationPresenter.setLastCheckedDateForNotifications();
    }

    @Override
    public void displayOptInNotificationSettings() {
        if(activityNavigator != null) {
            activityNavigator.openAppNotificationsSettings(getActivity());
        }
    }

    @Override
    public void closeScreen() {
        optInFragmentListener.onCloseOptInNotification();
    }
}

*/
