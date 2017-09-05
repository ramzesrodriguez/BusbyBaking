package me.androidbox.busbybaking.recipieslist;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;
import me.androidbox.busbybaking.recipedetail.RecipeDetailActivity$$IntentBuilder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by steve on 9/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeItemClickListenerImpTest {
    private RecipeItemClickListenerImp recipeItemClickListenerImp;

    @Mock Context context;
    @Mock Intent intent;

    @Before
    public void setup() {
        recipeItemClickListenerImp = new RecipeItemClickListenerImp();
    }

    @Ignore("Issue with put(bundle)")
    @Test
    public void shouldStartDetailActivity() {
        Recipe recipe = new Recipe();
        recipeItemClickListenerImp.onRecipeItemClick(recipe, context);

        final Intent intent = Henson.with(context)
                .gotoRecipeDetailActivity()
                .recipe(recipe)
                .build();

        verify(context).startActivity(intent);
    }
}