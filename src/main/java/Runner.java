import java.io.IOException;

public class Runner {
    public static void main(String[] args)  {
        ListOfCompanies.addingCompaniesToList();
        MainWindow window = new MainWindow();
        window.createMainWindow(window);
        window.addToFromList();
        window.addToToList();
    }

}
