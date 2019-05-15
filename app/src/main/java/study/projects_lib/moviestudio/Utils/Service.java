package study.projects_lib.moviestudio.Utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import study.projects_lib.moviestudio.MainActivity;


//https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
//https://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui


public class Service {
    public Parsing data = new Parsing();
    public List<Parsing> list= new ArrayList<Parsing>() ;


    private IAsyncResponse iAsyncResponse;

    public Service(IAsyncResponse iAsyncResponse) {
        this.iAsyncResponse = iAsyncResponse;
    }

    public void getDataWeb(){
        new ParserData().execute();
    }

    private class ParserData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            list = getData();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iAsyncResponse.processFinish(list);
        }
    }






    private List<Parsing> getData() {

        try {

            Document doc = Jsoup.connect("http://hdkino.vip/filmy/filmy_2019_novinki_hd").get();
            Element name = doc.select("div[class=post-film-slider-name]").first();

            String mName = name.text();
//            Log.e("TestParsing", "nameFilm==>" + mName);
//            data.setMovieName(mName);
//            String urlimage = "http://hdkino.vip/_bd/19/85430885.jpg";
//            data.setUrlImage(urlimage);
//            list.add(data);
//



            Elements img = doc.select("div.post-film-poster img");
            for (Element el : img){
                String src = el.absUrl("src");
                String alt = el.absUrl("alt");
                Log.e("TestParsing", "nameFilm==>" + alt);
//                Log.e("TestParsing", "nameFilm==>" + src);
//                data.setUrlImage(src);
//                String alt = el.absUrl("alt");

            }
//            Log.e("TestParsing", "nameFilm==>" + img);



        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }
}

