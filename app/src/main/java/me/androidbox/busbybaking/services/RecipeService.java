package me.androidbox.busbybaking.services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.widget.BusbyBakingWidgetProvider;
import timber.log.Timber;

/**
 * Created by steve on 6/15/17.
 */

public class RecipeService extends IntentService implements RecipeListModelContract.RecipeGetAllListener {
    public static final String ACTION_RECIPE_GET = "me.androidbox.busbybaking.services";

    @Inject RecipeListModelContract recipeListModelContract;
    private Context context;

    public RecipeService() {
        super("");

    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((BusbyBakingApplication)getApplication())
                .createRecipeListComponent()
                .inject(this);

        if(recipeListModelContract == null) {
            Timber.e("recipeListModelContract == null");
        }
    }

    public RecipeService(String name) {
        super(name);
    }

    public static void startActionRecipeGet(Context context) {
        final Intent intent = new Intent(context, RecipeService.class);
        intent.setAction(ACTION_RECIPE_GET);
        context.startService(intent);
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
        Timber.d("onRecipeGetAllSuccess");
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(RecipeService.this);
        final int appWidget[] = appWidgetManager.getAppWidgetIds(new ComponentName(RecipeService.this, BusbyBakingWidgetProvider.class));

        BusbyBakingWidgetProvider.updateAppWidget(RecipeService.this, appWidgetManager, appWidget[0], recipeList.get(0).getName());
    }

    @Override
    public void onRecipeGetAllFailure(String errorMessage) {
        Timber.d("onRecipeGetAllFailure %s", errorMessage);
    }
}
