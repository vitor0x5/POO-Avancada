import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import writter.Writer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-ss").format(LocalDateTime.now());
        String fileName = String.format("dump-%s.csv", strNow);
        Writer writer = new Writer(fileName);

        writer.println("Tipo;Notícia;Link");
        Document doc = Jsoup.connect("https://www.globo.com").get();
        Elements titles = doc.select("p.hui-premium__title");
        for (Element t: titles) {
            writer.print(String.format("Principal;%s;", t.text()));
            Element parent = t.parent();
            while (parent != null && !parent.tagName().equals("a")) {
                parent = parent.parent();
            }
            if(parent != null && parent.tagName().equals("a")) {
                writer.print(String.format("\"%s\"", parent.attr("href")));
            }
            writer.print("\n");
        }

        titles = doc.select("p.hui-highlight-title");
        for (Element t: titles) {
            writer.print(String.format("Secundário;%s;", t.text()));
            Element parent = t.parent();
            while (parent != null && !parent.tagName().equals("a")) {
                parent = parent.parent();
            }
            if(parent != null && parent.tagName().equals("a")) {
                writer.print(String.format("\"%s\"", parent.attr("href")));
            }
            writer.print("\n");
        }

    }
}
