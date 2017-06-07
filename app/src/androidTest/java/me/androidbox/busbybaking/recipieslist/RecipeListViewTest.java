package me.androidbox.busbybaking.recipieslist;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.base.Verify;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.MockAndroidModule;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.TestBusbyBakingComponent;
import me.androidbox.busbybaking.model.Recipe;
import rx.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by steve on 5/31/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListViewTest {
    @Inject RecipeListModelContract recipeListModelContract;

    private RecipeListModelContract.RecipeGetAllListener recipeGetAllListener;


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

        List<Recipe> recipeStub = new ArrayList<>();
        recipeGetAllListener = Mockito.mock(RecipeListModelContract.RecipeGetAllListener.class);

     //   Mockito.doReturn(ArgumentMatchers.anyObject()).when(recipeListModelContract.getRecipesFromAPI(Observable.just(recipeStub));
        Mockito.when(recipeListModelContract.getRecipesFromAPI(recipeGetAllListener)).thenReturn(Observable.just(recipeStub)));

        mainActivity.launchActivity(new Intent());

        Mockito.verify(recipeGetAllListener, times(1)).onRecipeGetAllSuccess(recipeStub);
        Mockito.verify(recipeGetAllListener, never()).onRecipeGetAllFailure(anyString());
    }
}