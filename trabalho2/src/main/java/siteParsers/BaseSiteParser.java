package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSiteParser {
    public final String url;
    private Document doc;
    private List<NewsSelector> newsSelectors;

    public BaseSiteParser(String url) {
        this.newsSelectors = new ArrayList<>();
        this.url = url;
    }

    public boolean connect() {
        try {
            this.doc = Jsoup.connect(this.url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Document getDoc() {
        return doc;
    }

    public void setNewsSelector(String selector, String type) {
        this.newsSelectors.add(new NewsSelector(selector, type));
    }

    public List<NewsSelector> getNewsSelectors() {
        return this.newsSelectors;
    }

    public abstract List<News> getAllNews();
    public abstract List<News> getNewsFromOneType(String type);
}
