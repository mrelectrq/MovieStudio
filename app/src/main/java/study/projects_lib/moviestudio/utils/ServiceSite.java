package study.projects_lib.moviestudio.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import study.projects_lib.moviestudio.callbacks.ItemFilmResponse;
import study.projects_lib.moviestudio.model.ItemFilm;


//lifecycle Fragment
//lifecycle Activity android

public class ServiceSite {
    private ItemFilm data;
    private ItemFilmResponse itemFilm;


    public ServiceSite(ItemFilmResponse itemFilm) {
        this.itemFilm = itemFilm;
    }

    public void getDataWeb(String contentURL) {
        new ParserData().execute(contentURL);
    }


    private class ParserData extends AsyncTask<String, ItemFilm, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            String contentURL = voids[0];

            getDataNew(contentURL);
            return null;


        }

        @Override
        protected void onProgressUpdate(ItemFilm... values) {
            super.onProgressUpdate(values);
            Log.e("TestService2", "name Film==>" + values[0]);
            itemFilm.itemContentFilm(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        private void getDataNew(String contentURL) {

            try {

                //http://moviebuster.tv/film
                Document doc = Jsoup.connect(contentURL).get();
                int i = 0;

                Elements elements = doc.select("div.movie-poster");

                for (Element el : elements) {




                    // Href select
                    Element aa = el.select("a.play-icon").first();
                    Elements links = aa.select("a[href]");
                    String urlFilm = links.attr("abs:href");

                    Log.e("ParserURLContent", "========>"+ urlFilm);


                    // Aditional info select
                    Document doc1 = Jsoup.connect(urlFilm).get();

                    //Actors select
                    String actors = doc1.select("li.actors").text();
                    Log.e("ParserURLContent", "========>"+ actors);

                    //Country select
                    String country = doc1.select("li.common-list").get(2).text();
                    Log.e("ParserURLContent", "========>"+ country);

                    //Details_text select
                    String details =doc1.select("div.moview-details-text").text();
                    Log.e("ParserURLContent", "========>"+ details);



                    // Image select
                    Element image = el.select("div.movie-poster img").first();
                    String urlImage = image.absUrl("src");


                    //Name select
                    Elements element3 = doc.select("div.movie-name");
                    Element element33 = element3.select("a").get(i++);
                    String name = element33.select("a").text();

                    data=new ItemFilm(name,urlImage,urlFilm,actors,country,details,null);

//                    data.setCountry(country);
//                    data.setActors(actors);
//                    data.setInformation(details);
//                    data.setUrlImage(urlImage);
//                    data.setMovieName(name);
//                    data.setUrlFilm(urlFilm);

                    publishProgress(data);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


}


// https://stackoverflow.com/questions/35246897/unable-to-fetch-the-video-source-link-from-this-website-using-jsoup
// https://stackoverflow.com/questions/16780517/java-obtain-text-within-script-tag-using-jsoup