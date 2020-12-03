package siteParsers.uolParser;

import models.news.News;
import models.newsSelector.NewsSelector;
import siteParsers.BaseSiteParser;
import java.util.ArrayList;
import java.util.List;

public class UolParser extends BaseSiteParser {

    private final UolFindNewsStrategy findNews;

    public UolParser() {
        super("https://www.uol.com.br");

        this.findNews = new UolFindNewsStrategy();

        // Setando seletores do site
        this.setNewsSelector("h1.titulo", "Principal");
        this.setNewsSelector("h2.titulo", "Secundário");
    }

    // Get all news from this site
    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();

        //Percorrendo todos seletores do site da Globo
        for (NewsSelector s: this.getNewsSelectors()) {
            //Obtendo todas noticia do seletor s e adicionando a lista
            news.addAll(this.findNews.run(s, super.getDoc()));
        }
        return news;
    }

    //Metodo para obter noticias de um mesmo tipo
    public List<News> getNewsFromOneType(String type) {

        NewsSelector selector = null;

        //Percorrendo os seletores do site
        for (NewsSelector s: this.getNewsSelectors()) {
            //Verifica se o tipo do titulo encontrado bate com aquele passado como parametro
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
