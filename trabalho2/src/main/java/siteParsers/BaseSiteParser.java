package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class BaseSiteParser {
    public final String url;
    public List<News> news;
    private Document doc;

    public BaseSiteParser(String url) {
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

    public abstract List<News> getAllNews();
    public abstract List<News> getNewsFromOneType(String type);
}
