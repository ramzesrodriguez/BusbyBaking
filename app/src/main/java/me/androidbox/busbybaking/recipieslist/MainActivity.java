package me.androidbox.busbybaking.recipieslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.androidbox.busbybaking.R;

public class MainActivity extends AppCompatActivity {
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



}
