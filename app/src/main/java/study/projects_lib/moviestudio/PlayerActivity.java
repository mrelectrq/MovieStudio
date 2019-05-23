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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.ManifestParser;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import study.projects_lib.moviestudio.model.ItemFilm;

public class PlayerActivity extends AppCompatActivity  {


    private TrackSelector trackSelector;
    private ExoPlayer player;
    private SimpleExoPlayerView simpleExoPlayerView;
    private MediaSource mediaSource;
    private Dialog mFullScreenDialog;
    private boolean mExoPlayerFullscreen=false;
    private ImageView mFullScreenIcon;


    ItemFilm itemFilm =new ItemFilm();
    private ImageView imageView;
    private TextView namefilm;
    private TextView actors;
    private TextView country;
    private TextView information;
    private FrameLayout mFullScreenButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Log.d("OnCreate", "Started PlayerActivity");

        getDataFromMain();
        initialize();
        initializePlayer();

    }


    //https://geoffledak.com/blog/2017/09/11/how-to-add-a-fullscreen-toggle-button-to-exoplayer-in-android/

    public void getDataFromMain(){


            Bundle bundle = getIntent().getExtras();
            Log.e("TestFinal", " rrrr=>" + bundle.getParcelable("data"));
            itemFilm = bundle.getParcelable("data");

    }

    public void initialize(){
        imageView=findViewById(R.id.image_content);
        namefilm=findViewById(R.id.name_film);
        actors=findViewById(R.id.actors_film);
        country=findViewById(R.id.country_film);
        information=findViewById(R.id.film_info);

        information.setText(itemFilm.getInformation());
        namefilm.setText(itemFilm.getMovieName());
        actors.setText(itemFilm.getActors());
        country.setText(itemFilm.getCountry());

        Glide.with(this)
                .asBitmap()
                .load(itemFilm.getUrlImage())
                .into(imageView);

    }

    public void initializePlayer(){
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectorFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            trackSelector = new DefaultTrackSelector(videoTrackSelectorFactory);


            //Initializare player
        player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);



        //Initializare simpleExoplayerView
        simpleExoPlayerView = findViewById(R.id.exoplayer);
        simpleExoPlayerView.setPlayer(player);


        String streamUrl = itemFilm.getUrlMp4();
        String userAgent = Util.getUserAgent(PlayerActivity.this, getApplicationContext().getApplicationInfo().packageName);
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, null, DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);

        DataSource.Factory dataSourceFactory=new DefaultDataSourceFactory(this,null, httpDataSourceFactory);
        Uri dataUri= Uri.parse(streamUrl);

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        mediaSource = new ExtractorMediaSource(dataUri, dataSourceFactory, extractorsFactory, null, null);


        player.prepare(mediaSource);

//        initFullscreenButton();
        initFullscreenDialog();
        openFullscreenDialog();
        simpleExoPlayerView.getPlayer().setPlayWhenReady(false);


    }


        public void initFullscreenDialog(){
            mFullScreenDialog= new Dialog(this, R.style.AppFullScreenTheme){
                @Override
                public void onBackPressed(){
                    if (mExoPlayerFullscreen)
                        closeFullscreenDialog();
                    super.onBackPressed();
                }
            };
        }


        private void openFullscreenDialog(){

            ((ViewGroup) simpleExoPlayerView.getParent()).removeView(simpleExoPlayerView);
            mFullScreenDialog.addContentView(simpleExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mFullScreenIcon=findViewById(R.id.exo_fullscreen_icon);
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_fullscreen_skrink));
            mExoPlayerFullscreen=true;
            mFullScreenDialog.show();

        }

        private void closeFullscreenDialog(){
            ((ViewGroup) simpleExoPlayerView.getParent()).removeView(simpleExoPlayerView);
            ((FrameLayout) findViewById(R.id.main_media_frame)).addView(simpleExoPlayerView);
            mExoPlayerFullscreen=false;
            mFullScreenDialog.dismiss();
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fullscreen_expand));
        }


        private void initFullscreenButton(){
        PlaybackControlView controlView= simpleExoPlayerView.findViewById(R.id.exo_controller_view);
        mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });

        }



}


//https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android