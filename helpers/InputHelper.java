package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputHelper {
    private Path path;

    public InputHelper(Path path) {
        this.path = path;
    }

    public List<String> readLines() {
        try {
            return Files.readAllLines(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
