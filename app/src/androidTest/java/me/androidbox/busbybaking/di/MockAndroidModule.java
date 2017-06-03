package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by steve on 6/1/17.
 */
@Module
public class MockAndroidModule {

    @Singleton
    @Provides
    public Context providesContext() {
        return Mockito.mock(Context.class);
    }

    @Singleton
    @Provides
    public Resources providesResources() {
        return Mockito.mock(Resources.class);
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }
}
