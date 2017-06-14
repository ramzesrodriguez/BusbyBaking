package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by smason on 6/14/2017 AD.
 */
public class RecipeListViewTest {
    private RecipeListView fragment;
    @Mock RecipeListPresenterContract presenter;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(RecipeListViewTest.this);

        fragment = RecipeListView.newInstance();
        fragment.recipeListPresenterContract = presenter;
    }



    @Test
    public void testFragmentNotNull() throws Exception {
        assertNotNull(fragment);
    }
}


/*

public class OptInNotificationViewFragmentTest {

    private OptInNotificationViewFragment fragment;
    @Mock
    OptInNotificationPresenterImp presenter;
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
