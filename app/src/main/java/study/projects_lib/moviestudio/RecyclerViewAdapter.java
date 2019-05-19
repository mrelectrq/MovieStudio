package study.projects_lib.moviestudio;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import study.projects_lib.moviestudio.Utils.Parsing;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{



    private static final String TAG = "RecyclerViewAdapter";


    private List<Parsing> content;
    private Context mContext;

    public RecyclerViewAdapter (Context mContext, List<Parsing> list) {

        this.content = list;
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
                .load(content.get(i).getUrlImage())
                .into(viewHolder.image);

        viewHolder.name.setText(content.get(i).getMovieName());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked"+ content.get(i).getMovieName());
                Toast.makeText(mContext, content.get(i).getMovieName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext ,PlayerActivity.class);


                intent.putExtra("image_name", content.get(i).getMovieName());
                intent.putExtra("mp4_url", content.get(i).getUrlMp4());
                mContext.startActivity(intent);

            }
        });


    }

    public void setList(List<Parsing> list){
        this.content=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return content.size();
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
