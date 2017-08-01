package me.androidbox.busbybaking.di;

import android.app.Activity;
import android.app.Application;

import me.androidbox.busbybaking.recipieslist.MainActivity;
import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */

public class BusbyBakingApplication extends Application {
    private BusbyBakingComponent applicationComponent;
    private RecipeListComponent recipeListComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        applicationComponent = createApplicationComponent();
        recipeListComponent = createRecipeListComponent();
    }

    public BusbyBakingComponent createApplicationComponent() {
        return DaggerBusbyBakingComponent.builder()
                .networkModule(new NetworkModule())
                .androidModule(new AndroidModule(BusbyBakingApplication.this))
                .exoPlayerModule(new ExoPlayerModule())
                .build();
    }

    public BusbyBakingComponent busbyApplicationComponent() {
        return applicationComponent;
    }

    public RecipeListComponent createRecipeListComponent() {
        return applicationComponent.add(new RecipeListModule());
    }

    public RecipeListComponent recipeListComponent() {
        return recipeListComponent;
    }

    public void releaseRecipeListComponent() {
        recipeListComponent = null;
    }

    /* Used for setting the mock component for AT */
    public void setRecipeListComponent(RecipeListComponent recipeListComponent) {
        this.recipeListComponent = recipeListComponent;
    }
}
