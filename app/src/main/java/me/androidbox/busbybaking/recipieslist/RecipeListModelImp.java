package me.androidbox.busbybaking.recipieslist;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */

public class RecipeListModelImp
        implements RecipeListModelContract {

    private Subscription subscription;
    private RecipesAPI recipesAPI;
    private RecipeSchedulers recipeSchedulers;

    @Inject
    public RecipeListModelImp(@NonNull RecipesAPI recipesAPI, @NonNull RecipeSchedulers recipeSchedulers) {
        this.recipesAPI = Preconditions.checkNotNull(recipesAPI);
        this.recipeSchedulers = Preconditions.checkNotNull(recipeSchedulers);
    }

    @Override
    public void getRecipesFromAPI(final RecipeGetAllListener recipeGetAllListener) {
        subscription = recipesAPI.getAllRecipes()
                .subscribeOn(recipeSchedulers.getBackgroundScheduler())
                .observeOn(recipeSchedulers.getUIScheduler())
                .subscribe(new Subscriber<List<Recipe>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e, e.getMessage());
                        recipeGetAllListener.onRecipeGetAllFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Recipe> recipe) {
                        Timber.d("onNext");
                        recipeGetAllListener.onRecipeGetAllSuccess(recipe);
                    }
                });
    }

    @Override
    public void releaseResources() {
        if(subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
