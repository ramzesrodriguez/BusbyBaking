package me.androidbox.busbybaking.recipedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.androidbox.busbybaking.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, RecipeDetailView.getInstance(), RecipeDetailView.TAG)
                    .commit();
        }
    }
}
