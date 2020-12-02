package algorithms.CSV;

import algorithms.CSV.CSVWriter.CSVWriter;
import models.news.News;

import java.io.IOException;
import java.util.List;

public class NewsToCSV {

    // create a CSV file based on a news List
    public static void createCSV(String fileName, List<News> news) throws IOException {
        CSVWriter writer = new CSVWriter(fileName);

        for(News n: news) {
            String[] line = {n.getType(), n.getTitle(), n.getLink()};
            writer.addLine(line);
        }
    }
}
