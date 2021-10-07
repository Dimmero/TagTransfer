import java.util.ArrayList;
import java.util.List;

public class ListOfCompanies {
    private Company company;
    static List<Company> companyList;

    static final Company laptokcom = new Company("Laptokcom", "dima@laptok.com", "Krakowska 56", "PL", "Wieliczka", "Lesser Poland", "32-020",  "601913957");
    static final Company bufotech = new Company("Bufotech", "bufotech@gmail.com", "", "UK", "", "", "FY83AY",  "601913957");
    static  final Company ecomputers = new Company("Ecomputers", "dima@laptok.com", "", "UK", "", "" ,"Ne166DZ",  "601913957");
    static final Company maxmart = new Company("MaxMart", "dima@laptok.com", "118 Edwardia Dr.", "US", "Greensboro", "", "27409",  "601913957");
    static final Company deane = new Company("Deane Computer Solutions Limited", "dima@laptok.com", "Unit 8F Middle Farm Way", "UK", "Dorchester", "", "DT13AR",  "601913957");

    static void addingCompaniesToList(){
        companyList = new ArrayList<>();
        companyList.add(laptokcom);
        companyList.add(bufotech);
        companyList.add(ecomputers);
        companyList.add(maxmart);
        companyList.add(deane);
    }

    public ListOfCompanies() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

}
