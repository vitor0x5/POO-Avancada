package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

//Cada site devera implementar essa classe

public class BaseFindNewsStrategy  {

    // Obtem todas noticias do site
    public List<News> run(NewsSelector selector, Document doc) {
        List<News> news = new ArrayList<>();

        Elements titles = doc.select(selector.getselector());
        for (Element t: titles) {
            //System.out.println(t);

            String link = "";

            //Obtendo o pai da classe do titulo que eh do tipo link (a)
            Element parent = t.parent();
            while (parent != null && !parent.tagName().equals("a")) {
                parent = parent.parent();
            }

            if(parent != null && parent.tagName().equals("a")) {
                link = String.format("\"%s\"", parent.attr("href"));
            }

            News n = new News(selector.getNewsType(), t.text(), link);
            news.add(n);
        }

        //System.out.println(news);
        return news;
    }
}