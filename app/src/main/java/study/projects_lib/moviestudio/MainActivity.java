package study.projects_lib.moviestudio;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import study.projects_lib.moviestudio.Utils.IAsyncResponse;
import study.projects_lib.moviestudio.Utils.Parsing;
import study.projects_lib.moviestudio.Utils.Service;

public class MainActivity extends AppCompatActivity implements IAsyncResponse {


    private static final String TAG = "MainActivity";
    private RecyclerViewAdapter adapter;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        initRecyclerView();

    }



    private void initRecyclerView(){

        List<Parsing> content;
        Service service = new Service(this);
        service.getDataWeb();
        content=service.list;
//        Log.e("TestParsing", "nameFilm==>" + content);
        RecyclerView.LayoutManager layoutManager;
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
         adapter=new RecyclerViewAdapter(this ,content);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Log.d(TAG,"initRecyclerView: initialization.");

    }


    @Override
    public void processFinish(List<Parsing> output) {
        adapter.setList(output);
    }
}
