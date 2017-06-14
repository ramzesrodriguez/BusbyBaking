package me.androidbox.busbybaking.recipieslist;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by steve on 5/31/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListViewTest {
    @Inject RecipeListModelContract recipeListModelContract;
    @Inject RecipesAPI recipesAPI;


    private RecipeListModelContract.RecipeGetAllListener mockRecipeListener;
/*
    @Mock List<Recipe> mockRecipeList;
*/

    public ActivityTestRule<MainActivity> mainActivity =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,
                    true);

    @Before
    public void setup() throws Exception {
        mainActivity.launchActivity(new Intent());

        // MockitoAnnotations.initMocks(RecipeListViewTest.class);
 /*       mockRecipeListener = Mockito.mock(RecipeListModelContract.RecipeGetAllListener.class);

        final Instrumentation instrumentation =
                InstrumentationRegistry.getInstrumentation();

        BusbyBakingApplication busbyBakingApplication =
                (BusbyBakingApplication)instrumentation
                        .getTargetContext()
                        .getApplicationContext();

        TestBusbyBakingComponent component =
                (TestBusbyBakingComponent)busbyBakingApplication.getApplicationComponent();

        component.inject(RecipeListViewTest.this);
*/
    }

    @Test
    public void testShouldDisplayDetailOfCooking() throws Exception {
        onView(withId(R.id.rvRecipeList)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

 //       onView(withId(R.id.rvRecipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public void shouldReturnAListOfRecipes() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);

        Mockito.when(recipesAPI.getAllRecipes()).thenReturn(Observable.just(recipeList));
   //     Mockito.doNothing().when(recipeListModelContract.getRecipesFromAPI(mockRecipeListener));

        mainActivity.launchActivity(new Intent());
        recipeListModelContract.getRecipesFromAPI(mockRecipeListener);
/*
        RecipeListModelContract.RecipeGetAllListener mockRecipeListener
                = Mockito.mock(RecipeListModelContract.RecipeGetAllListener.class);
        recipeListModelContract.getRecipesFromAPI(mockRecipeListener);
*/

        onView(withId(R.id.tvRecipeName))
                .check(matches(withText("Test Brownies")));

/*
        Mockito.verify(mockRecipeListener, times(1)).onRecipeGetAllSuccess(recipeList);
        Mockito.verify(mockRecipeListener, never()).onRecipeGetAllFailure(anyString());
*/
    }
}