package study.projects_lib.moviestudio.Utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

            getDataNew();

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iAsyncResponse.processFinish(list);
        }
    }


    private ArrayList<Parsing> getDataNew() {

        try {

            //http://moviebuster.tv/film
            Document doc = Jsoup.connect("http://moviebuster.tv/film/fdrama").get();
            int i = 0;

            Elements elements = doc.select("div.movie-poster");

            for (Element el : elements) {
                data = new Parsing();
                Log.e("TestParsing7", "====================================================================================================================================");

                // Href select
                Element aa = el.select("a.play-icon").first();
                Elements links = aa.select("a[href]");
                String content = links.attr("abs:href");


                // Image select
                Element element2 = el.select("div.movie-poster img").first();
                String src = element2.absUrl("src");


                //Name select
                Elements element3 = doc.select("div.movie-name");
                Element element33 = element3.select("a").get(i++);
                String name = element33.select("a").text();


                //Mp4 select
                Document doc1 = Jsoup.connect(content).get();
                Element element4 = doc1.select("div.trailer").first();
//                Log.e("TestParsing3", "url Film==>" + element4);
                Element links1 = element4.select("iframe").first();
                String src1 = links1.absUrl("src");
                Log.e("TestParsing3", "url Film==>" + src1);


                Document doc2 = Jsoup.connect(src1)
                        .userAgent("Mozila/5.0")
                        .get();


                Elements link = doc2.getElementsByTag("script");


                String mp4;
                for (Element scriptEl: link){
                    String html = scriptEl.html();
                    Pattern p = Pattern.compile("720p](.*?),");

                    Matcher m = p.matcher(html);
                    if (m.find()){
                        String link1 = m.group(1);
//                        if (link1==null){
//                            link1="error";
//                        }
                        Log.e("TestParsing5", "url Film s==>" + link1);
                        mp4=link1;
                        Log.e("TestParsing7", "Mp4 URL    ==>" + mp4);
                        data.setUrlMp4(link1);
                        break;
                    }
                }
                Log.e("TestParsing7", "Silca la MP4!!==>" + src1);
                Log.e("TestParsing7", "url Content==>" + content);
                Log.e("TestParsing7", "Image      ==>" + src);
                Log.e("TestParsing7", "Name film  ==>" + name);


                data.setUrlImage(src);
                data.setMovieName(name);
                data.setUrlFilm(content);
                list.add(data);
            }
            Log.e("TestParsing4", "nameFilm==>" + list.size());


        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }
}



// https://stackoverflow.com/questions/35246897/unable-to-fetch-the-video-source-link-from-this-website-using-jsoup
// https://stackoverflow.com/questions/16780517/java-obtain-text-within-script-tag-using-jsoup