package me.androidbox.busbybaking.recipieslist;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 5/27/17.
 */

public class RecipeListPresenterImp
        extends
        MvpBasePresenter<RecipeListViewContract>
        implements
        RecipeListPresenterContract, RecipeListModelContract.RecipeGetAllListener {

    private RecipeListModelContract recipeListModelContract;

    @Inject
    public RecipeListPresenterImp(@NonNull RecipeListModelContract recipeListModelContract) {
        this.recipeListModelContract = Preconditions.checkNotNull(recipeListModelContract);
    }

    @Override
    public void retrieveAllRecipes() {
        recipeListModelContract.getRecipesFromAPI(RecipeListPresenterImp.this);
    }

    @Override
    public void onRecipeGetAllSuccess(List<Recipe> recipeList) {
        if(isViewAttached() && getView() != null) {
            getView().displayRecipeData(recipeList);
        }
    }

    @Override
    public void onRecipeGetAllFailure(String errorMessage) {
        if(isViewAttached() && getView() != null) {
            getView().displayRecipeError(errorMessage);
        }
    }
}

public class OptInNotificationPresenterImp
        extends BaseMvpPresenter<OptInNotificationViewContract>
        implements OptInNotificationPresenterContract {

    private final IUserAchievementsRepository userAchievementsRepository;

    public OptInNotificationPresenterImp(
            @Nonnull IUserAchievementsRepository userAchievementsRepository,
            ISchedulerFactory schedulerFactory) {
        super(schedulerFactory);

        this.userAchievementsRepository = Preconditions.checkNotNull(userAchievementsRepository);
    }

    @Override
    public void displayApplicationSettingsNotification() {
        if (getView() != null && isViewAttached()) {
            getView().displayOptInNotificationSettings();
            getView().closeScreen();
        }
    }

    @Override
    public void setLastCheckedDateForNotifications() {
        userAchievementsRepository.setTravelerOptInNotificationLastCheckedDate(Clocks.today());
        if (getView() != null && isViewAttached()) {
            getView().closeScreen();
        }
    }
}