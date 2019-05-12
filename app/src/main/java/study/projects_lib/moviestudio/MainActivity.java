package study.projects_lib.moviestudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initimageBitmaps();
    }


    private void   initimageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: initialization.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
       // RecyclerViewAdapter adapter = new RecyclerViewAdapter()
    }


}
