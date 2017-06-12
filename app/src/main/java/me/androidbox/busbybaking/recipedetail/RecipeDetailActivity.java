package me.androidbox.busbybaking.recipedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {
    public static final String RECIPE_KEY = "recipe_key";
    @InjectExtra Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Dart.inject(RecipeDetailActivity.this);

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_KEY, Parcels.wrap(recipe));

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, RecipeDetailView.getInstance(args), RecipeDetailView.TAG)
                    .commit();
        }
    }
}
