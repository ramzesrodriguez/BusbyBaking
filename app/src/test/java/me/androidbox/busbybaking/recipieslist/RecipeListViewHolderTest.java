package me.androidbox.busbybaking.recipieslist;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowApplication;

import java.util.Map;

import javax.inject.Inject;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.testrunner.BaseRobolectricTestRunner;
import me.androidbox.busbybaking.utils.Constants;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by steve on 9/2/17.
 */
public class RecipeListViewHolderTest extends BaseRobolectricTestRunner {

    @Inject Map<Integer, RecipeListViewHolderFactory> viewHolderFactories;
    @Inject @LayoutRes int recipeItem;
    private RecipeListViewHolder recipeListViewHolder;

    @Before
    public void setup() {
        getTestComponent().inject(RecipeListViewHolderTest.this);

        final View view = View.inflate(
                ShadowApplication.getInstance().getApplicationContext(),
                recipeItem,
                new LinearLayout(ShadowApplication.getInstance().getApplicationContext()));

        recipeListViewHolder = viewHolderFactories.get(Constants.RECIPE_LIST).create(view);
        assertThat(recipeListViewHolder, is(notNullValue()));
    }

    private Recipe createRecipeData() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);

        return recipe;
    }

    @Test
    public void testRecipeDataIsPopulated() {
        recipeListViewHolder.populateDate(createRecipeData());

        assertThat(recipeListViewHolder.tvQuantity.getText().toString(), is("Quantity: 10"));
        assertThat(recipeListViewHolder.tvRecipeName.getText().toString(), is("Test Brownies"));
    }
}