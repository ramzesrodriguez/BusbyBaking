package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.mockito.Mock;
import org.mockito.Mockito;

import dagger.Module;

/**
 * Created by steve on 6/1/17.
 */
@Module
public class MockAndroidModule {
    @Mock Application application;

    public MockAndroidModule(Application application) {
        this.application = application;
    }

    public Context providesContext() {
        return Mockito.mock(Context.class);
    }

    public Resources providesResources() {
        return Mockito.mock(Resources.class);
    }

    public SharedPreferences providesSharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }
}
