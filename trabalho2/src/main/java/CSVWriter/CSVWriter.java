package CSVWriter;

import writter.Writer;

import java.io.IOException;
import java.util.List;

public class CSVWriter implements ICSVWriter {
    private Writer writer;

    public CSVWriter(String fileName) throws IOException {
        this.writer = new Writer(fileName + ".csv");
    }

    @Override
    public void addLine(String[] values) {
        for (String value: values) {
            this.writer.print(value);
            // Se não for o último elemento
            if(!value.equals(values[values.length -1])) {
                this.writer.print(";");
            }
        }
        this.writer.print("\n");
    }
}
