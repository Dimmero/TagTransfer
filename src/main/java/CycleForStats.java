import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CycleForStats {
    public static HyperlinkURL hyperlinkURL;
    public static Hyperlinks hyperlinks;

    public static void turnOffCookies(WebDriver driver) {
        try {
            driver.findElement(By.cssSelector("#top > div.cc-window.cc-floating.cc-type-opt-in.cc-theme-block.cc-bottom.cc-left.cc-color-override--1898810230 > div.cc-compliance.cc-highlight.cc-regular > a.cc-btn.cc-allow")).click();
            System.out.println("accepted");
        } catch (Exception o) {
            System.out.println("no stupid cookies, go further");
        }
    }

    public static void getACycle(int i, FileOutputStream src, HSSFSheet sheet1, HSSFWorkbook wb){
        String modelName;
        WebElement warranty;
        turnOffCookies(SeleniumDriver.driver);
        SeleniumDriver.wait = new WebDriverWait(SeleniumDriver.driver, 15);
        String data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
        SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.id("inpEntrySelection")));
        SeleniumDriver.driver.findElement(By.id("inpEntrySelection")).sendKeys(data0);
        SeleniumDriver.driver.findElement(By.id("inpEntrySelection")).sendKeys(Keys.ENTER);
        try {
            SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ps-inlineWarranty")));
            warranty = SeleniumDriver.driver.findElement(By.id("ps-inlineWarranty"));
            if (warranty.getText().contains("Informacje niedostepne")) {

                sheet1.getRow(i).createCell(1).setCellValue("Info is not available");
            } else {
                String css = "#warranty-card > div > div > div > div > div.ml-0.ml-lg-auto.flex-shrink-0.text-center.p-4.pr-6.my-auto > div > a";
                String cssWarranty = "#warranty-card > div > div > div > div > div.w-100.text-lg-left.text-center > div > div > p";
                String cssModel = "html body#top div#site-wrapper.site-wrapper.supportpage-initialized div.site-canvas.site-canvas-mob.mh-sim-canvas div.site-canvas-mob.min-height-body div div.product-support-hero.pt-3.pt-lg-4.tab-overview-bg-gray div.container.mb-4.mb-lg-8 div.row div.col-12 div.card.flex-row.flex-nowrap.justify-content-center.align-items-center.p-5.p-lg-6.prod-card div.product-summary.d-flex.align-items-center div.product-info.d-flex.flex-column.align-items-center.d-lg-block h1.h2.mb-0.mb-lg-1.text-center.text-lg-left.position-relative.word-break.pt-lg-0.pt-4";
                String cssCloseWarrantyWindow = "html body#top.modal-open div#site-wrapper.site-wrapper.supportpage-initialized div.site-canvas.mh-sim-canvas.site-canvas-mob div.site-canvas-mob.min-height-body div div#warrantydetailsmodalContainer div#warrantyDetailsPopup.modal.modal-fullscreen-xs-down.show div.modal-dialog.modal-lg.full_modal-dialog div.modal-content div.modal-header button#warranty-cancel.close span svg.dti.dti-lg";
                //Model name output

                modelName = SeleniumDriver.driver.findElement(By.cssSelector(cssModel)).getText();
                sheet1.getRow(i).createCell(1).setCellValue(modelName);
                //Country output

                SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css))).click();
                WebElement warrantyButton = SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("countryLabel")));
                sheet1.getRow(i).createCell(2).setCellValue(warrantyButton.getText());
                SeleniumDriver.driver.findElement(By.cssSelector(cssCloseWarrantyWindow)).click();
                //Warranty expires output

                WebElement warrantyInfo = SeleniumDriver.driver.findElement(By.cssSelector(cssWarranty));
                sheet1.getRow(i).createCell(3).setCellValue(warrantyInfo.getText());

                //Download serviceTag.csv
                String cssInfoProcessor = "html body#top div#site-wrapper.site-wrapper.supportpage-initialized div.site-canvas.site-canvas-mob.mh-sim-canvas div.site-canvas-mob.min-height-body div div.ips-tab-main-container.tab-overview-bg-gray div.tab-content div#tab_overview.tab-pane.active div.tab-overview-bg-gray div#overview-tag.container.ov-container.pt-10.pt-md-12.border-0 div.row div#quickLinkContainer.col-lg-3.col-xs-12 div.col-md-12.card.col-xs-12.mb-7.px-6 div#overview-quicklink-1.text-lg-left.text-center.pb-3 a#quicklink-sysconfig";
                WebElement configButton = SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssInfoProcessor)));
                configButton.click();
                WebElement download = SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("current-config-export")));
                download.click();

//                Hyperlinks
                hyperlinks = new Hyperlinks();
                hyperlinks.addLink(wb, sheet1, i, data0, src);
//                Reading data from file
                Thread.sleep(300);
                File fileCSV = new File("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\csvFiles\\" + data0 + ".csv");
                try {
                    BufferedReader csvReader = new BufferedReader(new FileReader(fileCSV));
                    String row;
                    while ((row = csvReader.readLine()) != null) {
                        Pattern pattern = Pattern.compile("(i\\d-.+?)[, ]", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(row);
                        boolean found = matcher.find();
                        if (found) {
                            sheet1.getRow(i).createCell(5).setCellValue(matcher.group(1));
                        }
                    }
                    csvReader.close();
                } catch (Exception e) {
                    System.out.println("Fuck");
                }
                String currentURL = SeleniumDriver.driver.getCurrentUrl();
                hyperlinkURL = new HyperlinkURL();
                hyperlinkURL.addLinkURL(wb, sheet1, i, data0, currentURL);
            }
        } catch (Exception e) {
            System.out.println("WOW");
        }
    }


}
