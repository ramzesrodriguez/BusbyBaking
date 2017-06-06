package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by steve on 5/29/17.
 */
@Module
public class AndroidModule {
    private final Context context;

    public AndroidModule(Application application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context providesContext() {
        return context;
    }

    @Singleton
    @Provides
    public Resources providesResources() {
        return context.getResources();
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSystemServices(Context context, String serviceName) {
        return (T)context.getSystemService(serviceName);
    }
}
