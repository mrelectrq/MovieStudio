package study.projects_lib.moviestudio;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import study.projects_lib.moviestudio.Utils.Parsing;
import study.projects_lib.moviestudio.Utils.Service;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialization recyclerView

    }





    private void initRecyclerView(){

        List<Parsing> content;
        Service service = new Service();
        service.setData();
        content = service.list;

        RecyclerView.LayoutManager layoutManager;
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this ,content);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.d(TAG,"initRecyclerView: initialization.");

       // RecyclerViewAdapter adapter = new RecyclerViewAdapter()
    }


}
