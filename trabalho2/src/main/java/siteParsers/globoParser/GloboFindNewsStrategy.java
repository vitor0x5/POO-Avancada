package siteParsers.globoParser;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import siteParsers.IFindNewsStrategy;

import java.util.ArrayList;
import java.util.List;

//Implementacao da estrategia para buscar noticias no site da Globo
public class GloboFindNewsStrategy implements IFindNewsStrategy {
    // Get all news from a specific selector
    public List<News> run(NewsSelector selector, Document doc) {
        List<News> news = new ArrayList<>();

        Elements titles = doc.select(selector.getselector());
        for (Element t: titles) {
            String link = "";

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

        return news;
    }
}
