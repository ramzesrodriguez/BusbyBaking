package me.androidbox.busbybaking.recipieslist;


import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */

public class RecipeListModelImp
        implements RecipeListModelContract {

    private RecipesAPI recipesAPI;
    private RecipeSchedulers recipeSchedulers;
    @VisibleForTesting
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RecipeListModelImp(@NonNull RecipesAPI recipesAPI, @NonNull RecipeSchedulers recipeSchedulers) {
        this.recipesAPI = Preconditions.checkNotNull(recipesAPI);
        this.recipeSchedulers = Preconditions.checkNotNull(recipeSchedulers);
    }

    @Override
    public void getRecipesFromAPI(final RecipeGetAllListener recipeGetAllListener) {
        compositeDisposable.add(recipesAPI.getAllRecipes()
                .subscribeOn(recipeSchedulers.getBackgroundScheduler())
                .observeOn(recipeSchedulers.getUIScheduler())
                .subscribeWith(new DisposableObserver<List<Recipe>>() {
                    @Override
                    protected void onStart() {
                        Timber.d("onStart");
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<Recipe> recipeList) {
                        Timber.d("onNext %d", recipeList.size());
                        recipeGetAllListener.onRecipeGetAllSuccess(recipeList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "onError %s", e.getMessage());
                        recipeGetAllListener.onRecipeGetAllFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                    }
                }));
    }

    @Override
    public void releaseResources() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
