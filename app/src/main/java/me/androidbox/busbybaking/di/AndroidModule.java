package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;

/**
 * Created by steve on 5/29/17.
 */
@Module
public class AndroidModule {
    private final Context context;

    AndroidModule() {
        context = null;
    }

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
