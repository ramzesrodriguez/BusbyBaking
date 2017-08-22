package me.androidbox.busbybaking.di;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by steve on 7/28/17.
 */
@Module
public class MockExoPlayerModule {
    @Singleton
    @Provides
    public BandwidthMeter providesBandwidthMeter() {
        return Mockito.mock(BandwidthMeter.class);
    }

    @Singleton
    @Provides
    public AdaptiveTrackSelection.Factory providesAdaptiveTrackSelection() {
        return Mockito.mock(AdaptiveTrackSelection.Factory.class);
    }

    @Singleton
    @Provides
    public TrackSelector providesTrackSelector() {
        return Mockito.mock(TrackSelector.class);
    }

    @Singleton
    @Provides
    public String providesUserAgent() {
        return Mockito.mock(String.class);
    }

    @Singleton
    @Provides
    public DataSource.Factory providesDataSourceFactory() {
        return Mockito.mock(DefaultDataSourceFactory.class);
    }

    @Singleton
    @Provides
    public ExtractorsFactory providesExtractorsFactory() {
        return Mockito.mock(ExtractorsFactory.class);
    }

    @Singleton
    @Provides
    public SimpleExoPlayer providesSimpleExoPlayer() {
        return Mockito.mock(SimpleExoPlayer.class);
    }
}
