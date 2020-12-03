package siteParsers.globoParser;

import models.news.News;
import models.newsSelector.NewsSelector;
import siteParsers.BaseSiteParser;

import java.util.ArrayList;
import java.util.List;

//Parser do site da Globo

public class GloboParser extends BaseSiteParser {

    private final GloboFindNewsStrategy findNews;

    public GloboParser() {
        super("https://www.globo.com");

        this.findNews = new GloboFindNewsStrategy();

        // Setando seletores do site
        this.setNewsSelector("p.hui-premium__title", "Principal");
        this.setNewsSelector("p.hui-highlight-title", "Secundário");
    }

    //Obtem todas noticias do site
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
