import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CycleForTransfer {
    public static void turnOffCookies(WebDriver driver) {

        try {
            SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#top > div.cc-window.cc-floating.cc-type-opt-in.cc-theme-block.cc-bottom.cc-left.cc-color-override--1898810230 > div.cc-compliance.cc-highlight.cc-regular > a.cc-btn.cc-allow")));
            driver.findElement(By.cssSelector("#top > div.cc-window.cc-floating.cc-type-opt-in.cc-theme-block.cc-bottom.cc-left.cc-color-override--1898810230 > div.cc-compliance.cc-highlight.cc-regular > a.cc-btn.cc-allow")).click();
            System.out.println("accepted");
        } catch (Exception o) {
            System.out.println("no stupid cookies, go further");
        }
    }

    public static void getCycle(String serviceTag, Company fromCompany, Company toCompany, ArrayList<String> listOfServiceTags){
        try {
            turnOffCookies(SeleniumDriver.driver);

            SeleniumDriver.driver.findElement(By.id("OTST_txtSTag1")).click();
            SeleniumDriver.driver.findElement(By.id("OTST_txtSTag1")).sendKeys(serviceTag);

            SeleniumDriver.driver.findElement(By.id("OTST_txtSTag1")).sendKeys(Keys.ENTER);
            SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCompanyName")));
            SeleniumDriver.driver.findElement(By.id("txtCompanyName")).click();

            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtCompanyName")).sendKeys(fromCompany.getName());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtZipcode")).sendKeys(fromCompany.getZipCode());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("retailOT_spanContinueButton")).click();

            SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ddlIntendedUse")));
            Thread.sleep(200);
            Select usage = new Select(SeleniumDriver.driver.findElement(By.id("ddlIntendedUse")));
            usage.selectByValue("2_Office");
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerCompanyName")).sendKeys(toCompany.getName());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerEmail")).sendKeys(toCompany.getEmail());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerStreetAddress1")).sendKeys(toCompany.getAddress());
            Thread.sleep(200);
            usage = new Select(SeleniumDriver.driver.findElement(By.id("ddlNewOwnerLocation")));
            usage.selectByValue(toCompany.getCountry());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerCity")).sendKeys(toCompany.getCity());
            Thread.sleep(200);
            usage = new Select(SeleniumDriver.driver.findElement(By.id("ddlNewOwnerState")));
            usage.selectByValue(toCompany.getState());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerZipCode")).sendKeys(toCompany.getZipCode());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerAreaCode")).sendKeys(toCompany.getPrefixNumber());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("txtNewOwnerPhone")).sendKeys(toCompany.getTelNumber());
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("retailOT_spanContinueButton")).click();
            SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.id("btnNewOwnerModelContinue")));
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("btnNewOwnerModelContinue")).click();
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.cssSelector("div.custom-control:nth-child(3) > label:nth-child(2)")).click();
            Thread.sleep(200);
            SeleniumDriver.driver.findElement(By.id("retailOT_spanSubmitButton")).click();
            SeleniumDriver.wait.until(ExpectedConditions.urlContains("https://www.dell.com/support/assets-transfer/pl-pl/confirmation"));
            Thread.sleep(200);
            WebElement idNumber = SeleniumDriver.driver.findElement(By.id("refNumber"));
            idNumber.getText();
            File file = new File(System.getProperty("user.dir") + "\\confirmation\\" + serviceTag + ".txt");
            try {
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(serviceTag + " " + idNumber);
                    fileWriter.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            MainWindow.success.setText(listOfServiceTags + " have been successfully transferred to Laptokcom");
        } catch (Exception l) {
            SeleniumDriver.driver.close();
            SeleniumDriver.driver.quit();
        }
    }
    }

