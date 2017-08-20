package me.androidbox.busbybaking.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import timber.log.Timber;

/**
 * Created by steve on 6/1/17.
 */
@Module
public class MockAndroidModule {

    @Singleton
    @Provides
    Context providesContext() {
        return Mockito.mock(Context.class);
    }

    @Singleton
    @Provides
    Resources providesResources() {
        return Mockito.mock(Resources.class);
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }

    @Singleton
    @Provides
    RecipeSchedulers provideRecipeSchedulers() {
        return new RecipeSchedulers() {
            @Override
            public Scheduler getBackgroundScheduler() {
                Timber.d("getBackgroundScheduler");
                return Schedulers.trampoline();
            }

            @Override
            public Scheduler getUIScheduler() {
                Timber.d("getUIScheduler");
                return Schedulers.trampoline();
            }
        };
    }
}
