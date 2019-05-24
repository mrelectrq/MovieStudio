package study.projects_lib.moviestudio.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
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

import study.projects_lib.moviestudio.R;
import study.projects_lib.moviestudio.callbacks.ItemClickResponse;
import study.projects_lib.moviestudio.model.ItemFilm;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    private int selected_position = RecyclerView.NO_POSITION;

    private List<ItemFilm> content;
    private Context mContext;
    private ItemClickResponse itemClick;

    public RecyclerViewAdapter(Context mContext, List<ItemFilm> list, ItemClickResponse itemClick) {
        this.content = list;
        this.mContext = mContext;
        this.itemClick = itemClick;
    }

    public void addItemFilm(ItemFilm itemFilm) {
        Log.e("TestItwem","iten name film ===>"+itemFilm.getMovieName());
        content.add(itemFilm);
        notifyItemInserted(content.size() - 1);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(content.get(position).getUrlImage())
                .into(viewHolder.image);
        viewHolder.itemView.setSelected(selected_position==position);
        viewHolder.name.setText(content.get(position).getMovieName());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked" + content.get(position).getMovieName());
                Toast.makeText(mContext, content.get(position).getMovieName(), Toast.LENGTH_SHORT).show();
                itemClick.itemClicked(position);
                notifyItemChanged(selected_position);
                selected_position=getItemCount();
                notifyItemChanged(selected_position);
            }


        });

    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


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
