package algorithms.CSV;

import algorithms.CSV.CSVWriter.CSVWriter;
import models.news.News;

import java.io.IOException;
import java.util.List;
//Recebe uma lista de notícias e cria um arquivo csv correspondente
public class NewsToCSV {

    public static void createCSV(String fileName, List<News> news) throws IOException {
        CSVWriter writer = new CSVWriter(fileName);

        for(News n: news) { //Separa a notícia em 3 colunas
            String[] line = {n.getType(), n.getTitle(), n.getLink()};
            writer.addLine(line);
        }
    }
}
