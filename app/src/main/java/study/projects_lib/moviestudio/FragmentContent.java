package study.projects_lib.moviestudio;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import study.projects_lib.moviestudio.callbacks.ItemTransferData;
import study.projects_lib.moviestudio.model.ItemFilm;

public class FragmentContent extends Fragment implements ItemTransferData {


    private PlayerActivity playerActivity = new PlayerActivity(this);
    private ItemFilm itemFilm;
    private ImageView imageView;
    private TextView namefilm;
    private TextView actors;
    private TextView country;
    private TextView information;
    private ImageView button_start;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container,false);
        imageView=view.findViewById(R.id.image_content);
        namefilm=view.findViewById(R.id.name_film);
        actors=view.findViewById(R.id.actors_film);
        country=view.findViewById(R.id.country_film);
        information=view.findViewById(R.id.film_info);

        button_start=view.findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 1", Toast.LENGTH_SHORT).show();

                ((PlayerActivity)getActivity()).setViewPager(1);
            }
        });
        playerActivity.getData();


        initialize();
        return view;
    }




    public void initialize(){


        information.setText(itemFilm.getInformation());
        namefilm.setText(itemFilm.getMovieName());
        actors.setText(itemFilm.getActors());
        country.setText(itemFilm.getCountry());

        Glide.with(this)
                .asBitmap()
                .load(itemFilm.getUrlImage())
                .into(imageView);

    }

    @Override
    public void itemTransfer(ItemFilm itemFilm) {
        this.itemFilm=itemFilm;
    }
}
