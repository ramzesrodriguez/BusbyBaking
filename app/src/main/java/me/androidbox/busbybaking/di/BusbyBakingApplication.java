package me.androidbox.busbybaking.di;

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
    }

    public BusbyBakingComponent createApplicationComponent() {
        return DaggerBusbyBakingComponent.builder()
                .networkModule(new NetworkModule())
                .androidModule(new AndroidModule(BusbyBakingApplication.this))
                .build();
    }

    public BusbyBakingComponent getApplicationComponent() {
        return applicationComponent;
    }

    public RecipeListComponent createRecipeListComponent(MainActivity activity) {
        recipeListComponent = applicationComponent.add(new RecipeListModule(activity));
        return recipeListComponent;
    }

    public void releaseRecipeListComponent() {
        recipeListComponent = null;
    }
}
