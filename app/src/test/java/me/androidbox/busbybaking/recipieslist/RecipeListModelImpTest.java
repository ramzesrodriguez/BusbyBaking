package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.androidbox.busbybaking.networkapi.RecipesAPI;
import rx.Subscription;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by smason on 6/23/2017 AD.
 */
public class RecipeListModelImpTest {

    @Mock Subscription subscription;
    @Mock RecipesAPI recipesAPI;
    @Mock RecipeListModelContract.RecipeGetAllListener recipeGetAllListener;
    private RecipeListModelContract recipeListModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(RecipeListModelImpTest.this);
        recipeListModel = new RecipeListModelImp(recipesAPI);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowExceptionOnNullParameter() {
        recipeListModel = new RecipeListModelImp(null);
    }

    @Test
    public void testRecipeListModelShouldNotBeNull() {
        assertNotNull(recipeListModel);
    }
/*

    public void testShouldGetAllRecipes() {
        RecipeListView spy = Mockito.spy(fragment);
        doNothing().when(spy).fillAdapter(recipe);

        spy.displayRecipeData(recipe);

        verify(spy, times(1)).fillAdapter(recipe);
        verify(recipeItemClickListener, times(1)).onRecipeItemClick();
    }
*/

    @Test
    public void testGetRecipesFromAPI() {
        RecipeListModelContract recipeListModelSpy = spy(recipeListModel);
        RecipesAPI recipeApiSpy = spy(recipesAPI);
        doNothing().when(recipeApiSpy).getAllRecipes();

        recipeListModelSpy.getRecipesFromAPI(recipeGetAllListener);

        verify(recipesAPI, times(1)).getAllRecipes();
    }

    @Test
    public void testShouldShutdown() {
        recipeListModel.shutdown();

        verify(subscription, times(1)).unsubscribe();
    }
}