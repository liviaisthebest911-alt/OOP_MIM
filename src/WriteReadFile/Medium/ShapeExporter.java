package WriteReadFile.Medium;

import Part9.Library.Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShapeExporter {
    public void writeToCVS(List<Shape> shapes) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("liu.txt"))){
            for (Shape s : shapes){
                if (s instanceof Circle c){
                    bw.write("Circle: "+c.getRadius());
                }else if (s instanceof Rectangle r){
                    bw.write("Rectangle: "+r.getChieuDai()+", "+r.getChieuRong());
                }else {
                    bw.write("UNKNOWN");
                }
            }
            bw.newLine();

        }

    }
}
