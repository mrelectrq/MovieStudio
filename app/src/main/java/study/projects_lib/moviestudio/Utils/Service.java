package study.projects_lib.moviestudio.Utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import study.projects_lib.moviestudio.MainActivity;


//https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
//https://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui


public class Service implements IAsyncResponse{
    Task asynkTask = new Task();


    public Parsing data = new Parsing();
    public List<Parsing> list = new ArrayList<>();

    @Override
    public void processFinish(List<Parsing> output) {
        asynkTask.onPostExecute(list);
    }

//    public void setData()
//
//    {
//        asynkTask.delegate = this;
//        asynkTask.execute(list);
//
//    }




    public class Task extends AsyncTask<List<Parsing>, Void, List<Parsing>>{

        public IAsyncResponse delegate = null;



        @Override
        protected List<Parsing> doInBackground(List<Parsing>... arrayLists) {

            try {
                Document doc = Jsoup.connect("http://hdkino.vip/filmy/filmy_2019_novinki_hd").get();
                Element name = doc.select("div[class=post-film-slider-name]").first();
                String mName = name.text();
                Log.e("TestParsing","nameFilm==>"+mName);
                data.setMovieName(mName);
                String urlimage = "http://hdkino.vip/_bd/19/85430885.jpg";
                data.setUrlImage(urlimage);
                list.add(data);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }




        @Override
        protected void onPostExecute(List<Parsing> result) {
            delegate.processFinish(result);
        }
    }




}

