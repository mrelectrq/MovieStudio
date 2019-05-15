package study.projects_lib.moviestudio;

import android.media.Image;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import study.projects_lib.moviestudio.Utils.IAsyncResponse;
import study.projects_lib.moviestudio.Utils.Parsing;
import study.projects_lib.moviestudio.Utils.Service;

public class MainActivity extends AppCompatActivity implements IAsyncResponse {


    private static final String TAG = "MainActivity";
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

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

       // RecyclerViewAdapter adapter = new RecyclerViewAdapter()
    }


    @Override
    public void processFinish(List<Parsing> output) {
        adapter.setList(output);
    }
}
