import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import CSVWriter.CSVWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-ss").format(LocalDateTime.now());
        String fileName = String.format("dump-%s", strNow);

        CSVWriter csvWriter = new CSVWriter(fileName);
        String[] header = {"Tipo", "Noticia", "Link"};
        csvWriter.addLine(header);

        Document doc = Jsoup.connect("https://www.globo.com").get();

        Elements titles = doc.select("p.hui-premium__title");
        for (Element t: titles) {
            String[] line = {"Principal", t.text(), ""};

            Element parent = t.parent();
            while (parent != null && !parent.tagName().equals("a")) {
                parent = parent.parent();
            }

            if(parent != null && parent.tagName().equals("a")) {
                line[2] = String.format("\"%s\"", parent.attr("href"));
                csvWriter.addLine(line);
            }
        }

        titles = doc.select("p.hui-highlight-title");
        for (Element t: titles) {
            String[] line = {"Secundário", t.text(), ""};

            Element parent = t.parent();
            while (parent != null && !parent.tagName().equals("a")) {
                parent = parent.parent();
            }

            if(parent != null && parent.tagName().equals("a")) {
                line[2] = String.format("\"%s\"", parent.attr("href"));
                csvWriter.addLine(line);
            }
        }

    }
}
