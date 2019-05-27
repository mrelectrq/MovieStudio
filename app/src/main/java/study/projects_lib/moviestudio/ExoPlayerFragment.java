package study.projects_lib.moviestudio;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerFragment extends DialogFragment {

    private TrackSelector trackSelector;
    private ExoPlayer player;
    private SimpleExoPlayerView simpleExoPlayerView;
    private MediaSource mediaSource;
    private Dialog mFullScreenDialog;
    private boolean mExoPlayerFullscreen=false;
    private ImageView mFullScreenIcon;
    private FrameLayout mFullScreenButton;
    private String urlMp4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppFullScreenTheme);
        initializePlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exo_player, container, false);

    }

    public void initializePlayer(){
        Bundle bundle = getActivity().getIntent().getExtras();
        urlMp4 =bundle.getParcelable("mp4");

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectorFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectorFactory);

        //Initializare player
        player = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);

        //Initializare simpleExoplayerView
        simpleExoPlayerView = simpleExoPlayerView.findViewById(R.id.exoplayer);
        simpleExoPlayerView.setPlayer(player);



        String streamUrl = urlMp4;
        String userAgent = Util.getUserAgent(getContext(), "MovieStudio");
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, null, DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);

        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory=new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "MovieStudio"), defaultBandwidthMeter);
        Uri dataUri= Uri.parse(streamUrl);

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        mediaSource = new ExtractorMediaSource(dataUri, dataSourceFactory, extractorsFactory, null, null);

        player.prepare(mediaSource);

//        initFullscreenButton();
//        initFullscreenDialog();
        simpleExoPlayerView.getPlayer().setPlayWhenReady(false);

    }


}
