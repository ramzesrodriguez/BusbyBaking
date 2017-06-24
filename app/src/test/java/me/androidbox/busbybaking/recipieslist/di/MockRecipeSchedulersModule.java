package me.androidbox.busbybaking.recipieslist.di;



import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import rx.Scheduler;
import rx.schedulers.Schedulers;

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
