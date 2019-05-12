package study.projects_lib.moviestudio;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{



    private static final String TAG = "RecyclerViewAdapter";


    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mImage, ArrayList<String> mNames, Context mContext) {
        this.mImage = mImage;
        this.mNames = mNames;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(i))
                .into(viewHolder.image);

        viewHolder.name.setText(mNames.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked"+ mNames.get(i));
                Toast.makeText(mContext, mNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView name;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.imageinfo);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }


    }
}
