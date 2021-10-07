import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws IOException {

        ListOfCompanies.addingCompaniesToList();
        MainWindow window = new MainWindow();
        window.createMainWindow(window);
        window.addToFromList();
        window.addToToList();

    }

}
