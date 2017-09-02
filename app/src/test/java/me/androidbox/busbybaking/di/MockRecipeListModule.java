package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListenerImp;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.utils.Constants;

/**
 * Created by steve on 8/25/17.
 */
@Module
public class MockRecipeListModule {
    @Singleton
    @Provides
    RecipeItemClickListener providesRecipeItemClickListenerImp() {
        return new RecipeItemClickListenerImp();
    }

    @Singleton
    @Provides
    RecipeListModelContract providesRecipeListModel() {
        return Mockito.mock(RecipeListModelContract.class, Mockito.RETURNS_DEEP_STUBS);
    }

    @Singleton
    @Provides
    RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelContract recipeListModelContract) {
        return new RecipeListPresenterImp(recipeListModelContract);
    }

    @Singleton
    @Provides
    RecipeAdapter providesRecipeAdapter(RecipeItemClickListener recipeItemClickListener,
                                        Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        return new RecipeAdapter(recipeItemClickListener, viewHolderFactories);
    }

    @Singleton
    @Provides
    @IntoMap
    @IntKey(Constants.RECIPE_LIST)
    RecipeListViewHolderFactory provideRecipeListViewHolder() {
        return new RecipeListViewHolderFactory();
    }
}

