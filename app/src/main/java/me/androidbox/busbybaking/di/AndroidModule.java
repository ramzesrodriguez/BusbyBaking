package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by steve on 5/29/17.
 */
@Module
public class AndroidModule {
    private final Context context;

    public AndroidModule(Application application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context providesContext() {
        return context;
    }

    @Singleton
    @Provides
    public Resources providesResources() {
        return context.getResources();
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public BandwidthMeter providesBandwidthMeter() {
        return new DefaultBandwidthMeter();
    }

    @Singleton
    @Provides
    public AdaptiveTrackSelection.Factory providesAdaptiveTrackSelection(BandwidthMeter bandwidthMeter) {
        return new AdaptiveTrackSelection.Factory(bandwidthMeter);
    }

    @Singleton
    @Provides
    public TrackSelector providesTrackSelector(AdaptiveTrackSelection.Factory factory) {
        return new DefaultTrackSelector(factory);
    }

    @Singleton
    @Provides
    public SimpleExoPlayer providesSimpleExoPlayer(Context context, TrackSelector trackSelector) {
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSystemServices(Context context, String serviceName) {
        return (T)context.getSystemService(serviceName);
    }
}
