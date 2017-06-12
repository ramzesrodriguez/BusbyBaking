package me.androidbox.busbybaking.recipeVideoSteps;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.google.android.exoplayer2.SimpleExoPlayer;

import org.parceler.Parcels;

import javax.inject.Inject;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.model.Steps;
import timber.log.Timber;

import static me.androidbox.busbybaking.recipeVideoSteps.RecipeVideoStepsActivity.STEPS_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeVideoStepsView extends Fragment {
    public static final String TAG = RecipeVideoStepsView.class.getSimpleName();

    @Inject SimpleExoPlayer simpleExoPlayer;

    public RecipeVideoStepsView() {
        // Required empty public constructor
    }

    public static RecipeVideoStepsView newInstance(Bundle args) {
        RecipeVideoStepsView recipeVideoStepsView = new RecipeVideoStepsView();
        recipeVideoStepsView.setArguments(args);

        return recipeVideoStepsView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        Steps steps = Parcels.unwrap(bundle.getParcelable(STEPS_KEY));

        ((BusbyBakingApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(RecipeVideoStepsView.this);

        if(simpleExoPlayer == null) {
            Timber.e("simpleExoPlayer == null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_video_steps_view, container, false);
    }
}
