package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by steve on 5/29/17.
 */
@Module
public class AndroidModule {
    private final Context context;

    AndroidModule(Application application) {
        this.context = application;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return context;
    }

    @Singleton
    @Provides
    Resources providesResources() {
        return context.getResources();
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    RecipeSchedulers providesRecipeSchedulers() {
        return new RecipeSchedulers() {
            @Override
            public Scheduler getBackgroundScheduler() {
                return Schedulers.io();
            }

            @Override
            public Scheduler getUIScheduler() {
                return AndroidSchedulers.mainThread();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public <T> T getSystemServices(String serviceName) {
        return (T)context.getSystemService(serviceName);
    }
}
