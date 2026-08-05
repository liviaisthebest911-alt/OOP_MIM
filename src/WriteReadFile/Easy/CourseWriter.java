package WriteReadFile.Easy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CourseWriter {
    public void writeToFile(List<String> courses, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String course : courses) {
                bw.write(course);
                bw.newLine();
            }
        }
    }
}