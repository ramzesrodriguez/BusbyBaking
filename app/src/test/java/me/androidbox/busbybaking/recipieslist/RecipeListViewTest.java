package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.model.Recipe;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 6/14/2017 AD.
 */
public class RecipeListViewTest {
    @Mock RecipeListPresenterContract presenter;
    @Mock RecipeItemClickListener recipeItemClickListener;
    @Mock List<Recipe> recipe;
    @Mock RecipeAdapter recipeAdapter;
    private RecipeListView fragment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(RecipeListViewTest.this);
        fragment = RecipeListView.newInstance();
        fragment.recipeItemClickListener = recipeItemClickListener;
        fragment.recipeListPresenterContract = presenter;
        fragment.recipeAdapter = recipeAdapter;
    }

    @Test
    public void testFragmentShouldNotBeNull() {
        assertNotNull(fragment);
    }

    @Test
    public void testRecipeItemClickListenerShouldNotBeNull() {
        assertNotNull(fragment.recipeItemClickListener);
    }

    @Test
    public void testShouldGetAllRecipes() {
        RecipeListView spy = Mockito.spy(fragment);
        doNothing().when(spy).fillAdapter(recipe);

        spy.displayRecipeData(recipe);

        verify(spy, times(1)).fillAdapter(recipe);
        verify(recipeItemClickListener, times(1)).onRecipeItemClick();
    }

    @Test
    public void testShouldCallGetAllRecipes() {
        fragment.getAllRecipes();

        verify(presenter, times(1)).retrieveAllRecipes();
    }

    @Test
    public void testShouldFillAdapter() {
        fragment.fillAdapter(recipe);

        verify(recipeAdapter, times(1)).fillRecipeData(recipe);
    }
}

























































/*

public class OptInNotificationViewFragmentTest {

    private OptInNotificationViewFragment fragment;
    @Mock OptInNotificationPresenterImp presenter;
    @Mock IBaseActivityNavigator activityNavigator;
    @Mock OptInFragmentListener optInFragmentListener;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        fragment = OptInNotificationViewFragment.newInstance();
        fragment.optInNotificationPresenter = presenter;
        fragment.activityNavigator = activityNavigator;
        fragment.optInFragmentListener = optInFragmentListener;
    }

    @Test
    public void testTurnOnNotification_displayApplicationSettingsNotification() throws Exception {
        fragment.turnOnNotification();

        Mockito.verify(presenter).displayApplicationSettingsNotification();
    }

    @Test
    public void testEnableNotNowForNotifications_setLastCheckedDateForNotifications() throws Exception {
        fragment.enableNotNowForNotifications();

        Mockito.verify(presenter).setLastCheckedDateForNotifications();
    }

    @Test
    public void testDisplayOptInNotificationSettings_openAppNotificationSettings() throws Exception {
        fragment.displayOptInNotificationSettings();

        Mockito.verify(activityNavigator).openAppNotificationsSettings(fragment.getActivity());
    }

    @Test
    public void testOptInNotificationScreen_closeOptIn() throws Exception {
        fragment.closeScreen();

        Mockito.verify(optInFragmentListener).onCloseOptInNotification();
    }
}*/
