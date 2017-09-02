package me.androidbox.busbybaking.di;

import android.app.BuildConfig;
import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
