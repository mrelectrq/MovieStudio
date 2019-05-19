package study.projects_lib.moviestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.module.ManifestParser;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class PlayerActivity extends AppCompatActivity  {

    private SimpleExoPlayer player;
    private SimpleExoPlayerView exoPlayerView;

    String videoURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Log.d("OnCreate", "Started PlayerActivity");

        initializePlayer();
    }


    private void getIncomingIntent(){

        if(getIntent().hasExtra("image_name")&& getIntent().hasExtra("mp4_url")){

            String imageUrl =getIntent().getStringExtra("image_name");
            String imageName = getIntent().getStringExtra("mp4_url");
        }

    }

    private void initializePlayer(){

        try {

            player = ExoPlayerFactory.newSimpleInstance(this);
            exoPlayerView =  findViewById(R.id.video_view);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoURI = Uri.parse(getIntent().getStringExtra("mp4_url"));

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("ExoplayerVideo");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource videosource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setPlayer(player);
            player.prepare(videosource);
            player.setPlayWhenReady(true);
        }catch (Exception e){
            Log.e("PlayerActivity", "exoplayer Error" + e.toString());
        }



    }


    // https://medium.com/fungjai/playing-video-by-exoplayer-b97903be0b33

}
