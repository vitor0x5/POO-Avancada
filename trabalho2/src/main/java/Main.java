import algorithms.CSV.NewsToCSV;
import algorithms.consoleWriter.NewsToConsole;
import models.news.News;
import algorithms.CSV.CSVWriter.CSVWriter;
import siteParsers.globoParser.GloboParser;
import siteParsers.uolParser.UolParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-ss").format(LocalDateTime.now());
        String fileName = String.format("dump-%s", strNow);

        //Obtendo informacoes referentes de um determinado site
        UolParser siteParser = new UolParser();                 //O site que as noticias serao capturadas eh o da UOL
        if (siteParser.connect()) {                             //Obtem o html da pagina
            List<News> news = siteParser.getAllNews();

            NewsToCSV.createCSV(fileName, news);
            NewsToConsole.writeToConsole(news);
        }else{
            System.out.println("Falha na conexão com o site");
        }
    }
}
