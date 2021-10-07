import java.util.ArrayList;

public class ServiceTagParsing {
    static ArrayList<String> listOfServiceTags = new ArrayList<>();

    public static ArrayList<String> trimStringToServiceTags() {
        if (!MainWindow.serviceTagText.getText().isEmpty()) {
            String allAsOne = MainWindow.serviceTagText.getText().replace("\n", "");
            StringBuilder oneTag = new StringBuilder();
            for (int i = 0; i < allAsOne.toCharArray().length; i++) {
                oneTag.append(allAsOne.toCharArray()[i]);
                if ((i + 1) % 7 == 0) {
                    listOfServiceTags.add(oneTag.toString());
                    oneTag = new StringBuilder();
                }
            }
        }
        return listOfServiceTags;
    }
}
