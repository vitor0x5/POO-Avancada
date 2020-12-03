import algorithms.CSV.NewsToCSV;
import algorithms.consoleWriter.NewsToConsole;
import models.news.News;
import algorithms.CSV.CSVWriter.CSVWriter;
import siteParsers.globoParser.GloboParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-ss").format(LocalDateTime.now());
        String fileName = String.format("dump-%s", strNow);

        //Obtendo informacoes referentes de um determinado site
        GloboParser siteParser = new GloboParser();            //O site que as noticias serao capturadas eh o da globo
        if (siteParser.connect()) {                             //Obtem o html da pagina
            List<News> globoNews = siteParser.getAllNews();

            NewsToCSV.createCSV(fileName, globoNews);
            NewsToConsole.writeToConsole(globoNews);
        }else{
            System.out.println("Falha na conexão com o site");
        }
    }
}
