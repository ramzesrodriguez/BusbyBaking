package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.model.Recipe;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 6/14/2017 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeListViewTest {
    @Mock RecipeListPresenterContract presenter;
    @Mock List<Recipe> recipe;
    @Mock RecipeAdapter recipeAdapter;
    private RecipeListView fragment;

    @Before
    public void setup() {
        fragment = RecipeListView.newInstance();
        fragment.recipeListPresenterContract = presenter;
        fragment.recipeAdapter = recipeAdapter;
    }

    @Test
    public void testFragmentShouldNotBeNull() {
        assertThat(fragment, is(notNullValue()));
    }

    @Test
    public void testShouldGetAllRecipes() {
        RecipeListView spy = Mockito.spy(fragment);
        doNothing().when(spy).fillAdapter(recipe);

        spy.displayRecipeData(recipe);

        verify(spy, times(1)).fillAdapter(recipe);
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
