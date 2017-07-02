package me.androidbox.busbybaking.recipieslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipeItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, RecipeListView.newInstance(), RecipeListView.TAG)
                    .commit();
        }
    }

    @Override
    public void onRecipeItemClick(Recipe recipe) {
        Timber.d("onRecipeItemClick");
        final Intent intent = Henson.with(MainActivity.this)
                .gotoRecipeDetailActivity()
                .recipe(recipe)
                .build();

        startActivity(intent);
    }
}
