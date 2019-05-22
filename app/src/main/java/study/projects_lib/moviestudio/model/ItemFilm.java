package study.projects_lib.moviestudio.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ItemFilm  implements Parcelable {


    private String movieName;

    private String urlImage;

    private String urlFilm;

    private String urlMp4;

    private String actors;

    private String country;

    private String information;


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(urlImage);
        dest.writeString(urlMp4);
        dest.writeString(urlFilm);
        dest.writeString(actors);
        dest.writeString(country);
        dest.writeString(information);
    }


    public ItemFilm(String movieName, String urlImage, String urlFilm, String actors, String country
            , String information, String urlMp4){

        this.movieName = movieName;
        this.urlImage = urlImage;
        this.urlFilm = urlFilm;
        this.actors=actors;
        this.country=country;
        this.information=information;
        this.urlMp4=urlMp4;

    }

    public ItemFilm (Parcel in){
        movieName= in.readString();
        urlImage= in.readString();
        urlMp4=in.readString();
        urlFilm=in.readString();
        actors=in.readString();
        country=in.readString();
        information=in.readString();

    }

    public static final  Parcelable.Creator<ItemFilm> CREATOR = new Parcelable.Creator<ItemFilm>(){


        @Override
        public ItemFilm createFromParcel(Parcel source) {
            return new ItemFilm(source);
        }

        @Override
        public ItemFilm[] newArray(int size) {
            return new ItemFilm[size];
        }
    };



}

//https://stackoverflow.com/questions/22188332/download-ts-files-from-video-stream
//https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android
