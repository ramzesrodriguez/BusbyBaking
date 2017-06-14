package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 6/14/2017 AD.
 */
public class RecipeListPresenterImpTest {

    private RecipeListModelContract recipeListModelContract;
    private RecipeListViewContract recipeListViewContract;
    private RecipeListPresenterImp recipeListPresenterContract;

    @Before
    public void setUp() throws Exception {
        recipeListModelContract = Mockito.mock(RecipeListModelContract.class);
        recipeListViewContract = Mockito.mock(RecipeListViewContract.class);

        recipeListPresenterContract = new RecipeListPresenterImp(recipeListModelContract);

        recipeListPresenterContract.attachView(recipeListViewContract);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorIsNotNULL() throws Exception {
        new RecipeListPresenterImp(null);
    }

    @Test
    public void testPresenterShouldNotBeNull() {
        assertNotNull(recipeListPresenterContract);
    }

    @Test
    public void testShouldRetrieveAllRecipes() throws Exception {
        recipeListPresenterContract.retrieveAllRecipes();

        verify(recipeListModelContract, times(1))
                .getRecipesFromAPI(recipeListPresenterContract);
    }

    @Test
    public void testOnRecipeGetAllSuccess() throws Exception {
        List<Recipe> recipeList = new ArrayList<>();
        recipeListPresenterContract.onRecipeGetAllSuccess(recipeList);

        verify(recipeListViewContract, times(1))
                .displayRecipeData(recipeList);
    }

    @Test
    public void testOnRecipeGetAllFailure() throws Exception {
        recipeListPresenterContract.onRecipeGetAllFailure(anyString());

        verify(recipeListViewContract, times(1))
                .displayRecipeError(anyString());
    }
}

