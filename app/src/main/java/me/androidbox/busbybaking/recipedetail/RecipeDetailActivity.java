package me.androidbox.busbybaking.recipedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {
    @InjectExtra Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Dart.inject(RecipeDetailActivity.this);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, RecipeDetailView.getInstance(), RecipeDetailView.TAG)
                    .commit();
        }
    }
}
