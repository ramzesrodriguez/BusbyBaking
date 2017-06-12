package me.androidbox.busbybaking.recipeVideoSteps;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Steps;

public class RecipeVideoStepsActivity extends AppCompatActivity {
    public static final String STEPS_KEY = "steps_key";
    @InjectExtra Steps steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video_steps);

        Dart.inject(RecipeVideoStepsActivity.this);
        Parcelable parcelable = Parcels.wrap(steps);
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEPS_KEY, parcelable);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_video_steps_container, RecipeVideoStepsView.newInstance(bundle), RecipeVideoStepsView.TAG)
                    .commit();
        }
    }
}
