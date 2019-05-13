package study.projects_lib.moviestudio.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {

    Parsing data = new Parsing();
    public List<Parsing> list = new ArrayList<>();

    public void setData()

    {


        try {
            Document doc = Jsoup.connect("http://hdkino.vip/filmy/filmy_2019_novinki_hd").get();
            Element name = doc.select("div[class=post-film-slider-name]").first();
            String mName = name.text();


            data.setMovieName(mName);
            String urlimage = "http://hdkino.vip/_bd/19/85430885.jpg";
            data.setUrlImage(urlimage);
            list.add(data);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

