import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainWindow extends BaseWindow {
    static JTextArea serviceTagText;
    private JButton button;
    private JButton generateFile;
    private JButton generateStats;
    static JLabel success;
    private ButtonGroup fromButtonGroup;
    private ButtonGroup toButtonGroup;
    private JRadioButton radioButtonLaptok1;
    private JRadioButton radioButtonLaptok2;
    private JRadioButton radioButtonBufo1;
    private JRadioButton radioButtonBufo2;
    private JRadioButton radioButtonEco1;
    private JRadioButton radioButtonEco2;
    private JRadioButton radioButtonMax1;
    private JRadioButton radioButtonMax2;
    private JRadioButton radioButtonDeane1;
    private JRadioButton radioButtonDeane2;
    static JRadioButton radioButtonAddFromCompany;
    static JRadioButton radioButtonAddToCompany;

    private ArrayList<JRadioButton> fromList;
    private ArrayList<JRadioButton> toList;

    public static OutputToExcel output;

    public MainWindow() {
        super();
        this.fromList = new ArrayList<>();
        this.toList = new ArrayList<>();
    }

    public void createMainWindow(MainWindow mainWindow) {
        mainWindow.getPanelForWindow();

        JLabel serviceTagLabel = new JLabel("Provide service tags for transferring:");
        serviceTagLabel.setForeground(Color.BLACK);
        serviceTagLabel.setBounds(10, 20, 220, 25);

        serviceTagText = new JTextArea();
        serviceTagText.setBounds(240, 20, 100, 285);

        JLabel previousOwner = new JLabel("Previous owner");
        previousOwner.setForeground(Color.BLACK);
        previousOwner.setBounds(15, 70, 100, 25);

        JLabel newOwner = new JLabel("New owner");
        newOwner.setForeground(Color.BLACK);
        newOwner.setBounds(140, 70, 100, 25);

        mainWindow.getPanel().add(serviceTagLabel);
        mainWindow.getPanel().add(serviceTagText);
        mainWindow.getPanel().add(previousOwner);
        mainWindow.getPanel().add(newOwner);
//        Left column

        radioButtonLaptok1 = new JRadioButton("Laptokcom");
        radioButtonLaptok1.setBounds(10, 100, 100, 25);

        radioButtonBufo1 = new JRadioButton("Bufotech");
        radioButtonBufo1.setBounds(10, 130, 100, 25);

        radioButtonEco1 = new JRadioButton("Ecomputers");
        radioButtonEco1.setBounds(10, 160, 100, 25);

        radioButtonMax1 = new JRadioButton("MaxMart");
        radioButtonMax1.setBounds(10, 190, 100, 25);

        radioButtonDeane1 = new JRadioButton("Deane Computer Solutions Limited");
        radioButtonDeane1.setBounds(10, 220, 100, 25);
//        Right column
        radioButtonLaptok2 = new JRadioButton("Laptokcom");
        radioButtonLaptok2.setBounds(120, 100, 100, 25);

        radioButtonBufo2 = new JRadioButton("Bufotech");
        radioButtonBufo2.setBounds(120, 130, 100, 25);

        radioButtonEco2 = new JRadioButton("Ecomputers");
        radioButtonEco2.setBounds(120, 160, 100, 25);

        radioButtonMax2 = new JRadioButton("MaxMart");
        radioButtonMax2.setBounds(120, 190, 100, 25);

        radioButtonDeane2 = new JRadioButton("Deane Computer Solutions Limited");
        radioButtonDeane2.setBounds(120, 220, 100, 25);

        radioButtonAddFromCompany = new JRadioButton("Add new");
        radioButtonAddFromCompany.setBounds(10, 250, 100, 25);

        radioButtonAddToCompany = new JRadioButton("Add new");
        radioButtonAddToCompany.setBounds(120, 250, 100, 25);

        button = new JButton("Submit");
        button.setBounds(10, 280, 210, 25);

        success = new JLabel("Result:");
        success.setForeground(Color.BLACK);
        success.setBounds(10, 310, 450, 25);

        generateFile = new JButton("Generate");
        generateFile.setBounds(350, 20, 100, 25);

        generateStats = new JButton("Statistics");
        generateStats.setBounds(350, 50, 100, 25);

        fromButtonGroup = new ButtonGroup();
        fromButtonGroup.add(radioButtonLaptok1);
        fromButtonGroup.add(radioButtonBufo1);
        fromButtonGroup.add(radioButtonEco1);
        fromButtonGroup.add(radioButtonMax1);
        fromButtonGroup.add(radioButtonDeane1);
        fromButtonGroup.add(radioButtonAddFromCompany);
        toButtonGroup = new ButtonGroup();
        toButtonGroup.add(radioButtonLaptok2);
        toButtonGroup.add(radioButtonBufo2);
        toButtonGroup.add(radioButtonEco2);
        toButtonGroup.add(radioButtonMax2);
        toButtonGroup.add(radioButtonDeane2);
        toButtonGroup.add(radioButtonAddToCompany);

        mainWindow.getPanel().add(radioButtonLaptok1);
        mainWindow.getPanel().add(radioButtonBufo1);
        mainWindow.getPanel().add(radioButtonEco1);
        mainWindow.getPanel().add(radioButtonMax1);
        mainWindow.getPanel().add(radioButtonDeane1);
        mainWindow.getPanel().add(radioButtonLaptok2);
        mainWindow.getPanel().add(radioButtonBufo2);
        mainWindow.getPanel().add(radioButtonEco2);
        mainWindow.getPanel().add(radioButtonMax2);
        mainWindow.getPanel().add(radioButtonDeane2);
        mainWindow.getPanel().add(radioButtonAddFromCompany);
        mainWindow.getPanel().add(radioButtonAddToCompany);
        mainWindow.getPanel().add(button);
        mainWindow.getPanel().add(success);
        mainWindow.getPanel().add(generateFile);
        mainWindow.getPanel().add(generateStats);
        mainWindow.getFrame().setVisible(true);

        generateFile.addActionListener(e -> {
            ServiceTagParsing.trimStringToServiceTags();
            try {
                OutputToExcel outputToExcel = new OutputToExcel();
                output = OutputToExcel.outputToFile(outputToExcel);
                generateFile.setText("Done");
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        generateStats.addActionListener(e -> {
            try {
                OutputToExcel.outputToFileStats(output);
                output.getWb1().write(output.getFileOut());
                generateStats.setText("Done");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        radioButtonAddToCompany.addActionListener(e -> {
            NewCompanyWindow newCompanyWindow = new NewCompanyWindow();
            newCompanyWindow.createNewCompanyWindow(newCompanyWindow, radioButtonAddFromCompany);
        });

        radioButtonAddFromCompany.addActionListener(e -> {
            NewCompanyWindow newCompanyWindow = new NewCompanyWindow();
            newCompanyWindow.createNewCompanyWindow(newCompanyWindow, radioButtonAddToCompany);
        });

        button.addActionListener(e -> {
            Map<JRadioButton, Company> fromMap = getMapOfButtonsAndCompanies(fromList, ListOfCompanies.companyList);
            Map<JRadioButton, Company> toMap = getMapOfButtonsAndCompanies(toList, ListOfCompanies.companyList);
            Company fromCompany = getCheckedCompany(fromMap);
            Company toCompany = getCheckedCompany(toMap);
            ArrayList<String> listOfServiceTags = ServiceTagParsing.trimStringToServiceTags();

            for (String serviceTag : listOfServiceTags) {
                SeleniumDriver.initDriver("https://www.dell.com/support/assets-transfer/pl-pl");
                CycleForTransfer.getCycle(serviceTag, fromCompany, toCompany, listOfServiceTags);
                SeleniumDriver.closeDriver();
            }
        });
    }

    public void addToFromList() {
        fromList.add(radioButtonLaptok1);
        fromList.add(radioButtonBufo1);
        fromList.add(radioButtonEco1);
        fromList.add(radioButtonMax1);
        fromList.add(radioButtonDeane1);
    }

    public void addToToList() {
        toList.add(radioButtonLaptok2);
        toList.add(radioButtonBufo2);
        toList.add(radioButtonEco2);
        toList.add(radioButtonMax2);
        toList.add(radioButtonDeane2);
        toList.add(radioButtonAddFromCompany);
    }

    public static Map<JRadioButton, Company> getMapOfButtonsAndCompanies(List<JRadioButton> list, List<Company> companyList) {
        Map<JRadioButton, Company> map = new LinkedHashMap<>();
        for (int i = 0; i < companyList.size(); i++) {
            map.put(list.get(i), companyList.get(i));
        }
        return map;
    }

    public Company getCheckedCompany(Map<JRadioButton, Company> map) {
        Company checkedCompany = new Company();
        Set<JRadioButton> set = map.keySet();
        for (JRadioButton button : set) {
            if (button.isSelected()) {
                checkedCompany = map.get(button);
            }
        }
        return checkedCompany;
    }


}
