package study.projects_lib.moviestudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import study.projects_lib.moviestudio.callbacks.IAsyncResponse;
import study.projects_lib.moviestudio.callbacks.ItemClickResponse;
import study.projects_lib.moviestudio.callbacks.ItemFilmResponse;
import study.projects_lib.moviestudio.model.ItemFilm;
import study.projects_lib.moviestudio.utils.ParserUrlDeafult;
import study.projects_lib.moviestudio.utils.ServiceSite;
import study.projects_lib.moviestudio.adapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements IAsyncResponse, ItemClickResponse, ItemFilmResponse, NavigationView.OnNavigationItemSelectedListener {


    private static final String CONTENT_ACTION_FIGHT = "http://moviebuster.tv/film/fboevik";
    private static final String CONTENT_VESTERN = "http://moviebuster.tv/film/fvestern";
    private static final String CONTENT_DETECTIVE = "http://moviebuster.tv/film/fdetective";
    private static final String CONTENT_CARTOON = "http://moviebuster.tv/cartoon";
    private static final String CONTENT_WAR = "http://moviebuster.tv/film/fwar";
    private static final String CONTENT_DRAMA = "http://moviebuster.tv/film/fdrama";
    private static final String CONTENT_HISTORY = "http://moviebuster.tv/film/fhistorical";
    private static final String CONTENT_COMEDY = "http://moviebuster.tv/film/fcomedy";
    private static final String CONTENT_CRIMINAL = "http://moviebuster.tv/film/fcrime";
    private static final String CONTENT_MELODRAMA = "http://moviebuster.tv/film/fmelodrama";
    private static final String CONTENT_HORROR = "http://moviebuster.tv/film/fhorrors";
    private static final String CONTENT_FANTASTIC = "http://moviebuster.tv/film/ffantastic";
    private static final String TAG = "MainActivity";
    private RecyclerViewAdapter adapter;
    private DrawerLayout drawer;
    private List<ItemFilm> contentListFilm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            initRecyclerView("http://moviebuster.tv/film");
        }

    }


    private void initRecyclerView(String url) {

        ServiceSite service = new ServiceSite(this);
        service.getDataWeb(url);
        contentListFilm = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager;
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        adapter = new RecyclerViewAdapter(this, contentListFilm, this);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Log.d(TAG, "initRecyclerView: initialization.");

    }


    @Override
    public void processFinish(List<ItemFilm> output) {
        adapter.setList(output);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.action_fight:
                initRecyclerView(CONTENT_ACTION_FIGHT);
                drawer.closeDrawers();
                break;

            case R.id.action_vestern:
                initRecyclerView(CONTENT_VESTERN);
                drawer.closeDrawers();
                break;

            case R.id.action_war:
                initRecyclerView(CONTENT_WAR);
                drawer.closeDrawers();
                break;

            case R.id.action_detective:
                initRecyclerView(CONTENT_DETECTIVE);
                drawer.closeDrawers();
                break;
            case R.id.action_drama:
                initRecyclerView(CONTENT_DRAMA);
                drawer.closeDrawers();
                break;

            case R.id.action_cartoon:
                initRecyclerView(CONTENT_CARTOON);
                drawer.closeDrawers();
                break;

            case R.id.action_history:
                initRecyclerView(CONTENT_HISTORY);
                drawer.closeDrawers();
                break;

            case R.id.action_comedy:
                initRecyclerView(CONTENT_COMEDY);
                drawer.closeDrawers();
                break;
            case R.id.action_criminal:
                initRecyclerView(CONTENT_CRIMINAL);
                drawer.closeDrawers();
                break;
            case R.id.action_melodrama:
                initRecyclerView(CONTENT_MELODRAMA);
                drawer.closeDrawers();
                break;
            case R.id.action_horror:
                initRecyclerView(CONTENT_HORROR);
                drawer.closeDrawers();
                break;
            case R.id.action_fantastic:
                initRecyclerView(CONTENT_FANTASTIC);
                drawer.closeDrawers();
                break;
        }
        return true;
    }

    @Override
    public void itemClicked(int position) {


        if(contentListFilm.get(position).getUrlMp4()==null) {
            Log.e("TestScccccc","sscccccc==>"+contentListFilm.get(position).getUrlMp4());

            ParserUrlDeafult parserUrlDeafult = new ParserUrlDeafult(this);
            parserUrlDeafult.giveUrlFilm(contentListFilm.get(position).getUrlFilm(), position);

        }else{
            openAnotherScreen(position);
        }
    }

    @Override
    public void itemContentFilm(ItemFilm parsingFilm) {
        Log.e("TestItwem2", "iten name film ===>" + parsingFilm.getMovieName());
        adapter.addItemFilm(parsingFilm);
    }

    @Override
    public void itemUrlResponse(String urlFilm, int position) {

        ItemFilm itemFilm = new ItemFilm(contentListFilm.get(position).getMovieName(),
                contentListFilm.get(position).getUrlImage(),
                contentListFilm.get(position).getUrlFilm(),
                contentListFilm.get(position).getActors(),
                contentListFilm.get(position).getCountry(),
                contentListFilm.get(position).getInformation(),
                urlFilm);




//        itemFilm.setMovieName(contentListFilm.get(position).getMovieName());
//        itemFilm.setUrlFilm(contentListFilm.get(position).getUrlFilm());
//        itemFilm.setUrlImage(contentListFilm.get(position).getUrlImage());
//        itemFilm.setCountry(contentListFilm.get(position).getCountry());
//        itemFilm.setInformation(contentListFilm.get(position).getInformation());
//        itemFilm.setActors(contentListFilm.get(position).getActors());
//        itemFilm.setUrlMp4(urlFilm);

        contentListFilm.set(position, itemFilm);



        Log.e("TestRssssss", " rrrr=>" + urlFilm);

        if (urlFilm.length() > 0) {
            openAnotherScreen(position);
        }
    }
    public void openAnotherScreen(int position){
        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        Bundle bundle=new Bundle();

        Log.e("TestFinal", " rrrr=>" + contentListFilm.get(position).getUrlMp4());
        bundle.putParcelable("data",contentListFilm.get(position));
        intent.putExtras(intent);



//        intent.putExtra("mp4_url",contentListFilm.get(position).getUrlMp4());
//        intent.putExtra("movie_name", contentListFilm.get(position).getMovieName());
//        intent.putExtra("actors_name",contentListFilm.get(position).getActors());
//        intent.putExtra("aditional_info", contentListFilm.get(position).getInformation());



        startActivity(intent);
    }

}
