package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.di.BaseRobolectricTestRunner;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.networkapi.RecipesAPI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by smason on 6/23/2017 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeListModelImpTest extends BaseRobolectricTestRunner {

    @Mock Disposable subscription;
    @Mock RecipesAPI recipesAPI;
    @Mock RecipeListModelContract.RecipeGetAllListener recipeGetAllListener;
    @Mock List<Recipe> recipes;

    @Inject RecipeSchedulers recipeSchedulers;
    @Inject RecipeAdapter recipeAdapter;

    private RecipeListModelImp recipeListModel;

    @Before
    public void setup() {
        getTestComponent().inject(RecipeListModelImpTest.this);
        recipeListModel = new RecipeListModelImp(recipesAPI, recipeSchedulers);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowExceptionOnNullParameter() {
        recipeListModel = new RecipeListModelImp(null, null);
    }

    @Test
    public void testRecipeListModelShouldNotBeNull() {
        assertNotNull(recipeListModel);
    }

    @Test
    public void testRecipeAdapterShouldNotBeNull() {
        assertThat(recipeAdapter, is(notNullValue()));
    }

    @Test
    public void testShouldGetRecipesFromAPI() {
        when(recipesAPI.getAllRecipes()).thenReturn(Observable.just(recipes));

        recipeListModel.getRecipesFromAPI(recipeGetAllListener);

        verify(recipesAPI, times(1)).getAllRecipes();
        verify(recipeGetAllListener, times(1)).onRecipeGetAllSuccess(recipes);
        verify(recipeGetAllListener, never()).onRecipeGetAllFailure(anyString());
    }

    @Test
    public void testShouldFailToGetRecipesFromAPI() {
        when(recipesAPI.getAllRecipes())
                .thenReturn(io.reactivex.Observable
                        .<List<Recipe>>error(new Throwable(new RuntimeException("Failed to get recipes"))));

        recipeListModel.getRecipesFromAPI(recipeGetAllListener);

        verify(recipesAPI, times(1)).getAllRecipes();
        verify(recipeGetAllListener, times(1)).onRecipeGetAllFailure(anyString());
        verify(recipeGetAllListener, never()).onRecipeGetAllSuccess(recipes);
    }

    @Ignore("FIXME")
    @Test
    public void testShouldShutdown() {
        when(subscription.isDisposed()).thenReturn(false);
        final Field subscriptionField;

        try {
            subscriptionField = recipeListModel.getClass().getDeclaredField("subscription");
            subscriptionField.setAccessible(true);
            subscriptionField.set(recipeListModel, subscription);
        }
        catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }

        recipeListModel.releaseResources();

        verify(subscription, times(1)).isDisposed();
    }

    @Test
    public void testReleaseSubscriptionsAreCleared() {
        assertThat(recipeListModel.compositeDisposable, is(notNullValue()));
        when(recipesAPI.getAllRecipes()).thenReturn(Observable.just(recipes));
        assertThat(recipeListModel.compositeDisposable.isDisposed(), is(false));
        recipeListModel.getRecipesFromAPI(recipeGetAllListener);

        recipeListModel.releaseResources();

        assertThat(recipeListModel.compositeDisposable.isDisposed(), is(false));
    }
}