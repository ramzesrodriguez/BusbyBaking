package me.androidbox.busbybaking.di;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;

/**
 * Created by smason on 6/30/2017 AD.
 */
@Module
public class RecipeListViewHolderModule {

    @Provides
    @IntoMap
    @StringKey("RECIPELIST")
    RecipeListViewHolderFactory provideRecipeListViewHolder() {
        return new RecipeListViewHolderFactory();
    }
}
