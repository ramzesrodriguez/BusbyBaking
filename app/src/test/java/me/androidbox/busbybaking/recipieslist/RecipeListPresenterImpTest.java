package me.androidbox.busbybaking.recipieslist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;

import java.util.ArrayList;
import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by smason on 6/14/2017 AD.
 */
public class RecipeListPresenterImpTest {

    RecipeListModelContract recipeListModelContract;
    RecipeListViewContract recipeListViewContract;
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
    public void testOnRecipeGetAllSuccess() {
        List<Recipe> recipeList = new ArrayList<>();
        verify(recipeListPresenterContract, times(1)).getView().displayRecipeData(recipeList);
    }

    @Test
    public void testShouldRetrieveAllRecipes() throws Exception {
      //  when(recipeListModelContract.getRecipesFromAPI(this));
    }


}


package com.agoda.mobile.core.screens.nha.optinnotification;

        import com.agoda.mobile.consumer.data.repository.IUserAchievementsRepository;
        import com.agoda.mobile.consumer.data.rx.ISchedulerFactory;
        import com.agoda.mobile.core.time.ClocksMockAccessor;

        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.Mockito;
        import org.threeten.bp.LocalDate;
        import org.threeten.bp.LocalDateTime;
        import org.threeten.bp.ZoneOffset;

        import static org.junit.Assert.assertNotNull;
        import static org.mockito.Mockito.times;

public class OptInNotificationPresenterImpTest {

    private IUserAchievementsRepository userAchievementsRepository;
    private ISchedulerFactory iSchedulerFactory;
    private OptInNotificationViewContract optInNotificationViewContract;
    private OptInNotificationPresenterImp optInNotificationPresenter;
    private LocalDate today = LocalDate.of(2016, 12, 1);

    @Before
    public void setup() throws Exception {
        userAchievementsRepository = Mockito.mock(IUserAchievementsRepository.class);
        iSchedulerFactory = Mockito.mock(ISchedulerFactory.class);
        optInNotificationViewContract = Mockito.mock(OptInNotificationViewContract.class);

        ClocksMockAccessor.setFixedClock(LocalDateTime.from(today.atStartOfDay()), ZoneOffset.UTC);

        optInNotificationPresenter =
                new OptInNotificationPresenterImp(userAchievementsRepository, iSchedulerFactory);

        optInNotificationPresenter.attachView(optInNotificationViewContract);
    }

    @Test(expected=NullPointerException.class)
    public void testOptInNotificationPresenter_throwException_whenUserAchievementsRepositoryIsNull() throws Exception {
        new OptInNotificationPresenterImp(null, iSchedulerFactory);
    }

    @Test
    public void testOptInNotification_isNotNull() throws Exception {
        assertNotNull(optInNotificationPresenter);
    }

    @Test
    public void testDisplayApplicationSettingsNotification_notificationsSettingsIsDisplayed() {
        optInNotificationPresenter.displayApplicationSettingsNotification();

        Mockito.verify(optInNotificationViewContract, times(1)).displayOptInNotificationSettings();
        Mockito.verify(optInNotificationViewContract, times(1)).closeScreen();
    }

    @Test
    public void testSetLastCheckedDateForNotification_setsLastDate_whenNotNull() {
        optInNotificationPresenter.setLastCheckedDateForNotifications();

        Mockito.verify(userAchievementsRepository, times(1)).setTravelerOptInNotificationLastCheckedDate(today);
        Mockito.verify(optInNotificationViewContract, times(1)).closeScreen();
    }
}