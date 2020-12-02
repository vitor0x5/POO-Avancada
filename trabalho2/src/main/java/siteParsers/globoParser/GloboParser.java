package siteParsers.globoParser;

import models.news.News;
import siteParsers.BaseSiteParser;

import java.util.ArrayList;
import java.util.List;

public class GloboParser extends BaseSiteParser {
    private List<GloboNewsSelector> newsSelectors;
    private final GloboFindNewsStrategy findNews;

    public GloboParser() {
        super("https://www.globo.com");

        this.newsSelectors = new ArrayList<>();
        this.findNews = new GloboFindNewsStrategy();

        // Setting the site's selectors
        GloboNewsSelector principalSelector =
                new GloboNewsSelector("p.hui-premium__title", "Principal");

        GloboNewsSelector secundarioSelector =
                new GloboNewsSelector("p.hui-highlight-title", "Secundário");

        this.newsSelectors.add(principalSelector);
        this.newsSelectors.add(secundarioSelector);
    }

    // Get all news from this site
    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();

        for (GloboNewsSelector s: newsSelectors) {
            news.addAll(this.findNews.run(s, super.getDoc()));
        }

        return news;
    }

    // Verify if the type exists for this site and get all the news from it
    public List<News> getNewsFromOneType(String type) {
        GloboNewsSelector selector = null;
        for (GloboNewsSelector s: this.newsSelectors) {
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
