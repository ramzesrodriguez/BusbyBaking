package me.androidbox.busbybaking.recipieslist;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.TestBusbyBakingApplication;
import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.TestBusbyBakingComponent;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by steve on 5/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeListViewAndroidTest {
    @Inject RecipesAPI recipesAPI;

    @Mock RecipeListModelContract.RecipeGetAllListener mockRecipeListener;

    @Rule
    public ActivityTestRule<MainActivity> mainActivity =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,
                    false);

    @Before
    public void setup() throws Exception {
        final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        final TestBusbyBakingApplication testBusbyBakingApplication =
                (TestBusbyBakingApplication)instrumentation.getTargetContext().getApplicationContext();

        testBusbyBakingApplication.getRecipeListComponent().inject(RecipeListViewAndroidTest.this);
    }

    /** Return a mocked list of recipes instead of doing a network call */
    @Test
    public void shouldReturnAListOfRecipes() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);
        recipeList.add(recipe);

        when(recipesAPI.getAllRecipes()).thenReturn(Observable.just(recipeList));
        doNothing().when(mockRecipeListener).onRecipeGetAllSuccess(recipeList);

        mainActivity.launchActivity(new Intent());

   //     onView(withId(R.id.rvRecipeList)).check(matches(hasDescendant(withText("Test Brownies"))));
    }
}