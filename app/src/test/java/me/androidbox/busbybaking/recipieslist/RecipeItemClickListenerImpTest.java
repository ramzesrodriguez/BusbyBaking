package me.androidbox.busbybaking.recipieslist;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;
import me.androidbox.busbybaking.recipedetail.RecipeDetailActivity;
import me.androidbox.busbybaking.testrunner.BaseRobolectricTestRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by steve on 9/5/17.
 */
public class RecipeItemClickListenerImpTest extends BaseRobolectricTestRunner {
    private RecipeItemClickListenerImp recipeItemClickListenerImp;

    @Before
    public void setup() {
        recipeItemClickListenerImp = new RecipeItemClickListenerImp();
    }

    @Test
    public void shouldStartDetailActivity() {
        final Context context = ShadowApplication.getInstance().getApplicationContext();

        final Recipe recipe = new Recipe();
        recipeItemClickListenerImp.onRecipeItemClick(recipe, context);

        final MainActivity activity = Robolectric.buildActivity(MainActivity.class).get();
        final ShadowActivity shadowActivity = shadowOf(activity);
        final Intent intent = shadowActivity.getNextStartedActivity();
        final ShadowIntent shadowIntent = shadowOf(intent);

        assertThat(shadowIntent.getIntentClass().getName(), equalTo(RecipeDetailActivity.class.getName()));
    }
}