package algorithms.CSV.CSVWriter;

import utils.writter.CustomFileWriter;
import java.io.IOException;

public class CSVWriter implements ICSVWriter {
    private CustomFileWriter fileWriter;

    public CSVWriter(String fileName) throws IOException {
        this.fileWriter = new CustomFileWriter(fileName + ".csv");
    }

    @Override
    public void addLine(String[] values) {
        for (String value: values) {
            this.fileWriter.print(value);
            // Se não for o último elemento
            if(!value.equals(values[values.length -1])) {
                this.fileWriter.print(";");
            }
        }
        this.fileWriter.print("\n");
    }
}
