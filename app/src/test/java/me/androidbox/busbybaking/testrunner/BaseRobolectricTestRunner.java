package me.androidbox.busbybaking.testrunner;

import android.app.BuildConfig;
import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.DaggerTestBusbyComponent;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.MockRecipeSchedulersModule;
import me.androidbox.busbybaking.di.TestBusbyComponent;

/**
 * Created by steve on 9/2/17.
 */
@Config(constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.LOLLIPOP,
        packageName = "me.androidbox.busbybaking",
        application = BusbyBakingApplication.class)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseRobolectricTestRunner {

    protected TestBusbyComponent getTestComponent() {
        return DaggerTestBusbyComponent.builder()
                .mockRecipeListModule(new MockRecipeListModule())
                .mockRecipeSchedulersModule(new MockRecipeSchedulersModule())
                .build();
    }
}
