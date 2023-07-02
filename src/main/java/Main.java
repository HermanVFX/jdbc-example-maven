import repo.Repository;
import repo.RepositoryImpl;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Repository repository = new RepositoryImpl();

        try(FileWriter writer = new FileWriter("result.txt", false))
        {
            String text = repository.getContent();
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
