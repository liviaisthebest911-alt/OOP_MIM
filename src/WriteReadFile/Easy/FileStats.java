package WriteReadFile.Easy;

import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileStats {
    public int countNonEmpty(String path) throws FileNotFoundException , IOException {
        int count =0;
        try (BufferedReader br  = new BufferedReader(new FileReader(path))){
            String line;

            while ((line= br.readLine()) != null){

                if(!line.trim().isEmpty()) count++;

            }
        }
        return count;


    }
}
