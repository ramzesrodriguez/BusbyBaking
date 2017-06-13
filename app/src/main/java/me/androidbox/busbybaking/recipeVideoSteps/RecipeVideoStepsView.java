package me.androidbox.busbybaking.recipeVideoSteps;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
    @Inject BandwidthMeter bandwidthMeter;
    @Inject AdaptiveTrackSelection.Factory factory;
    @Inject TrackSelector trackSelector;

    @BindView(R.id.simpleExoPlayerView) SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.tvDescription) TextView tvDescription;

    private Unbinder unbinder;
    private Steps steps;

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

        final Bundle bundle = getArguments();
        steps = Parcels.unwrap(bundle.getParcelable(STEPS_KEY));

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
        final View view = inflater.inflate(R.layout.recipe_video_steps_view, container, false);
        unbinder = ButterKnife.bind(RecipeVideoStepsView.this, view);

        tvDescription.setText(steps.getShortDescription());

        playStepsVideo();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void playStepsVideo() {
        simpleExoPlayerView.setPlayer(simpleExoPlayer);
        // Uri uri = new Uri.Builder().appendPath("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4").build();

        Uri uri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

        MediaSource mediaSource = new ExtractorMediaSource(
                uri,
                null,
                null,
                null,
                null);

        simpleExoPlayer.prepare(mediaSource);
    }
}
