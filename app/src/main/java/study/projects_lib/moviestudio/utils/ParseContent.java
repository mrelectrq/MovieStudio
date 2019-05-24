package study.projects_lib.moviestudio.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import study.projects_lib.moviestudio.callbacks.ItemFilmResponse;
import study.projects_lib.moviestudio.model.ItemFilm;

public class ParseContent {

private ItemFilm data;
private int position;

private ItemFilmResponse itemFilmResponse;

public ParseContent (ItemFilmResponse itemFilmResponse){
    this.itemFilmResponse = itemFilmResponse;
}

public void getDataWeb(String contentURL, int position){
    new ParserContentTask().execute(contentURL);
    this.position=position;
}

public class ParserContentTask extends AsyncTask<String, Void, Void>{



    @Override
    protected Void doInBackground(String... strings) {
        getDataContent(strings[0]);

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

        itemFilmResponse.itemContentResponse(data,position);

    }

    private String getDataContent(String url){

        try {
            data=new ItemFilm();

            Document doc1 = Jsoup.connect(url).get();
            Log.e("TestParsing3", "url Film==>" + url);

            // Aditional info select

            //Actors select
            String actors = doc1.select("li.actors").text();


            //Country select
            String country = doc1.select("li.common-list").get(2).text();


            //Details_text select
            String details =doc1.select("div.moview-details-text").text();


            //Mp4 select

            Element element4 = doc1.select("div.trailer").first();
            Element links1 = element4.select("iframe").first();
            String src1 = links1.absUrl("src");



            Document doc2 = Jsoup.connect(src1)
                    .userAgent("Mozila/5.0")
                    .get();


            Elements link = doc2.getElementsByTag("script");
            for (Element scriptEl : link) {
                String html = scriptEl.html();
                Pattern p = Pattern.compile("720p](.*?),");

                Matcher m = p.matcher(html);
                if (m.find()) {
                    String linkFilm = m.group(1);

                    data.setUrlMp4(linkFilm);
                    return linkFilm;
                }




                Log.e("ParserURLContent", "url Film s==>" + data.getUrlMp4());
                Log.e("ParserURLContent", "========>"+ actors);
                Log.e("ParserURLContent", "========>"+ country);
                Log.e("ParserURLContent", "========>"+ details);

                data.setActors(actors);
                data.setCountry(country);
                data.setInformation(details);
            }


        } catch (Exception e) {

        }
        return "";

    }

}


}



//https://cloudinary.com/blog/exoplayer_android_tutorial_easy_video_delivery_and_editing
