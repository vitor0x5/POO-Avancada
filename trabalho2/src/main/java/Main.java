import algorithms.CSV.NewsToCSV;
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

        GloboParser globoParser = new GloboParser();
        globoParser.connect();
        List<News> globoNews =  globoParser.getAllNews();

        NewsToCSV.createCSV(fileName, globoNews);
    }
}
