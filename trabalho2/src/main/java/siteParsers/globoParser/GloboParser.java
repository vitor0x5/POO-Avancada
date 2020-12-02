package siteParsers.globoParser;

import models.news.News;
import models.newsSelector.NewsSelector;
import siteParsers.BaseSiteParser;

import java.util.ArrayList;
import java.util.List;

public class GloboParser extends BaseSiteParser {
    private final GloboFindNewsStrategy findNews;

    public GloboParser() {
        super("https://www.globo.com");

        this.findNews = new GloboFindNewsStrategy();

        // Setting the site's selectors
        this.setNewsSelector("p.hui-premium__title", "Principal");
        this.setNewsSelector("p.hui-highlight-title", "Secundário");
    }

    // Get all news from this site
    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();

        for (NewsSelector s: this.getNewsSelectors()) {
            news.addAll(this.findNews.run(s, super.getDoc()));
        }

        return news;
    }

    // Verify if the type exists for this site and get all the news from it
    public List<News> getNewsFromOneType(String type) {
        NewsSelector selector = null;
        for (NewsSelector s: this.getNewsSelectors()) {
            if (s.getNewsType().equals(type)) {
                selector = s;
            }
        }

        if(selector == null) {
            return null;
        }

        return findNews.run(selector, super.getDoc());
    }

}
