package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListView;

/**
 * Created by steve on 5/29/17.
 */
@RecipeListScope
@Subcomponent(modules = {RecipeListModule.class})
public interface RecipeListComponent {
    void inject(RecipeListView target);
}
