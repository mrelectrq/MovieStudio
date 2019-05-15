package study.projects_lib.moviestudio.Utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


//https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
//https://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui


public class Service {
    public Parsing data;
    public ArrayList<Parsing> list = new ArrayList<Parsing>();


    private IAsyncResponse iAsyncResponse;

    public Service(IAsyncResponse iAsyncResponse) {
        this.iAsyncResponse = iAsyncResponse;
    }

    public void getDataWeb() {
        new ParserData().execute();
    }

    private class ParserData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

//            list = getData();
            getDataNew();

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iAsyncResponse.processFinish(list);
        }
    }


    private ArrayList<Parsing> getData() {

        try {

            Document doc = Jsoup.connect("http://hdkino.vip/filmy/filmy_2019_novinki_hd").get();


            Elements img = doc.select("div.post-film-poster img");
            for (Element el : img) {
                String src = el.absUrl("src");
                String name = el.attr("alt");

                data = new Parsing();
                data.setUrlImage(src);
                data.setMovieName(name);

                Log.e("TestParsing", "nameFilm==>" + src);
                Log.e("TestParsing", "nameFilm==>" + name);

                list.add(data);

                Log.e("TestParsing", "nameFilm==>" + list.size());
            }

            Elements urls = doc.select("div.post-film");
//            Log.e("TestParsing", "nameFilm==>" + urls);
            for (Element el : urls) {
                Elements links = el.select("a[href]");
                String content = links.attr("abs:href");
                Log.e("TestParsing", "nameFilm==>" + content);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    private ArrayList<Parsing> getDataNew() {

        try {

            Document doc = Jsoup.connect("http://hdkino.vip/filmy/filmy_2019_novinki_hd").get();


            Elements elements = doc.select("div.post-film");
            Log.e("TestParsing1", "url==>" + elements);
            for (Element el : elements) {
                Elements aa= el.select("div.post-film");
                Elements links = aa.select("a[href]");
                String content = links.attr("abs:href");



                Element element2 = el.select("div.post-film-poster img").first();
                String src = element2.absUrl("src");
                String name = element2.attr("alt");
                Log.e("TestParsing3", "name Film==>" + name);
                Log.e("TestParsing3", "url image Film==>" + src);
                Log.e("TestParsing3", "url Film==>" + content);
                Log.e("TestParsing3", "==============================================");

            }
            Log.e("TestParsing4", "nameFilm==>" + list.size());
//            for (Element el : img) {
//                String src = el.absUrl("src");
//                String name = el.attr("alt");
//
//                data = new Parsing();
//                data.setUrlImage(src);
//                data.setMovieName(name);
//
//                Log.e("TestParsing", "nameFilm==>" + src);
//                Log.e("TestParsing", "nameFilm==>" + name);
//
//                list.add(data);
//
//                Log.e("TestParsing", "nameFilm==>" + list.size());
//            }
//
//            Elements urls = doc.select("div.post-film");
////            Log.e("TestParsing", "nameFilm==>" + urls);
//            for (Element el : urls) {
//                Elements links = el.select("a[href]");
//                String content = links.attr("abs:href");
//                Log.e("TestParsing", "nameFilm==>" + content);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }
}

