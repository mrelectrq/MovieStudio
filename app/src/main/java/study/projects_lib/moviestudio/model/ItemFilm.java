package study.projects_lib.moviestudio.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class ItemFilm  {


    private String movieName;

    private String urlImage;

    private String urlFilm;

    private String urlMp4;

    private String actors;

    private String country;

    private String information;




    public String getUrlFilm() {
        return urlFilm;
    }

    public void setUrlFilm(String urlFilm) {
        this.urlFilm = urlFilm;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlMp4(){
        return urlMp4;
    }

    public void setUrlMp4(String urlMp4){
        this.urlMp4=urlMp4;
    }




}

//https://stackoverflow.com/questions/22188332/download-ts-files-from-video-stream

