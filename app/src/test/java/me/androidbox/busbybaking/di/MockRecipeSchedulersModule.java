package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;


/**
 * Created by steve on 6/24/17.
 */
@Module
public class MockRecipeSchedulersModule {
    @Singleton
    @Provides
    RecipeSchedulers providesRecipeSchedulers() {
        return new RecipeSchedulers() {
            @Override
            public Scheduler getBackgroundScheduler() {
                return Schedulers.trampoline();
            }

            @Override
            public Scheduler getUIScheduler() {
                return Schedulers.trampoline();
            }
        };
    }
}
