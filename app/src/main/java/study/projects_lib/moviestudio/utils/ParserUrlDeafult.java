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

public class ParserUrlDeafult {

//
//    private ItemFilmResponse itemFilmResponse;
//    private int position;
//
//
//    public ParserUrlDeafult(ItemFilmResponse itemFilmResponse) {
//        this.itemFilmResponse = itemFilmResponse;
//    }
//
//    public void giveUrlFilm(String content, int position) {
//        this.position = position;
//        new ParserData().execute(content);
//    }
//
//
//    private class ParserData extends AsyncTask<String, Void, String> {
//
//
//        @Override
//        protected String doInBackground(String... voids) {
//            String contentURL = voids[0];
//
//            return getUrlVideo(contentURL);
//
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Log.e("TestParsing55", "dssafdas==>" + s);
//            itemFilmResponse.itemUrlResponse(s, position);
//        }
//
//
//        private String getUrlVideo(String url) {
//            try {
//                Log.e("TestParsing3", "url Film==>" + url);
//                //Mp4 select
//                Document doc1 = Jsoup.connect(url).get();
//                Element element4 = doc1.select("div.trailer").first();
//                Element links1 = element4.select("iframe").first();
//                String src1 = links1.absUrl("src");
//                Log.e("TestParsing3", "url Film==>" + src1);
//
//
//                Document doc2 = Jsoup.connect(src1)
//                        .userAgent("Mozila/5.0")
//                        .get();
//
//
//                Elements link = doc2.getElementsByTag("script");
//                for (Element scriptEl : link) {
//                    String html = scriptEl.html();
//                    Pattern p = Pattern.compile("720p](.*?),");
//
//                    Matcher m = p.matcher(html);
//                    if (m.find()) {
//                        String linkFilm = m.group(1);
//                        Log.e("TestParsing5", "url Film s==>" + linkFilm);
//                        return linkFilm;
//                    }
//
//
//                }
//
//
//                // Information select
//
//
//
//            } catch (Exception e) {
//
//            }
//            return "";
//        }
//    }
}


