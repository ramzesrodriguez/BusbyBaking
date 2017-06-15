package me.androidbox.busbybaking.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;

/**
 * Created by steve on 6/15/17.
 */

public class RecipeService extends IntentService implements RecipeListModelContract.RecipeGetAllListener {
    public static final String ACTION_RECIPE_GET = "me.androidbox.busbybaking.services";

    @Inject RecipeListModelContract recipeListModelContract;

    public RecipeService(String name) {
        super(name);
    }

    public static void startActionRecipeGet(Context context) {
        

        final Intent intent = new Intent(context, RecipeService.class);
        intent.setAction(ACTION_RECIPE_GET);
        context.startActivity(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_RECIPE_GET.equals(action)) {
                handleActionRecipeGet();
            }
        }
    }

    private void handleActionRecipeGet() {
        recipeListModelContract.getRecipesFromAPI(RecipeService.this);
    }

    @Override
    public void onRecipeGetAllSuccess(List<Recipe> recipeList) {

    }

    @Override
    public void onRecipeGetAllFailure(String errorMessage) {

    }
}
