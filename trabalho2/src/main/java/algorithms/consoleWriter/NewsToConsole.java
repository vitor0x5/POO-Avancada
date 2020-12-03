package algorithms.consoleWriter;

import algorithms.CSV.CSVWriter.CSVWriter;
import models.news.News;

import java.io.IOException;
import java.util.List;

public class NewsToConsole{

    public static void writeToConsole(List<News> news){

        System.out.println("Tipo;Título;Link");
        for (News n : news) {
            System.out.println(n.getType() + ";" + n.getTitle() + ";" + n.getLink());
        }
    }
}