package me.androidbox.busbybaking.di;

import android.content.Context;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by steve on 6/13/17.
 */
@Module
public class ExoPlayerModule {
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
    public String providesUserAgent(Context context) {
        return Util.getUserAgent(context, context.getPackageName());
    }

    @Singleton
    @Provides
    public DataSource.Factory providesDataSourceFactory(Context context) {
        return new DefaultDataSourceFactory(context, providesUserAgent(context));
    }

    @Singleton
    @Provides
    public ExtractorsFactory providesExtractorsFactory() {
        return new DefaultExtractorsFactory();
    }

    @Singleton
    @Provides
    public SimpleExoPlayer providesSimpleExoPlayer(Context context, TrackSelector trackSelector) {
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    }
}
