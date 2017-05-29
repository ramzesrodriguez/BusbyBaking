package me.androidbox.busbybaking.di;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */

public class BusbyBakingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
