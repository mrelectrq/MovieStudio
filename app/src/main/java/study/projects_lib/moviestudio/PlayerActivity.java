package study.projects_lib.moviestudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import study.projects_lib.moviestudio.adapter.SectionStageStateAdapter;
import study.projects_lib.moviestudio.callbacks.ItemTransferData;
import study.projects_lib.moviestudio.model.ItemFilm;

public class PlayerActivity extends AppCompatActivity {


    private SectionStageStateAdapter mSectionsStageStateAdapter;
    private ViewPager viewPager;
    private ItemFilm itemFilm ;
    private ItemTransferData itemTransferData;


    public PlayerActivity(){}
    public PlayerActivity (ItemTransferData itemTransferData){
        this.itemTransferData = itemTransferData;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        itemFilm=new ItemFilm();
        getDataFromMain();
        mSectionsStageStateAdapter = new SectionStageStateAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);


        setupViewPager(viewPager);



    }

    private void setupViewPager(ViewPager viewPager){
        SectionStageStateAdapter adapter = new SectionStageStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentContent());
        adapter.addFragment(new FragmentExo());
        viewPager.setAdapter(adapter);

    }


    public void  getData(){
        itemTransferData.itemTransfer(itemFilm);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    public void getDataFromMain(){

            Bundle bundle = getIntent().getExtras();
            Log.e("TestFinal", " rrrr=>" + bundle.getParcelable("data"));
            itemFilm = bundle.getParcelable("data");
    }

    public void setViewPager (int fragmentNumber){
        this.viewPager.setCurrentItem(fragmentNumber);
    }



}


//https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android