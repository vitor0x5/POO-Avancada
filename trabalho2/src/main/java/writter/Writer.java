package writter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer implements IWriter {
    private final PrintWriter pw;

    public Writer(String fileName) throws IOException {
        this.pw = new PrintWriter(new FileWriter(new File(fileName)));
    }

    @Override
    public void print(String text) {
        pw.print(text);
    }

    @Override
    public void println(String text) {
        pw.println(text);
    }
}
