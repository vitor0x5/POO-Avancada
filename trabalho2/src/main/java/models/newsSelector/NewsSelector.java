package models.newsSelector;

public class NewsSelector {
    private final String selector;    //Seletor do CSS da pagina de noticia
    private final String newsType;    //Podemos ter noticias primarias, secundarias....

    //Construtor
    public NewsSelector(String selector, String newsType) {
        this.selector = selector;
        this.newsType = newsType;
    }

    public String getselector() {
        return selector;
    }

    public String getNewsType() {
        return newsType;
    }
}
