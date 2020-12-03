package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.nodes.Document;

import java.util.List;

//Cada site devera implementar essa classe
public interface IFindNewsStrategy {
    List<News> run(NewsSelector selector, Document doc);
}
