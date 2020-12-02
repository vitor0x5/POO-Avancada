package models.newsSelector;

public class NewsSelector {
    private final String selector;    //The css id of the news type
    private final String newsType;

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
