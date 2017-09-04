package me.androidbox.busbybaking.adapters;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.testrunner.BaseRobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 7/4/17.
 */
public class RecipeAdapterTest extends BaseRobolectricTestRunner {
    @Inject RecipeAdapter recipeAdapter;
    private ViewGroup linearLayout;
    private RecipeListViewHolder recipeListViewHolder;

    @Before
    public void setup() {
        linearLayout = new LinearLayout(ShadowApplication.getInstance().getApplicationContext());

        getTestComponent().inject(RecipeAdapterTest.this);

        recipeAdapter.recipeList = createRecipes();
    }

    @Test
    public void testRecipeAdapterShouldNotBeNull() {
        assertThat(recipeAdapter, is(notNullValue()));
    }

    @Test
    public void testViewHolderIsCreated() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        assertThat(recipeListViewHolder, is(notNullValue()));
    }

    @Test
    public void testOnBindViewHolder() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        recipeListViewHolder.tvRecipeName = mock(TextView.class);
        recipeListViewHolder.tvQuantity = mock(TextView.class);

        recipeAdapter.onBindViewHolder(recipeListViewHolder, 0);

        verify(recipeListViewHolder.tvRecipeName).setText("Test Brownies");
        verify(recipeListViewHolder.tvQuantity).setText("Quantity: 10");
    }

    private List<Recipe> createRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);
        recipeList.add(recipe);

        return recipeList;
    }
}
