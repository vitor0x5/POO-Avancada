package siteParsers;

import models.news.News;
import models.newsSelector.NewsSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Classe abstrata das classes de noticia que é a base para as criacoes das demais

public abstract class BaseSiteParser {
    public final String url;
    private Document doc;                       //Recebe o HTML da pagina
    private List<NewsSelector> newsSelectors;   //Lista de seletores da pagina

    //Construtor
    public BaseSiteParser(String url) {
        this.newsSelectors = new ArrayList<>();
        this.url = url;
    }

    //Responsavel por obter o html da pagina
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

    //Estes metodos precisaram ser implementados a cada nova classe que for extender essa
    public abstract List<News> getAllNews();
    public abstract List<News> getNewsFromOneType(String type);

}
