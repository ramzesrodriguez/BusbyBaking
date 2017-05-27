package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeListModuleContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModuleImp;
import me.androidbox.busbybaking.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class NetworkModule {

    private GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    private Retrofit getRetrofit(final String baseline) {
        return new Retrofit.Builder()
                .baseUrl(baseline)
                .addConverterFactory(getGsonConverterFactory())
                .build();
    }

    @Singleton
    @Provides
    public RecipesAPI providesRecipesAPI() {
        return getRetrofit(Constants.BASELINE).create(RecipesAPI.class);
    }

    @Singleton
    @Provides
    public RecipeListModuleContract providesRecipeListModel() {
        return new RecipeListModuleImp(providesRecipesAPI());
    }
}
