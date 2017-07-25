package me.androidbox.busbybaking.recipieslist;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Observable;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.DaggerTestBusbyBakingComponent;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.RecipeListComponent;
import me.androidbox.busbybaking.di.RecipeListModule;
import me.androidbox.busbybaking.di.TestBusbyBakingComponent;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by steve on 5/31/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListViewAndroidTest {
    @Inject RecipeListModelContract recipeListModelContract;
    @Inject RecipesAPI recipesAPI;

    @Mock RecipeListModelContract.RecipeGetAllListener mockRecipeListener;
    @Mock List<Recipe> mockRecipeList;
 //   @Mock RecipesAPI recipesAPI;
/*

    @Rule
    public DaggerMockRule<TestBusbyBakingComponent> daggerMockRule =
            new DaggerMockRule<>(TestBusbyBakingComponent.class, new MockRecipeListModule())
            .set(componet -> stub().setComponent(componet));
*/

    @Singleton
    @Component(modules = MockRecipeListModule.class)
    public interface MockRecipeListModuleComponent extends RecipeListComponent {
    }

    private BusbyBakingApplication getApplication() {
        return (BusbyBakingApplication) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivity =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,
                    false);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        MockRecipeListModuleComponent testComponent = DaggerRecipeListViewAndroidTest_MockRecipeListModuleComponent.builder()
                .mockRecipeListModule(new MockRecipeListModule())
                .build();

        getApplication().setRecipeListComponent(testComponent);
    //    RecipeListModelContract recipeListModelContract =
/*
        TestBusbyBakingComponent testBusbyBakingComponent = DaggerTestBusbyBakingComponent.builder()
                .mockRecipeListModule(new MockRecipeListModule())
                .build();

        getApplication().setRecipeListComponent(testBusbyBakingComponent);

        testBusbyBakingComponent.inject(RecipeListViewAndroidTest.this);
*/
    }

    @Test
    public void testShouldDisplayDetailOfCooking() throws Exception {
        onView(withId(R.id.rvRecipeList)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        try{
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.rvRecipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.recipe_detail_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.rvSteps)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.recipe_video_steps_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void shouldReturnAListOfRecipes() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);
        recipeList.add(recipe);

        when(recipesAPI.getAllRecipes()).thenReturn(Observable.just(recipeList));
        doNothing().when(mockRecipeListener).onRecipeGetAllSuccess(recipeList);



   //     Mockito.doNothing().when(recipeListModelContract.getRecipesFromAPI(mockRecipeListener));

    //    recipeListModelContract.getRecipesFromAPI(mockRecipeListener);
/*
        RecipeListModelContract.RecipeGetAllListener mockRecipeListener
                = Mockito.mock(RecipeListModelContract.RecipeGetAllListener.class);
        recipeListModelContract.getRecipesFromAPI(mockRecipeListener);
*/

        mainActivity.launchActivity(null);

        try{
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.rvRecipeList)).check(matches(hasDescendant(withText("Brownies"))));
        /*
        Mockito.verify(mockRecipeListener, times(1)).onRecipeGetAllSuccess(recipeList);
        Mockito.verify(mockRecipeListener, never()).onRecipeGetAllFailure(anyString());
*/
    }
}