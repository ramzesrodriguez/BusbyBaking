package me.androidbox.busbybaking.recipieslist;

import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */

public class RecipeListModuleImp
        implements RecipeListModuleContract{

    private Subscription subscription;
    private RecipesAPI recipesAPI;

    @Inject
    public RecipeListModuleImp(RecipesAPI recipesAPI) {
        this.recipesAPI = recipesAPI;
    }

    @Override
    public void getRecipesFromAPI(final RecipeGetAllListener recipeGetAllListener) {
        subscription = recipesAPI.getAllRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
    public void startup() {

    }

    @Override
    public void shutdown() {
        if(subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
