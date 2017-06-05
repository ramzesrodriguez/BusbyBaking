package me.androidbox.busbybaking.recipieslist;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.MockAndroidModule;
import me.androidbox.busbybaking.di.TestBusbyBakingComponent;

/**
 * Created by steve on 5/31/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListViewTest {
  //  @Inject MockAndroidModule mockAndroidModule;

    @Rule
    public ActivityTestRule<MainActivity> mainActivity =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,
                    false);

    @Before
    public void setup() throws Exception {
        final Instrumentation instrumentation =
                InstrumentationRegistry.getInstrumentation();

        BusbyBakingApplication busbyBakingApplication =
                (BusbyBakingApplication)instrumentation
                        .getTargetContext()
                        .getApplicationContext();

        TestBusbyBakingComponent component =
                (TestBusbyBakingComponent)busbyBakingApplication.getApplicationComponent();

        component.inject(RecipeListViewTest.this);
    }

    @Test
    public void shouldBeAbleToGetSharedPreferences() throws Exception {
        mainActivity.launchActivity(new Intent());
    }
}