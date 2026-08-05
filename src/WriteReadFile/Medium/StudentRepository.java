package WriteReadFile.Medium;

import java.io.*;

public class StudentRepository {
    public void save(Student s , String path) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(s);
        }
        

    }

    
    public Student load(String path) throws IOException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
             return  (Student) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
