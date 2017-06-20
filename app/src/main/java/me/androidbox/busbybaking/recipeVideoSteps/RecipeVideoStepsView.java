package me.androidbox.busbybaking.recipeVideoSteps;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;

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
public class RecipeVideoStepsView extends Fragment implements ExoPlayer.EventListener {
    public static final String TAG = RecipeVideoStepsView.class.getSimpleName();

    @Inject SimpleExoPlayer simpleExoPlayer;
    @Inject BandwidthMeter bandwidthMeter;
    @Inject AdaptiveTrackSelection.Factory factory;
    @Inject TrackSelector trackSelector;
    @Inject DataSource.Factory dataSourceFactory;
    @Inject ExtractorsFactory extractorsFactory;

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

        if(simpleExoPlayer != null) {
            Timber.d("simpleExoPlayer != null");
            simpleExoPlayer.addListener(RecipeVideoStepsView.this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.recipe_video_steps_view, container, false);
        unbinder = ButterKnife.bind(RecipeVideoStepsView.this, view);

        tvDescription.setText(steps.getShortDescription());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        playStepsVideo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.d("onDestoryView");
        unbinder.unbind();
    }

    private void releaseMediaResources() {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d("onStop");
        releaseMediaResources();
    }

    private void playStepsVideo() {
        if(URLUtil.isValidUrl(steps.getVideoURL())) {
            Timber.d(steps.getVideoURL());

            Uri uri = Uri.parse(steps.getVideoURL()); //  "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            MediaSource mediaSource = new ExtractorMediaSource(
                    uri,
                    dataSourceFactory,
                    extractorsFactory,
                    null,
                    null);

            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object o) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {

    }

    @Override
    public void onLoadingChanged(boolean b) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playBackState) {
        if((simpleExoPlayer.getPlaybackState() == ExoPlayer.STATE_READY) && playWhenReady) {
            Timber.d("onPlayerStateChanged: PLAYING");
        }
        else {
            Timber.d("onPlayerStateChanged: PAUSED");
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }
}
