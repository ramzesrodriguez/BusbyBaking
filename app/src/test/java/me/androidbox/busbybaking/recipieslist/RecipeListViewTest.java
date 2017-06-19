package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by smason on 6/14/2017 AD.
 */
public class RecipeListViewTest {
    private RecipeListView fragment;
    @Mock RecipeListPresenterContract presenter;
    @Mock RecipeItemClickListener recipeItemClickListener;
    @Mock List<Recipe> recipe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(RecipeListViewTest.this);
        fragment = RecipeListView.newInstance();
    }

    @Test
    public void testShouldGetAllRecipes() {
        RecipeListView spy = Mockito.spy(fragment);
        doNothing().when(spy).setupDataBinding(recipe);

        fragment.displayRecipeData(recipe);

        verify(recipeItemClickListener, times(1)).onRecipeItemClick();
    }


/*
    public void displayRecipeData(List<Recipe> recipeList) {
        Timber.d("displayData: %d", recipeList.size());

        setupDataBinding(recipeList);

        recipeItemClickListener.onRecipeItem();
    }
*/

    @Test
    public void testFragmentNotNull() throws Exception {
        assertNotNull(fragment);
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
