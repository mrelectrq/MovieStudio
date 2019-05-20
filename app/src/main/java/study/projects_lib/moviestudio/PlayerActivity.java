package study.projects_lib.moviestudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class PlayerActivity extends AppCompatActivity  {

    private SimpleExoPlayer player;
    private SimpleExoPlayerView exoPlayerView;
    private Dialog mFullScreenDialog;
    private boolean mExoPlayerFullscreen = false;
    private ImageView mFullScreenIcon;
    private FrameLayout mFullScreenButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Log.d("OnCreate", "Started PlayerActivity");

        initializePlayer();
    }


//    private void getIncomingIntent(){
//
//        if(getIntent().hasExtra("image_name")&& getIntent().hasExtra("mp4_url")){
//
//            String imageUrl =getIntent().getStringExtra("image_name");
//            String imageName = getIntent().getStringExtra("mp4_url");
//        }
//
//    }


    private void initFullscreenDialog(){

        mFullScreenDialog= new Dialog(this, R.style.AppFullScreenTheme) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen) {
                    closeFullScreenDialog();
                    super.onBackPressed();
                }
            }
        };
    }

    private void openFullscreenDialog(){
        ((ViewGroup)exoPlayerView.getParent()).removeView(exoPlayerView);
        mFullScreenDialog.addContentView(exoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(PlayerActivity.this, R.drawable.ic_fullscreen_skrink));
        mExoPlayerFullscreen=true;
        mFullScreenDialog.show();
    }

    private void closeFullScreenDialog(){
        ((ViewGroup)exoPlayerView.getParent()).removeView(exoPlayerView);
        ((LinearLayout)findViewById(R.id.main_media_frame)).addView(exoPlayerView);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(PlayerActivity.this, R.drawable.ic_fullscreen_expand));

    }

    private void iniFullscreenButton(){

        PlaybackControlView controlView =exoPlayerView.findViewById(R.id.exo_controller);
        mFullScreenIcon=controlView.findViewById(R.id.fullscreen_icon);
        mFullScreenButton=controlView.findViewById(R.id.fullscreen_button);
        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else closeFullScreenDialog();
            }
        });

    }


    private void initializePlayer(){

        try {
            initFullscreenDialog();
            iniFullscreenButton();

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
