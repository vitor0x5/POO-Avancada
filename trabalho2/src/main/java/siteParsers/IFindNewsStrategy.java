package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.nodes.Document;

import java.util.List;

public interface IFindNewsStrategy {
    List<News> run(NewsSelector selector, Document doc);
}
