package TradingBO;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import org.junit.Assert;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

/**
 * Created by p9137006 on 8/8/2019.
 */
public class BO_Steps {

    @Before
    public void setUp(){

    }

    @After
    public void closethedriver(){
        driver.close();
        driver.quit();

    }

    private int randomInt;

    public static String generateRandomName(int length) {
        char[] chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                .toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String randomString = sb.toString();
        return randomString;
    }




    public static WebDriver driver = null;

        String batchRefNumber;
        String TillMessageID;
        String line;
        int a = 0;
        int b;
        int c;
        String Delivery;
    boolean  Batchneeded=true;

        //String SaveS;


        Properties prop = new Properties();
        FileInputStream inp = new FileInputStream("C:\\Users\\Premkumar.surendiram\\Documents\\Center_SCOT_Automation - Backup\\config.properties");

    public BO_Steps() throws IOException {
        prop.load(inp);
    }
        private String RefundMessageID;
        private String PaymentMessageID;
        private String SalesMessageID;
        private String Age_RestrictionMessage;
        private String Promotionid;


        @Given("^the chrome driver is initiated$")
        public void the_chrome_driver_is_initiated () throws Throwable {
        System.setProperty("webdriver.chrome.driver", prop.getProperty("Chromedriver"));
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

        @Given("^I logged in Trading BackOffice$")
        public void i_logged_in_Trading_BackOffice () throws Throwable {
        driver.get(prop.getProperty("TradingBO"));
        driver.findElement(By.name("username")).sendKeys(prop.getProperty("UserName"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
        driver.findElement(By.id("logon_submit")).click();


    }

        @Given("^I choose HeadOffice$")
        public void i_choose_HeadOffice () throws Throwable {

        driver.findElement(By.id("form_search")).sendKeys("M001");
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();


    }

        @Given("^I choose Message option from maintanence menu$")
        public void i_choose_Message_option_from_maintanence_menu () throws Throwable {
        //Instantiate Action Class
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[8]")).click();

    }

        @Given("^I choose Print Receipt Restriction option from maintanence menu$")
        public void i_choose_Print_Receipt_Restriction_option_from_maintanence_menu () throws Throwable {
        //Instantiate Action Class
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[20]")).click();
    }
        @Given("^I enter Receipt Restriction value against SCOT$")
        public void i_enter_Receipt_Restriction_value_against_SCOT () throws Throwable {
            driver.findElement(By.id("SCOT")).sendKeys(prop.getProperty("ReceiptRestrictionValue"));
            Thread.sleep(3000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/ReceiptRestrictionValue.jpeg"));
            driver.findElement(By.id("nextButton")).click();
        }

        @Given("^I choose Remove Receipt Restriction Value against SCOT$")
        public void i_enter_Remove_Receipt_Restriction_value_against_SCOT () throws Throwable {
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[8]/td[5]/center/input")).click();
            Thread.sleep(2000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/RemoveReceiptRestrictionValue.jpeg"));
            driver.findElement(By.id("nextButton")).click();
        }

        @Given("^I disabled E Receipt against SCOT$")
        public void I_disabled_E_Receipt_against_SCOT () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[8]/td[8]/center/input")).click();
        Thread.sleep(2000);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/RemovingEReceipt.jpeg"));
        driver.findElement(By.id("nextButton")).click();
    }

        @Given("^I enabled E Receipt against SCOT$")
        public void I_enabled_E_Receipt_against_SCOT () throws Throwable {
            driver.findElement(By.xpath("//td[normalize-space()='SCOT']//following::td[5]")).click();
            Thread.sleep(3000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/EnablingEReceipt.jpeg"));
            driver.findElement(By.id("nextButton")).click();
        }

        @When("^I added the store details for Optional Receipt Prompt$")
        public void i_added_the_store_details_for_Optional_Receipt_Prompt () throws Throwable {
            driver.findElement(By.id("addButton")).click();
            driver.findElement(By.id("nextButton")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreIDOptedforOptRecPrompt"));
            driver.findElement(By.id("addButton")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();
            Thread.sleep(6000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/ReceiptRestrictionStore.jpeg"));
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();
        }

        @When("^I added the store details for E Receipt Prompt$")
        public void I_added_the_store_details_for_E_Receipt_Prompt () throws Throwable {
            driver.findElement(By.id("addButton")).click();
            driver.findElement(By.id("nextButton")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreIDOptedforERecPrompt"));
            driver.findElement(By.id("addButton")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();
            Thread.sleep(6000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/E-ReceiptEnabledStore.jpeg"));
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();
        }

        @Given("^I choose add option$")
        public void i_select_Add_new_message_option () throws Throwable {
        driver.findElement(By.id("addButton")).click();


    }

        @Given("^I select Red Alert from the message type dropdown list$")
        public void i_select_Red_Alert_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Red Alert");
        driver.findElement(By.id("form_department")).sendKeys("F20A");
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I fill the Red Alert message details$")
        public void i_fill_the_Red_Alert_message_details() throws Throwable {
        driver.findElement(By.name("messageName")).sendKeys(prop.getProperty("RedAlertMessage"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate1 = sdf.format(cal.getTime());
        driver.findElement(By.name("endDate")).sendKeys(enddate1);

        TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/SalesMessage.jpeg"));
            driver.findElement(By.name("nextButton")).click();
    }
        @Given("^I fill the Product restriction message details$")
        public void i_fill_the_Product_restriction_message_details() throws Throwable {
        driver.findElement(By.name("messageName")).sendKeys(prop.getProperty("ProductRestrictionMessage"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate1 = sdf.format(cal.getTime());
        driver.findElement(By.name("endDate")).sendKeys(enddate1);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/ProductRestriction.jpeg"));
        driver.findElement(By.name("nextButton")).click();
    }
        @Given("^I fill the Quantity restriction message details$")
        public void i_fill_the_Quantity_restriction_message_details() throws Throwable {
        driver.findElement(By.name("messageName")).sendKeys(prop.getProperty("QuantityRestrictionMessageName"));
        driver.findElement(By.id("form_tillRestrictionMessageLine1")).sendKeys(prop.getProperty("QuantityRestrictionMessage"));
        driver.findElement(By.name("restrictionValue")).sendKeys(prop.getProperty("QuantityRestrictedValue"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate1 = sdf.format(cal.getTime());
        driver.findElement(By.name("endDate")).sendKeys(enddate1);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/QuantityRestriction.jpeg"));
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I fill the Bulk RTM message details$")
        public void i_fill_the_Bulk_RTM_message_details() throws Throwable {
        driver.findElement(By.name("messageName")).sendKeys(prop.getProperty("BulkRTMMessage"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate1 = sdf.format(cal.getTime());
        driver.findElement(By.name("endDate")).sendKeys(enddate1);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/SalesMessage.jpeg"));
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I added the Product for the Red Alert Message$")
        public void i_added_the_Product_for_the_Red_Alert_Message() throws Throwable {
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("RedAlertMessageUPC"));
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(5000);
        TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/RedAlertProduct.jpeg"));
            driver.findElement(By.name("nextButton")).click();
            driver.findElement(By.name("nextButton")).click();
            driver.findElement(By.name("nextButton")).click();
        }

        @When("^I removed the store for reversion I save the changes$")
        public void I_removed_the_store_for_reversion_I_save_the_changes() throws Throwable {
            driver.findElement(By.linkText("Stores")).click();
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();

        }

        /*@When("^I removed the store for Bulk RTM I save the changes$")
        public void I_removed_the_store_for_Bulk_RTM_I_save_the_changes() throws Throwable {
        driver.findElement(By.linkText("Stores")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();

    }*/

        @Given("^I added the Product for the Bulk RTM$")
        public void i_added_the_Product_for_the_Bulk_RTM() throws Throwable {
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("BulkRTMMessageUPC"));
        String parentHandle = driver.getWindowHandle();
        System.out.println("parent window - "+parentHandle);
            Thread.sleep(2000);
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(20000);
        Set <String> handles=driver.getWindowHandles();
            for (String handle :
                 handles) {
                System.out.println(handle);
                if(!handle.equals(parentHandle)) {
                    driver.switchTo().window(handle);
                    WebElement firstElement = driver.findElement(By.name("selected"));
                    firstElement.click();
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File source = ts.getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/BulkRTMSuppliers.jpeg"));
                    Thread.sleep(2000);
                    driver.findElement(By.id("SubmitButton")).click();
// After you're done with the new window, you might want to switch back to the original window
                }
            }
            driver.switchTo().window(parentHandle);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/BulkRTM.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        //driver.findElement(By.name("nextButton")).click();
    }

    @Given("^I added the Product for the Product Restriction$")
    public void i_added_the_Product_for_the_Product_Restriction() throws Throwable {
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("ProductRestrictionUPC"));
        String parentHandle = driver.getWindowHandle();
        System.out.println("parent window - "+parentHandle);
        Thread.sleep(2000);
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(20000);
        Set <String> handles = driver.getWindowHandles();
        for (String handle :
             handles) {
            System.out.println(handle);
            if(!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                driver.findElement(By.name("selected")).click();
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/ProductRestrictionSuppliers.jpeg"));
                Thread.sleep(2000);
                driver.findElement(By.id("SubmitButton")).click();
            }

        }
// After you're done with the new window, you might want to switch back to the original window
        driver.switchTo().window(parentHandle);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/ProductRestrictionUPC.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        //driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I added the Product for the Quantity Restriction$")
        public void i_added_the_Product_for_the_Quantity_Restriction() throws Throwable {
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("QuantityRestrictionUPC"));
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(3000);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/QuantityRestrictionUPC.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I entered the required store$")
        public void i_entered_the_required_store () throws Throwable {
            driver.findElement(By.name("search")).sendKeys(prop.getProperty("requiredstoreID"));
            driver.findElement(By.name("searchButton")).click();
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        }

        @Given("^I choose Local Store in the Maintenance tab$")
        public void I_choose_Local_Store_in_the_Maintenance_tab () throws Throwable {
        Actions actions = new Actions(driver);
            //Retrieve WebElement 'Music' to perform mouse hover
            WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
            //Mouse hover menuOption 'Music'
            actions.moveToElement(MAINTENANCE).perform();
            driver.findElement(By.xpath("/html/body/div[5]/div[7]")).click();

        }

        @Given("^I choose Electronic Journal in the Enquiries tab$")
        public void I_choose_Electronic_Journal_in_the_Enquiries_tab () throws Throwable {
            Actions actions = new Actions(driver);
            //Retrieve WebElement 'Music' to perform mouse hover
            WebElement ENQUIRES = driver.findElement(By.id("divoCMenu0_1"));
            //Mouse hover menuOption 'Music'
            actions.moveToElement(ENQUIRES).perform();
            driver.findElement(By.xpath("/html/body/div[5]/div[1]")).click();
    }

        @When("^I entered the transaction number with date$")
        public void I_entered_the_transaction_number_with_date () throws Throwable {
            driver.findElement(By.id("form_dateFrom")).clear();
            driver.findElement(By.id("form_dateFrom")).sendKeys(prop.getProperty("Fromdate"));
            driver.findElement(By.id("form_dateTo")).clear();
            driver.findElement(By.id("form_dateTo")).sendKeys(prop.getProperty("Todate"));
            driver.findElement(By.id("form_txNumberFrom")).sendKeys(prop.getProperty("TransactionNumber"));
            driver.findElement(By.id("form_txNumberTo")).sendKeys(prop.getProperty("TransactionNumber"));
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            Thread.sleep(2000);
        }

        @Then("^Transaction details are captured$")
        public void Transaction_details_are_captured () throws Throwable {
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[5]")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/Transactiondetails.jpeg"));
            Thread.sleep(2000);
        }

        @Given("^I entered the trading hour details by daywise I save the changes$")
        public void I_entered_the_trading_hour_details_by_daywise_I_save_the_changes () throws Throwable {
        driver.findElement(By.id("form_sundayOpenTime")).clear();
        driver.findElement(By.id("form_sundayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_sundayCloseTime")).clear();
        driver.findElement(By.id("form_sundayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_mondayOpenTime")).clear();
        driver.findElement(By.id("form_mondayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_mondayCloseTime")).clear();
        driver.findElement(By.id("form_mondayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_tuesdayOpenTime")).clear();
        driver.findElement(By.id("form_tuesdayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_tuesdayCloseTime")).clear();
        driver.findElement(By.id("form_tuesdayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_wednesdayOpenTime")).clear();
        driver.findElement(By.id("form_wednesdayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_wednesdayCloseTime")).clear();
        driver.findElement(By.id("form_wednesdayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_thursdayOpenTime")).clear();
        driver.findElement(By.id("form_thursdayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_thursdayCloseTime")).clear();
        driver.findElement(By.id("form_thursdayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_fridayOpenTime")).clear();
        driver.findElement(By.id("form_fridayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_fridayCloseTime")).clear();
        driver.findElement(By.id("form_fridayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        driver.findElement(By.id("form_saturdayOpenTime")).clear();
        driver.findElement(By.id("form_saturdayOpenTime")).sendKeys(prop.getProperty("StartTime"));
        driver.findElement(By.id("form_saturdayCloseTime")).clear();
        driver.findElement(By.id("form_saturdayCloseTime")).sendKeys(prop.getProperty("EndTime"));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2161/StoreTimings.jpeg"));
        Thread.sleep(2000);

        driver.findElement(By.name("saveButton")).click();
        }

    @When("^I reverted the trading hour details by daywise I save the changes$")
    public void I_reverted_the_trading_hour_details_by_daywise_I_save_the_changes () throws Throwable {
        driver.findElement(By.id("form_sundayOpenTime")).clear();
        driver.findElement(By.id("form_sundayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_sundayCloseTime")).clear();
        driver.findElement(By.id("form_sundayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_mondayOpenTime")).clear();
        driver.findElement(By.id("form_mondayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_mondayCloseTime")).clear();
        driver.findElement(By.id("form_mondayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_tuesdayOpenTime")).clear();
        driver.findElement(By.id("form_tuesdayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_tuesdayCloseTime")).clear();
        driver.findElement(By.id("form_tuesdayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_wednesdayOpenTime")).clear();
        driver.findElement(By.id("form_wednesdayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_wednesdayCloseTime")).clear();
        driver.findElement(By.id("form_wednesdayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_thursdayOpenTime")).clear();
        driver.findElement(By.id("form_thursdayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_thursdayCloseTime")).clear();
        driver.findElement(By.id("form_thursdayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_fridayOpenTime")).clear();
        driver.findElement(By.id("form_fridayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_fridayCloseTime")).clear();
        driver.findElement(By.id("form_fridayCloseTime")).sendKeys(prop.getProperty("endTime"));
        driver.findElement(By.id("form_saturdayOpenTime")).clear();
        driver.findElement(By.id("form_saturdayOpenTime")).sendKeys(prop.getProperty("starttime"));
        driver.findElement(By.id("form_saturdayCloseTime")).clear();
        driver.findElement(By.id("form_saturdayCloseTime")).sendKeys(prop.getProperty("endTime"));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2161/RevertedStoreTimings.jpeg"));
        Thread.sleep(2000);

        driver.findElement(By.name("saveButton")).click();
    }

        @Given("^I choose Time Restricted in the Maintenance tab$")
        public void I_choose_Time_Restricted_in_the_Maintenance_tab () throws Throwable {
            Actions actions = new Actions(driver);
            //Retrieve WebElement 'Music' to perform mouse hover
            WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
            //Mouse hover menuOption 'Music'
            actions.moveToElement(MAINTENANCE).perform();
            driver.findElement(By.xpath("/html/body/div[5]/div[10]")).click();

        }

        @When("^I enter the details for the Licensing hours$")
        public void I_enter_the_details_for_the_Licensing_hours () throws Throwable {
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
            driver.findElement(By.id("form_sundayStartTime")).clear();
            driver.findElement(By.id("form_sundayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_sundayEndTime")).clear();
            driver.findElement(By.id("form_sundayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_mondayStartTime")).clear();
            driver.findElement(By.id("form_mondayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_mondayEndTime")).clear();
            driver.findElement(By.id("form_mondayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_tuesdayStartTime")).clear();
            driver.findElement(By.id("form_tuesdayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_tuesdayEndTime")).clear();
            driver.findElement(By.id("form_tuesdayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_wednesdayStartTime")).clear();
            driver.findElement(By.id("form_wednesdayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_wednesdayEndTime")).clear();
            driver.findElement(By.id("form_wednesdayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_thursdayStartTime")).clear();
            driver.findElement(By.id("form_thursdayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_thursdayEndTime")).clear();
            driver.findElement(By.id("form_thursdayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_fridayStartTime")).clear();
            driver.findElement(By.id("form_fridayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_fridayEndTime")).clear();
            driver.findElement(By.id("form_fridayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            driver.findElement(By.id("form_saturdayStartTime")).clear();
            driver.findElement(By.id("form_saturdayStartTime")).sendKeys(prop.getProperty("RestrictedStartTime"));
            driver.findElement(By.id("form_saturdayEndTime")).clear();
            driver.findElement(By.id("form_saturdayEndTime")).sendKeys(prop.getProperty("RestrictedEndTime"));
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2161/LicensingHours.jpeg"));
            Thread.sleep(2000);

            driver.findElement(By.name("saveButton")).click();

        }

    @When("^I reverted the details for the Licensing hours$")
    public void I_reverted_the_details_for_the_Licensing_hours () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.id("form_sundayStartTime")).clear();
        driver.findElement(By.id("form_sundayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_sundayEndTime")).clear();
        driver.findElement(By.id("form_sundayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_mondayStartTime")).clear();
        driver.findElement(By.id("form_mondayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_mondayEndTime")).clear();
        driver.findElement(By.id("form_mondayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_tuesdayStartTime")).clear();
        driver.findElement(By.id("form_tuesdayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_tuesdayEndTime")).clear();
        driver.findElement(By.id("form_tuesdayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_wednesdayStartTime")).clear();
        driver.findElement(By.id("form_wednesdayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_wednesdayEndTime")).clear();
        driver.findElement(By.id("form_wednesdayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_thursdayStartTime")).clear();
        driver.findElement(By.id("form_thursdayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_thursdayEndTime")).clear();
        driver.findElement(By.id("form_thursdayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_fridayStartTime")).clear();
        driver.findElement(By.id("form_fridayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_fridayEndTime")).clear();
        driver.findElement(By.id("form_fridayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        driver.findElement(By.id("form_saturdayStartTime")).clear();
        driver.findElement(By.id("form_saturdayStartTime")).sendKeys(prop.getProperty("RestrictedStarttime"));
        driver.findElement(By.id("form_saturdayEndTime")).clear();
        driver.findElement(By.id("form_saturdayEndTime")).sendKeys(prop.getProperty("RestrictedEndtime"));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2161/RevertingLicensingHours.jpeg"));
        Thread.sleep(2000);

        driver.findElement(By.name("saveButton")).click();

    }


    @Given("^I fill the action box with maximum characters$")
        public void i_fill_the_action_box_with_maximum_characters () throws Throwable {
        driver.findElement(By.id("form_messageText")).clear();
        driver.findElement(By.id("form_messageText")).sendKeys("This scenarios is validated as part of Regression Testing with the maximum characters in the Action box.On giving maximum characters ,a warning message should be displayed under the action box");
        driver.findElement(By.id("form_messageName")).sendKeys("Red Alert message ");


    }
        @Given("^I change the Red Alert item to live$")
        public void i_change_the_red_alert_item_to_live() throws Throwable{
        //String RedAlertItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).getText();
        //driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        WebElement Messagetype = driver.findElement(By.id("form_messageTypeId"));
        Select select1 = new Select(Messagetype);
        select1.selectByVisibleText("Red Alert");

        //MessageType.selectByVisibleText("Red Alert");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("RedAlertMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement RedAlertItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]"));
        System.out.println(RedAlertItemID.getText());
        RedAlertItemID.click();

        //WebElement Selectbox = driver.findElement(By.name("selected"));
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        //Selectbox.click();

        //driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        //driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[8]/a")).click();

        //WebDriverWait History = new WebDriverWait(driver, 10);
        //History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
            driver.findElement(By.linkText("5. Message History")).click();
        driver.findElement(By.name("Set To Live")).click();
        driver.switchTo().alert().accept();

    }

        @Given("^I opened the Red Alert with UPC$")
        public void i_opened_the_Red_Alert_with_UPC() throws Throwable{
            driver.findElement(By.id("onlyMine")).click();
            driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("RedAlertMessageUPC"));
            driver.findElement(By.id("form_searchButton")).click();
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
        }

        @Given("^I opened the Bulk RTM with UPC$")
        public void i_opened_the_Bulk_RTM_with_UPC() throws Throwable{
        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("BulkRTMMessageUPC"));
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
    }

        @Given("^I opened the Product Restricted item with UPC$")
        public void i_opened_the_Product_Restricted_item_with_UPC() throws Throwable{
        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("ProductRestrictionUPC"));
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
    }

        @Given("^I opened the Sales Message item with UPC$")
        public void I_opened_the_Sales_Message_item_with_UPC() throws Throwable{
        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
    }

        @Given("^I opened the Quantity Restricted item with UPC$")
        public void I_opened_the_Quantity_Restricted_item_with_UPC() throws Throwable{
        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("QuantityRestrictionUPC"));
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
    }

        @Given("^I opened the created CSAT Message$")
        public void I_opened_the_created_CSAT_Message() throws Throwable{
        driver.findElement(By.id("onlyMine")).click();
            WebElement items= driver.findElement(By.id("form_messageTypeId"));
            Select select = new Select(items);
            select.selectByVisibleText("Customer Survey");
            driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
    }

        @Given("^I change the Bulk RTM item to live$")
        public void i_change_the_Bulk_RTM_item_to_live() throws Throwable{
        //String RedAlertItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).getText();
        //driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        WebElement Messagetype = driver.findElement(By.id("form_messageTypeId"));
        Select select1 = new Select(Messagetype);
        select1.selectByVisibleText("Bulk RTM");

        //MessageType.selectByVisibleText("Red Alert");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("BulkRTMMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement BulkRTMItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]"));
        System.out.println(BulkRTMItemID.getText());
        BulkRTMItemID.click();

        driver.findElement(By.linkText("4. Message History")).click();
        driver.findElement(By.name("Set To Live")).click();
        driver.switchTo().alert().accept();

    }

        @Given("^I change the Product Restriction item to live$")
        public void i_change_the_Product_Restriction_item_to_live() throws Throwable{
        WebElement Messagetype = driver.findElement(By.id("form_messageTypeId"));
        Select select1 = new Select(Messagetype);
        select1.selectByVisibleText("Product Restriction");

        //MessageType.selectByVisibleText("Red Alert");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("ProductRestrictionUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement ProductRestrictionItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]"));
        System.out.println(ProductRestrictionItemID.getText());
        ProductRestrictionItemID.click();
        //WebDriverWait History = new WebDriverWait(driver, 10);
        //History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.linkText("4. Message History")).click();
        driver.findElement(By.name("Set To Live")).click();
        driver.switchTo().alert().accept();
    }

        @Given("^I fill other mandatory details$")
        public void i_fill_other_mandatory_details () throws Throwable {
        driver.findElement(By.id("noExpiryDate")).click();
        //driver.findElement(By.id("form_mainReasonId")).
        Select Reason = new Select(driver.findElement(By.id("form_mainReasonId")));
        Reason.selectByVisibleText("Quality");

    }

        @When("^I select Next option$")
        public void i_select_Next_option () throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.name("nextButton")).click();

    }

        @Then("^I validated the warning message below the action box$")
        public void i_validated_the_warning_message_below_the_action_box () throws Throwable {
        String Expected1 = "Maximum character limit for 1 line of this field is 155. Please reduce your input.";

        String warning1 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/font/font")).getText();
       Assert.assertEquals(warning1, Expected1);
        driver.findElement(By.id("form_messageText")).clear();
        driver.findElement(By.id("form_messageText")).sendKeys("This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box\n" +
                "This scenarios is validated as part of Regression Testing with the maximum characters in the Action box");
        driver.findElement(By.name("nextButton")).click();
        String Expected2 = "Maximum 10 lines are allowed in this field. Please reduce your input.";
        String warning2 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/font/font")).getText();
        Assert.assertEquals(warning1, Expected1);

       /* driver.close();
        driver.quit();*/

    }

        @Given("^I select Bulk RTM from the message type dropdown list$")
        public void i_select_Bulk_RTM_from_the_message_type_dropdown_list () throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Bulk RTM");
        driver.findElement(By.id("form_department")).sendKeys("F20A");
        driver.findElement(By.name("nextButton")).click();
//Bulk RTM
    }

        @Given("^I select Product restriction from the message type dropdown list$")
        public void i_select_Product_restriction_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Product Restriction");
        driver.findElement(By.id("form_department")).sendKeys("F20A");
        driver.findElement(By.name("nextButton")).click();

    }
        @Given("^I select Quantity Restriction from the message type dropdown list$")
        public void i_select_Quantity_restriction_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Quantity Restriction");
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I choose user option  from the maintanence tab$")
        public void i_choose_user_option_from_the_maintanence_tab () throws Throwable {
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[1]")).click();


    }

        @Given("^I choose add user option$")
        public void i_choose_add_user_option () throws Throwable {

        driver.findElement(By.id("addButton")).click();

    }

        @Given("^I enter the  user name$")
        public void i_enter_the_user_name () throws Throwable {
        driver.findElement(By.xpath("/*//*[@id=\"form_user_employeeNumber\"]")).sendKeys(prop.getProperty("NewUser"));

    }

        @Given("^I fill other user details$")
        public void i_fill_other_user_details () throws Throwable {
        driver.findElement(By.xpath("/*//*[@id=\"form_startDate\"]")).sendKeys("27/10/2023");
        driver.findElement(By.xpath("/*//*[@id=\"form_leavingDate\"]")).sendKeys("18/06/2030");
        driver.findElement(By.xpath("/*//*[@id=\"form_user_surname\"]")).sendKeys("Arthur");
        driver.findElement(By.xpath("/*//*[@id=\"form_user_forename\"]")).sendKeys("Morgan");
        driver.findElement(By.xpath("/*//*[@id=\"form_birthDate\"]")).sendKeys("10/07/1995");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2161/NewUser.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

    }

        @Given("^I choose user Role$")
        public void i_choose_user_Role () throws Throwable {
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        Select Role1 = new Select(driver.findElement(By.id("form_role")));
        Role1.selectByVisibleText("Advisor");
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.id("addButton")).click();
        Select Role2 = new Select(driver.findElement(By.id("form_role")));
        Role2.selectByVisibleText("SCOT");
        driver.findElement(By.name("nextButton")).click();
        //driver.findElement(By.id("addButton")).click();
        //Select Role3 = new Select(driver.findElement(By.id("form_role")));
        //Role3.selectByVisibleText("Store Administrator / Manager");
        //driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.id("deleteButton")).click();
    }

        @Given("^I Added stores for the user$")
        public void i_Added_stores_for_the_user () throws Throwable {
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        Select Organisation = new Select(driver.findElement(By.id("form_organisationCode")));
        Organisation.selectByVisibleText(prop.getProperty("StoreName"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        Select Organisation2 = new Select(driver.findElement(By.id("form_organisationCode")));
        Organisation2.selectByVisibleText(prop.getProperty("StoreName2"));
        // driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I save user the changes$")
        public void i_save_the_changes () throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.name("nextButton")).click();

        driver.findElement(By.name("saveButton")).click();

    }

        @Then("^Batch Reference number is captured$")
        public void batch_Reference_number_is_captured () throws Throwable {
            if (Batchneeded==true){
        WebDriverWait What = new WebDriverWait(driver, 5);
        What.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        String[] batchRef = alertText.split(" : ")[2].split("\n");
        batchRefNumber = (((((batchRef[0].substring(0, batchRef[0].length() - 1))))));
        System.out.println(batchRefNumber);
        //  String SaveS = String.format("%.0f",batchRefNumber);

        // String fileName = "C:\\Users\\p9137006\\Documents\\Center_Till_Automation\\ReferanceNumber_test.xls";
        //    FileWriter fw = new FileWriter(fileName,true); // -->the parameter true is very imp which allows the file to be opened in append mode
        //  fw.append(batchRefNumber.toString());
        //fw.append(batchRefNumber); //---> replace the parameter with your actual string value to be stored.
        //          fw.append("\r\n"); // ---> this will point the cursor in new line after its execution
        //   fw.close();
                //FileWriter fileWriter = new FileWriter("C:\\\\Users\\\\p9137006\\\\Documents\\\\Center_Till_Automation\\\\ReferanceNumber_test.txt", true);
        FileWriter fileWriter = new FileWriter("C:\\\\Users\\\\Premkumar.surendiram\\\\Documents\\\\Center_SCOT_Automation - Backup\\\\ReferanceNumber_test.txt", true); //Set true for append mode
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(batchRefNumber);  //New line
        printWriter.close();
        alert.accept();
        driver.findElement(By.id("imgLogoff")).click();
       /* driver.close();
        driver.quit();*/

    }}

        @Given("^I select Sales Message from the message type dropdown list$")
        public void i_select_Sales_Message_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Sales Message");
        //Select dep = new Select(driver.findElement(By.id("form_merchGroupTypeId")));
        //dep.selectByVisibleText("General Merchandise");
            driver.findElement(By.id("form_department")).sendKeys(prop.getProperty("Department"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

    }

        @Given("^I fill the Sales message details$")
        public void i_fill_the_Sales_message_details () throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"form_messageText\"]")).sendKeys(prop.getProperty("SalesMessage"));
        driver.findElement(By.id("form_messageName")).sendKeys("Sales Message");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("endDate")).sendKeys(enddate);

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/SalesMessage.jpeg"));
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I added the Products for the Sales Message$")
        public void i_added_the_Products_for_the_Sales_Message () throws Throwable {

        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("SalesMessageUPC"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/input[1]")).click();
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(5000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3377/SalesMessageProducts.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        Thread.sleep(5000);
    }

        @Given("^I added store for Red Alert item and saved the changes$")
        public void i_added_store_for_Red_Alert_item_and_saved_the_changes () throws Throwable {
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
            //driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(4000);
        WebElement Checkbox = driver.findElement(By.name("deleted"));
        if (Checkbox.isDisplayed()) {
            Checkbox.click();
        }
        driver.findElement(By.name("nextButton")).click();
        //Thread.sleep(7000);
        driver.findElement(By.name("selected")).click();
        //if (Checkbox1.isDisplayed()) {
            //Checkbox1.click();
        //}
        driver.findElement(By.name("saveButton")).click();
        Thread.sleep(8000);
        driver.switchTo().alert().accept();
        driver.switchTo().alert().accept();
    }

    @Given("^I added store for Sales Message item and saved the changes$")
    public void i_added_store_for_Sales_Message_item_and_saved_the_changes () throws Throwable {
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(4000);
        WebElement Checkbox = driver.findElement(By.name("deleted"));
        if (Checkbox.isDisplayed()) {
            Checkbox.click();
        }
        driver.findElement(By.name("saveButton")).click();
        //Thread.sleep(7000);
        driver.findElement(By.name("selected")).click();
        //if (Checkbox1.isDisplayed()) {
        //Checkbox1.click();
        //}
        driver.findElement(By.name("saveButton")).click();
        Thread.sleep(8000);
        //driver.switchTo().alert().accept();
        //driver.switchTo().alert().accept();
    }

    @Given("^I added store for Quantity Restrition item and saved the changes$")
    public void i_added_store_for_Quantity_Restriction_item_and_saved_the_changes () throws Throwable {
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(4000);
        WebElement Checkbox = driver.findElement(By.name("deleted"));
        if (Checkbox.isDisplayed()) {
            Checkbox.click();
        }
        driver.findElement(By.name("saveButton")).click();
        //Thread.sleep(7000);
        driver.findElement(By.name("selected")).click();
        //if (Checkbox1.isDisplayed()) {
        //Checkbox1.click();
        //}
        driver.findElement(By.name("saveButton")).click();
        Thread.sleep(8000);
        //driver.switchTo().alert().accept();
        //driver.switchTo().alert().accept();
    }

        @Given("^I added store for Bulk RTM and saved the changes$")
        public void i_added_store_for_Bulk_RTM_and_saved_the_changes () throws Throwable {
            driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
            driver.findElement(By.id("nextButton")).click();
            //driver.findElement(By.id("entry")).click();
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("Store"));
            driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
            driver.findElement((By.name("deleted"))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();
            driver.switchTo().alert().accept();
            driver.switchTo().alert().accept();
        }

    @Given("^I added store for Product Restriction and saved the changes$")
    public void i_added_store_for_Product_Restriction_and_saved_the_changes () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
        driver.findElement(By.id("nextButton")).click();
        //driver.findElement(By.id("entry")).click();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("Store"));
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
        driver.findElement((By.name("deleted"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();
        driver.switchTo().alert().accept();
        driver.switchTo().alert().accept();
    }

        @When("^I change the Sales message item to live$")
        public void i_change_the_Sales_message_item_to_live () throws Throwable {
        // String SalesMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).getText();
        driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Sales Message");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        SalesMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).getText();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[8]/a")).click();

        WebDriverWait History = new WebDriverWait(driver, 10);
        History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.name("Set To Live")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
        @When("^I change the Quantity Restricted item to live$")
        public void i_change_the_Quantity_Restricted_item_to_live () throws Throwable {
        // String SalesMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).getText();
        //driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Quantity Restriction");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("QuantityRestrictionUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement QuantityRestrictionItemID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]"));
        System.out.println(QuantityRestrictionItemID.getText());
        QuantityRestrictionItemID.click();
        driver.findElement(By.linkText("5. Message History")).click();
        driver.findElement(By.name("Set To Live")).click();
        driver.switchTo().alert().accept();
    }

        @When("^I choose Live Sales message$")
        public void i_choose_Live_Sales_message () throws Throwable {
        // driver.findElement(By.id("form_messageIDName")).sendKeys(SalesMessageID);
        driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Sales Message");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("LIVE");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
    }

        @When("^I remove UPC from the message$")
        public void i_remove_UPC_from_the_message () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[6]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[6]/input")).click();
        driver.findElement(By.name("deleteButton")).click();


    }

        @When("^I remove store from the message$")
        public void i_remove_store_from_the_message () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[8]/a")).click();
        driver.findElement(By.xpath("//*[@name='selected']")).click();
        driver.findElement(By.xpath("//input[@name='deleteButton']")).click();

    }

        @When("^I save the change$")
        public void i_save_the_change () throws Throwable {
        driver.findElement(By.name("saveButton")).click();



    }

        @Then("^changes are saved$")
        public void changes_are_saved () throws Throwable {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.findElement(By.id("imgLogoff")).click();
        /*driver.close();
        driver.quit();*/

    }

        @Given("^I select Payment Message from the message type dropdown list$")
        public void i_select_Payment_Message_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Payment Message");
        //driver.findElement(By.id("form_department")).sendKeys(prop.getProperty("Department"));
        driver.findElement(By.name("nextButton")).click();

    }

        @When("^I add Payment message details$")
        public void i_add_Payment_message_details () throws Throwable {
        driver.findElement(By.id("form_messageName")).sendKeys("Test-Payment Message");
        driver.findElement(By.id("form_paymentMessageText")).sendKeys("Payment Message");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("endDate")).sendKeys(enddate);

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3378/PaymentMessage.jpeg"));

        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();

    }

        @When("^I change the Payment message to live$")
        public void i_change_the_Payment_message_to_live () throws Throwable {


        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Payment Message");

        // driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        PaymentMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).getText();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[6]/a")).click();

        WebDriverWait History = new WebDriverWait(driver, 10);
        History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.name("Set To Live")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

        @When("^I choose Live Payment message$")
        public void i_choose_Live_Payment_message () throws Throwable {
        //  driver.findElement(By.id("form_messageIDName")).sendKeys(PaymentMessageID);
        // driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Payment Message");

        //  driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("LIVE");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();

    }

        @Given("^I select Refund Message from the message type dropdown list$")
        public void i_select_Refund_Message_from_the_message_type_dropdown_list () throws Throwable {

        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Refund Message");
            Select dep = new Select(driver.findElement(By.id("form_merchGroupTypeId")));
            dep.selectByVisibleText("General Merchandise");

            driver.findElement(By.id("form_department")).sendKeys(prop.getProperty("Department"));
            driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I fill the Refund message details$")
        public void i_fill_the_Refund_message_details () throws Throwable {
        driver.findElement(By.id("form_messageText")).sendKeys(prop.getProperty("RefundMessage"));
        driver.findElement(By.id("form_messageName")).sendKeys("Refund Message");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("endDate")).sendKeys(enddate);

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3379/RefundMessage.jpeg"));
        driver.findElement(By.name("nextButton")).click();
    }

        @When("^I change the refund message to live$")
        public void i_change_the_refund_message_to_live () throws Throwable {

        //String SalesMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).getText();
        driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Refund Message");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        RefundMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).getText();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[8]/a")).click();

        WebDriverWait History = new WebDriverWait(driver, 10);
        History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.name("Set To Live")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

        @When("^I choose Live Refund message$")
        public void i_choose_Live_Refund_message () throws Throwable {

        //driver.findElement(By.id("form_messageIDName")).sendKeys(RefundMessageID);
        driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Refund Message");

        driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("LIVE");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("selected"))));
        Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();


    }

        @Given("^I select Till Message from the message type dropdown list$")
        public void i_select_Till_Message_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Till Message");
        Select messages1 = new Select(driver.findElement(By.id("form_tillMsgParam")));
        messages1.selectByVisibleText("Till Message");
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I fill the Till message details$")
        public void i_fill_the_Till_message_details () throws Throwable {
        driver.findElement(By.id("form_messageName")).sendKeys(prop.getProperty("TillMessage"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("endDate")).sendKeys(enddate);
        driver.findElement(By.id("userLogOnMessage")).click();


    }

        @Given("^I add Bullet point name$")
        public void i_add_Bullet_point_name () throws Throwable {
        driver.findElement(By.name("addButton")).click();
        driver.findElement(By.name("bulletName")).sendKeys("Hello Adviser");
        driver.findElement(By.name("receiptTextFieldId1")).sendKeys("Aware of M&S plan A and save Paper");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3380/TillMessage.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
    }

        @When("^I change the Till message to live$")
        public void i_change_the_Till_message_to_live () throws Throwable {

        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Till Message");

        // driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        //driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        TillMessageID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).getText();

        //  WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]"))));
        //  Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[8]/a")).click();

        WebDriverWait History = new WebDriverWait(driver, 10);
        History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.name("Set To Live")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();


    }

        @When("^I choose Live Till message$")
        public void i_choose_Live_Till_message () throws Throwable {
        //driver.findElement(By.id("form_messageIDName")).sendKeys(RefundMessageID);
        // driver.findElement(By.id("form_department")).sendKeys((prop.getProperty("Department")));
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Till Message");

        //  driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("LIVE");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
//        WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]"))));
        //     Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
    }

        @Given("^I select Age Restriction message from the message type dropdown list$")
        public void i_select_Age_Restriction_message_from_the_message_type_dropdown_list () throws Throwable {
        Select messages = new Select(driver.findElement(By.id("form_typeId")));
        messages.selectByVisibleText("Product Age Restriction");
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I fill the Age Restriction message message details$")
        public void i_fill_the_Age_Restriction_message_message_details () throws Throwable {
        driver.findElement(By.id("form_messageName")).sendKeys(prop.getProperty("AgeRestrictionMessage"));
        driver.findElement(By.name("tillRestrictionMessageLine1")).sendKeys("Test Age Restriction message");
        driver.findElement(By.id("form_restrictionValue")).sendKeys("18");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("endDate")).sendKeys(enddate);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3381/AgeRestriction.jpeg"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I added Zone$")
        public void i_added_Zone () throws Throwable {
        String Region = prop.getProperty("Region");
        for (int i = 1; i <= 11; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[1]")).getText();
            if (msgID.contains(Region)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[3]/input")).click();
                break;
            }
        }
        driver.navigate().refresh();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-3381/Zone.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("prevButton"));
    }

        @When("^I saved the changes$")
        public void i_saved_the_changes () throws Throwable {

        driver.findElement(By.id("saveButton")).click();

    }

        @When("^I change the Age restriction  message to live$")
        public void i_change_the_Age_restriction_message_to_live () throws Throwable {
        Select MessageType = new Select(driver.findElement(By.id("form_messageTypeId")));
        MessageType.selectByVisibleText("Product Age Restriction");

        // driver.findElement(By.id("form_upc")).sendKeys(prop.getProperty("SalesMessageUPC"));

        Select MessageStatus = new Select(driver.findElement(By.id("form_messageStatus")));
        MessageStatus.selectByVisibleText("SAVED");

        driver.findElement(By.id("onlyMine")).click();
        driver.findElement(By.id("form_searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        Age_RestrictionMessage = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).getText();

        //  WebElement Selectbox = driver.findElement(By.name("selected"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]"))));
        //  Selectbox.click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr/td[8]")).click();

        WebDriverWait History = new WebDriverWait(driver, 10);
        History.until(ExpectedConditions.visibilityOfElementLocated((By.name("Set To Live"))));
        driver.findElement(By.name("Set To Live")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

        @Given("^I save the changes$")
        public void i_save_user_the_changes () throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();

    }

        @Given("^I enter the user name for whom password being changed$")
        public void i_enter_the_user_name_for_whom_password_being_changed () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("Change_Password_user"));
        driver.findElement(By.id("form_includeExEmployees"));
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();


    }

        @When("^I enter a new password$")
        public void i_enter_a_new_password () throws Throwable {
        driver.findElement(By.id("form_newPassword")).sendKeys(prop.getProperty("NewPsd"));
        driver.findElement(By.id("form_retypePassword")).sendKeys(prop.getProperty("NewPsd"));
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2166/ChangePassword.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("saveButton")).click();
    }

        @Given("^I enter the user name for whom enddate being changed$")
        public void i_enter_the_user_name_for_whom_enddate_being_changed () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("ReinstateUser"));
        //  driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("Change_Password_user"));
        driver.findElement(By.id("form_includeExEmployees")).click();
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
    }

        @When("^I enter a new enddate$")
        public void i_enter_a_new_enddate () throws Throwable {
        driver.findElement(By.id("form_leavingDate")).clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String enddate = sdf.format(cal.getTime());
        driver.findElement(By.id("form_leavingDate")).sendKeys(enddate);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2163/ReinstateUser.jpeg"));
            Thread.sleep(2000);

        driver.findElement(By.id("form_newPassword")).sendKeys(prop.getProperty("ReinstateUserPWD"));
        driver.findElement(By.id("form_retypePassword")).sendKeys(prop.getProperty("ReinstateUserPWD"));

        driver.findElement(By.name("saveButton")).click();

    }

        @Given("^I enter the user name for whom Role being changed$")
        public void i_enter_the_user_name_for_whom_Role_being_changed () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("Change_User_Role"));
        //  driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("Change_Password_user"));
        driver.findElement(By.id("form_includeExEmployees")).click();
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        driver.findElement(By.id("form_newPassword")).sendKeys(prop.getProperty("UserRolePWD"));
        driver.findElement(By.id("form_retypePassword")).sendKeys(prop.getProperty("UserRolePWD"));
        driver.findElement(By.name("nextButton")).click();


    }

        @Given("^I add the manager user Role$")
        public void i_add_the_manager_user_Role () throws Throwable {


        driver.findElement(By.id("addButton")).click();

        Select Role = new Select(driver.findElement(By.id("form_role")));
        Role.selectByVisibleText("Store Administrator / Manager");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2164/NewRole.jpeg"));
            Thread.sleep(2000);



        driver.findElement(By.name("nextButton")).click();


    }

        @Given("^I save user role the changes$")
        public void i_save_user_role_the_changes () throws Throwable {
        driver.findElement(By.name("saveButton")).click();


    }

        @Given("^I remove the manager user Role$")
        public void i_remove_the_manager_user_Role () throws Throwable {
        String Role = "5";
        for (int i = 1; i <= 5; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[2]")).getText();
            if (msgID.contains(Role)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[3]")).click();
                break;
            }

        }
        driver.findElement(By.id("deleteButton")).click();


    }

        @When("^I enter the user name for whom Store being removed$")
        public void i_enter_the_user_name_for_whom_Store_being_removed () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("RemoveStore_User"));
        //  driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("Change_Password_user"));
        driver.findElement(By.id("form_includeExEmployees")).click();
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        driver.findElement(By.id("form_newPassword")).sendKeys(prop.getProperty("UserRolePWD"));
        driver.findElement(By.id("form_retypePassword")).sendKeys(prop.getProperty("UserRolePWD"));
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("nextButton")).click();

    }

        @Given("^I remove store for the user$")
        public void i_remove_store_for_the_user () throws Throwable {


        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]")).click();
        driver.findElement(By.id("deleteButton")).click();
            Thread.sleep(2000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2162/RemoveUser.jpeg"));
            Thread.sleep(2000);
        Thread.sleep(4000);
        //  driver.findElement(By.name("nextButton")).click();


    }

        @Then("^I add store for the user$")
        public void i_add_store_for_the_user () throws Throwable {

        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        Select Organisation = new Select(driver.findElement(By.id("form_organisationCode")));
        Organisation.selectByVisibleText(prop.getProperty("StoreName"));
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I choose product option  from the maintanence tab$")
        public void i_choose_product_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();


    }

        @Given("^I search for a product$")
        public void i_search_for_a_product () throws Throwable {

        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("UPC"));

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();

    }

        @When("^I change the product Name and Receipt description$")
        public void i_change_the_product_Name_and_Receipt_description () throws Throwable {
        driver.findElement(By.id("form_product_name")).clear();
        driver.findElement(By.id("form_product_name")).sendKeys(prop.getProperty("ProductName"));
        driver.findElement(By.id("form_product_receiptDescription")).clear();
        driver.findElement(By.id("form_product_receiptDescription")).sendKeys(prop.getProperty("ReceiptDescription"));

            Thread.sleep(2000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2153/ProductChange.jpeg"));
            Thread.sleep(2000);

    }

        @When("^I revert the product name and Receipt description$")
        public void I_revert_the_product_name_and_Receipt_description () throws Throwable {
        driver.findElement(By.id("form_product_name")).clear();
        driver.findElement(By.id("form_product_name")).sendKeys(prop.getProperty("ProductName1"));
        driver.findElement(By.id("form_product_receiptDescription")).clear();
        driver.findElement(By.id("form_product_receiptDescription")).sendKeys(prop.getProperty("ReceiptDescription1"));

        Thread.sleep(2000);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2153/RevertProductChange.jpeg"));
        Thread.sleep(2000);

    }

        @Given("^I choose Reason code option from the maintanence tab$")
        public void i_choose_Reason_code_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[6]")).click();
    }

        @Given("^I select for a Reason code$")
        public void i_select_for_a_Reason_code () throws Throwable {

        driver.findElement(By.id("form_search")).sendKeys("Age Restriction");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[5]/input")).click();

        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();


    }

        @When("^I disable a Reason code$")
        public void i_disable_a_Reason_code () throws Throwable {

    }

        @When("^I select Deactivate button$")
        public void i_select_Deactivate_button () throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"form_activationStatefalse\"]")).click();

            Thread.sleep(2000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2145/SalesMessageProducts.jpeg"));
            Thread.sleep(2000);
        //driver.navigate().refresh();
        driver.findElement(By.name("saveButton")).click();
    }

        @When("^I select activate button$")
        public void i_select_activate_button () throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form_activationStatetrue\"]")).click();
        //driver.navigate().refresh();
        driver.findElement(By.name("saveButton")).click();
    }

        @Given("^I enter the user name whose password is locked$")
        public void i_enter_the_user_name_whose_password_is_locked () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("ReleaseUser"));
        driver.findElement(By.id("form_includeExEmployees"));
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();

    }

        @When("^I release the user from BO$")
        public void i_release_the_user_from_BO () throws Throwable {
        driver.findElement(By.id("form_user_releaseBlockedUser")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-799/ReleaseUser.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("saveButton")).click();
    }

        @Given("^I enter the user name for whose password needs to reset$")
        public void i_enter_the_user_name_for_whose_password_needs_to_reset () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("LockedUser"));
        driver.findElement(By.id("form_includeExEmployees"));
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();

    }

        @When("^I force change the password$")
        public void i_force_change_the_password () throws Throwable {
        driver.findElement(By.id("form_user_forcePasswordChange")).click();
        driver.findElement(By.id("form_newPassword")).sendKeys("000000");
        driver.findElement(By.id("form_retypePassword")).sendKeys("000000");
        Thread.sleep(5000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2177/LockedUser.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("saveButton")).click();

    }

        @Given("^I choose Store option from the maintanence tab$")
        public void i_choose_Store_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[9]")).click();

    }

        @Given("^I select a store$")
        public void i_select_a_store () throws Throwable {
        driver.findElement(By.id("form_search")).sendKeys(prop.getProperty("StoreCode"));
        //   driver.findElement(By.id("form_includeExEmployees"));
        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
    }

        @When("^I update the auto secure time$")
        public void i_update_the_auto_secure_time () throws Throwable {

        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("form_storeDisplayer_autoSecureTime")).clear();
        driver.findElement(By.id("form_storeDisplayer_autoSecureTime")).sendKeys("2");
            Thread.sleep(2000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-2147/secure.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("saveButton")).click();

    }

        @When("^I revert the auto secure time$")
        public void i_revert_the_auto_secure_time () throws Throwable {
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("form_storeDisplayer_autoSecureTime")).clear();
        driver.findElement(By.id("form_storeDisplayer_autoSecureTime")).sendKeys("10");
        driver.findElement(By.name("saveButton")).click();

    }

        @Given("^I choose Store maintanence by group option from the maintanence tab$")
        public void i_choose_Store_maintanence_by_group_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[10]")).click();

    }

        @When("^I add receipt footer$")
        public void i_add_receipt_footer () throws Throwable {
        driver.findElement(By.id("form_storeGroupDisplayer_receiptFooterLine1")).clear();
        driver.findElement(By.id("form_storeGroupDisplayer_receiptFooterLine1")).sendKeys(prop.getProperty("FooterMessage"));

        driver.findElement(By.name("storeGroupDisplayer.sendReceiptFooter")).click();
        // nextButton
        driver.findElement(By.name("nextButton")).click();


    }

        @When("^I save the store Maintenance changes$")
        public void i_save_the_store_Maintenance_changes () throws Throwable {
        driver.findElement(By.name("saveButton")).click();
        driver.findElement(By.name("saveButton")).click();
    }

        @When("^I remove receipt footer$")
        public void i_remove_receipt_footer () throws Throwable {
        driver.findElement(By.id("form_storeGroupDisplayer_receiptFooterLine1")).clear();
        //driver.findElement(By.id("form_storeGroupDisplayer_receiptFooterLine1")).sendKeys(prop.getProperty("FooterMessage"));

        driver.findElement(By.name("storeGroupDisplayer.sendReceiptFooter")).click();
        // nextButton
        driver.findElement(By.name("nextButton")).click();

    }

        @Given("^I choose Currency Exchange rate option from the maintanence tab$")
        public void i_choose_Currency_Exchange_rate_option_from_the_maintanence_tab () throws Throwable {
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[7]")).click();

    }

        @When("^I choose Pound as Base Currency$")
        public void i_choose_Pound_as_Base_Currency () throws Throwable {

        driver.findElement(By.id("form_search")).sendKeys("Pound Sterling");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();


    }

        @When("^I update the Pound currency value against Australian Dollar$")
        public void i_update_the_Pound_currency_value_against_Australian_Dollar () throws Throwable {

        String Currency = "Australian Dollar";
        for (int i = 1; i <= 7; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[1]")).getText();
            if (msgID.contains(Currency)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[3]/input")).sendKeys(prop.getProperty("Value"));
                break;
            }
        }
    }


        @When("^I save the currency changes$")
        public void i_save_the_currency_changes () throws Throwable {
        driver.findElement(By.name("saveButton")).click();

    }

        @When("^I Revert the Pound currency value against Australian Dollar$")
        public void i_Revert_the_Pound_currency_value_against_Australian_Dollar () throws Throwable {

        String Currency = "Australian Dollar";
        for (int i = 1; i <= 7; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[1]")).getText();
            if (msgID.contains(Currency)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[3]/input")).sendKeys("2.50");
                break;
            }
        }

    }

        @Given("^I choose Function by personality company option from the maintanence tab$")
        public void i_choose_Function_by_personality_company_option_from_the_maintanence_tab () throws Throwable {


        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        WebElement FBP = driver.findElement(By.xpath("/html/body/div[4]/div[14]"));

        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        actions.moveToElement(FBP).perform();
        driver.findElement(By.xpath("/html/body/div[5]/div[1]")).click();


    }

        @Given("^I choose personality$")
        public void i_choose_personality () throws Throwable {
        driver.findElement(By.id("form_searchText")).sendKeys("GM");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]"))));
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[2]")).click();

    }

        @Given("^I disable product selection option$")
        public void i_disable_product_selection_option () throws Throwable {
        driver.findElement(By.id("form_searchText")).sendKeys("Product Selection");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.name("devpApOrguLinks[0].enabled"))));
         if (driver.findElement(By.xpath("//*[@type='checkbox']")).getAttribute("value").contains("true")) {

             System.out.println("Button is enabled");
             driver.findElement(By.xpath("//*[@type='checkbox']")).click();
             driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();



            }
            else if  (driver.findElement(By.xpath("//input[@name='devpApOrguLinks[0].enabled']")).getAttribute("value").contains("false")){
                System.out.println("Button is disabled");
             Batchneeded=false;

            }



        Thread.sleep(2000);


    }

        @When("^i save Function by personality changes$")
        public void i_save_Function_by_personality_changes () throws Throwable {

        driver.findElement(By.id("saveButton")).click();

    }

        @Given("^I enable product selection option$")
        public void i_enable_product_selection_option () throws Throwable {
        driver.findElement(By.id("form_searchText")).sendKeys("Product Selection");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.name("devpApOrguLinks[0].enabled"))));

            if (driver.findElement(By.xpath("//*[@type='checkbox']")).getAttribute("value").contains("true")) {

                System.out.println("Button is enabled");
                Batchneeded=false;



            }
            else if  (driver.findElement(By.xpath("//input[@name='devpApOrguLinks[0].enabled']")).getAttribute("value").contains("false")){
                System.out.println("Button is disabled");
                driver.findElement(By.xpath("//*[@type='checkbox']")).click();
                driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();


            }
        //  driver.findElement(By.name("devpApOrguLinks[0].enabled")).click();


    }

        @Given("^I choose Function by personality StoreGroups option from the maintanence tab$")
        public void i_choose_Function_by_personality_StoreGroups_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        WebElement FBP = driver.findElement(By.xpath("/html/body/div[4]/div[14]"));

        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        actions.moveToElement(FBP).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[5]/div[2]")).click();


    }

        @Given("^I disable Customer Reservation option$")
        public void i_disable_Customer_Reservation_option () throws Throwable {

        driver.findElement(By.id("form_searchText")).sendKeys("Customer Reservation");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.name("devpApOrguLinks[0].enabled"))));
        if (driver.findElement(By.xpath("//*[@type='checkbox']")).getAttribute("value").contains("true")) {

                System.out.println("Button is enabled");
                driver.findElement(By.xpath("//*[@type='checkbox']")).click();
                driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();



            }
            else if  (driver.findElement(By.xpath("//input[@name='devpApOrguLinks[0].enabled']")).getAttribute("value").contains("false")){
                System.out.println("Button is disabled");
                Batchneeded=false;

            }
        Thread.sleep(5000);
        // driver.findElement(By.name("devpApOrguLinks[0].enabled")).click();
        driver.findElement(By.name("nextButton")).click();

    }

        @When("^i save Function by personality Store group changes$")
        public void i_save_Function_by_personality_Store_group_changes () throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();
    }

        @Given("^I enable Customer Reservation option$")
        public void i_enable_Customer_Reservation_option () throws Throwable {

        driver.findElement(By.id("form_searchText")).sendKeys("Customer Reservation");

        driver.findElement(By.name("searchButton")).click();
        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.visibilityOfElementLocated((By.name("devpApOrguLinks[0].enabled"))));

            if (driver.findElement(By.xpath("//*[@type='checkbox']")).getAttribute("value").contains("true")) {

                System.out.println("Button is enabled");
                Batchneeded=false;



            }
            else if  (driver.findElement(By.xpath("//input[@name='devpApOrguLinks[0].enabled']")).getAttribute("value").contains("false")){
                System.out.println("Button is disabled");
                driver.findElement(By.xpath("//*[@type='checkbox']")).click();
                driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();


            }
        Thread.sleep(2000);
        driver.findElement(By.name("nextButton")).click();
    }

        @Given("^I choose Receipt restriction option from the maintanence tab$")
        public void i_choose_Receipt_restriction_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[20]")).click();

    }

        @When("^I set suppression for  cafe$")
        public void i_set_suppression_for_cafe () throws Throwable {

        String Personality = "Small Cafe";
        for (int i = 1; i <= 12; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[2]")).getText();
            if (msgID.contains(Personality)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[4]/center/input")).click();
                Thread.sleep(4000);
                break;
            }
        }
        driver.findElement(By.id("nextButton")).click();


    }

        @When("^I remove suppression for  cafe$")
        public void i_remove_suppression_for_cafe () throws Throwable {

        String Personality = "Small Cafe";
        for (int i = 1; i <= 12; i++) {
            String msgID = driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[2]")).getText();
            if (msgID.contains(Personality)) {
                driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[" + i + "]/td[6]/center/input")).click();
                Thread.sleep(4000);
                break;
            }
        }
        driver.findElement(By.id("nextButton")).click();

    }

        @Given("^I choose Maintain table layout option from the maintanence tab$")
        public void i_choose_Maintain_table_layout_option_from_the_maintanence_tab () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[5]")).click();

    }

        @Given("^I add stores to change the table layout$")
        public void i_add_stores_to_change_the_table_layout () throws Throwable {

        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("entry")).clear();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
        Thread.sleep(4000);
        WebElement Checkbox = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]"));
        if (Checkbox.isDisplayed()) {
            Checkbox.click();
        }
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(4000);

        driver.findElement(By.name("saveButton")).click();


    }


        @When("^I set XL Deli layout for Deli$")
        public void i_set_XL_Deli_layout_for_Deli () throws Throwable {

        Select Table = new Select(driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]/select")));
        Table.selectByVisibleText("XL Deli - 64 Seats");
        Thread.sleep(3000);
        driver.findElement(By.name("saveButton")).click();


    }

        @When("^I set Medium Deli layout for Deli$")
        public void i_set_Medium_Deli_layout_for_Deli () throws Throwable {

        Select Table = new Select(driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr[1]/td[3]/select")));
        Table.selectByVisibleText("Medium Deli - 36 Seats");
        Thread.sleep(3000);
        driver.findElement(By.name("saveButton")).click();

    }

        @Given("^I set receipt restriction value for GM$")
        public void i_set_receipt_restriction_value_for_GM () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RET1024\"]")).sendKeys(prop.getProperty("RestrictionValue"));

    }

        @Given("^I click on Next button$")
        public void i_click_on_Next_button () throws Throwable {
        driver.findElement(By.id("nextButton")).click();

    }

        @Given("^I Add a Store$")
        public void i_Add_a_Store () throws Throwable {
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.name("addButton")).click();

    }

        @Given("^I click on Save button$")
        public void i_click_on_Save_button () throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();
    }

        @Given("^I choose Device menu$")
        public void i_choose_Device_menu () throws Throwable {
        /* To capture mouse hover in a page */
        Actions actions = new Actions(driver);
        WebElement target = driver.findElement(By.id("divoCMenu0_0"));
        actions.moveToElement(target).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[12]")).click();
    }

        @Given("^I Add a new device$")
        public void i_Add_a_new_device () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
        driver.findElement(By.name("deviceDisplayer.device.code")).sendKeys(prop.getProperty("TillID"));
        Select Orgucode = new Select(driver.findElement(By.name("organisationCode")));
        Orgucode.selectByVisibleText(prop.getProperty("StoreName"));
        Select Devicetype = new Select(driver.findElement(By.id("form_deviceTypeId")));
        Devicetype.selectByVisibleText("TILL");
        driver.findElement(By.name("device.name")).sendKeys("Test Till_123");
        driver.findElement(By.name("device.hardwareId")).sendKeys(prop.getProperty("HardwareId"));
        driver.findElement(By.name("saveButton")).click();

    }

        @Given("^I choose Location menu$")
        public void i_choose_Location_menu () throws Throwable {
        /* To capture mouse hover in a page and select Location menu */
        Actions actions = new Actions(driver);
        WebElement target = driver.findElement(By.id("divoCMenu0_0"));
        actions.moveToElement(target).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[11]")).click();

    }

        @Given("^I Add a new location$")
        public void i_Add_a_new_location () throws Throwable {
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.name("reference")).sendKeys(prop.getProperty("Description"));
        driver.findElement(By.name("description")).sendKeys(prop.getProperty("Description"));
        driver.findElement(By.name("saveButton")).click();


    }


        @Given("^I logged in Staging Backoffice$")
        public void i_logged_in_Staging_Backoffice () throws Throwable {
        driver.get(prop.getProperty("StagingBO"));
        driver.findElement(By.name("username")).sendKeys(prop.getProperty("UserName"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
        driver.findElement(By.id("logon_submit")).click();


    }

        @Given("^I choose head office$")
        public void i_choose_head_office () throws Throwable {

        driver.findElement(By.id("form_search")).sendKeys("M001");
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();

    }

        @Given("^I select GM Promotion option Promotion maintenance option$")
        public void i_select_GM_Promotion_option_Promotion_maintenance_option () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"divoCMenu0_1\"]")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[1]")).click();

        //Promotion maintanence
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys("GM PROMOTIONS");
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        }

        @Given("^I select a Campaign in Promotion maintenance option$")
        public void i_select_a_Campaign_in_Promotion_maintenance_option () throws Throwable {
            driver.findElement(By.xpath("//*[@id=\"divoCMenu0_1\"]")).click();
            driver.findElement(By.xpath("/html/body/div[4]/div[1]")).click();

            //Promotion maintanence
            driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
            Thread.sleep(2000);
            driver.findElement(By.name("searchButton")).click();
            driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        }

        @When("^I extract a promotion$")
        public void i_extract_a_promotion () throws Throwable {

        driver.findElement(By.linkText("Last Amended")).click();
        driver.findElement(By.linkText("Last Amended")).click();
        driver.findElement(By.id("excelExtractButton")).click();
        Alert Extract = driver.switchTo().alert();
        Extract.accept();

        WebDriverWait What = new WebDriverWait(driver, 20);
        What.until(ExpectedConditions.alertIsPresent());

        Alert Capture = driver.switchTo().alert();
        String ExtractID = Capture.getText().substring(31, 40).trim();
        System.out.println("Promotion Extracted from SBO is " + "  " + ExtractID);
        String validate = "PR" + ExtractID + ".xml";
        Thread.sleep(5000);

        File tempFile = new File("\\\\mshsrmnsukc1848\\BackOffice_Reports\\PromoExcelExtract\\" + validate + "");
        if (tempFile.exists()) {
            System.out.println("FileisPresent");
        } else {
            System.out.println("Nofile");
        }
//        driver.quit();


    }

        @Then("^I confirmed promotion extracted$")
        public void i_confirmed_promotion_extracted () throws Throwable {


    }

        @When("^I extract clashing report for promotion$")
        public void i_extract_clashing_report_for_promotion () throws Throwable {

        driver.findElement(By.linkText("Last Amended")).click();
        driver.findElement(By.linkText("Last Amended")).click();
        driver.findElement(By.id("clashingPromotionExcelExtractButton")).click();

        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String Parent = it.next();
        String Child = it.next();

        driver.switchTo().window(Child);

        /*WebDriverWait What = new WebDriverWait(driver, 5);
        What.until(ExpectedConditions.);*/


        String CR = driver.getCurrentUrl();
        System.out.println(CR);


    }

        @Given("^I select Promotion extract  option Promotion maintenance option$")
        public void i_select_Promotion_exrtact_option_Promotion_maintenance_option () throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"divoCMenu0_1\"]")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[6]")).click();
        String parentWinHandle = driver.getWindowHandle();
        System.out.println("Parent window handle: " + parentWinHandle);
        // Locate 'Click to open a new browser window!' button using id
//        WebElement newWindowBtn = driver.findElement(By.id("win1"));
        // Click the button to open a new window
        //   newWindowBtn.click();
        // Get the window handles of all open windows
        Set<String> winHandles = driver.getWindowHandles();
        // Loop through all handles
        for (String handle : winHandles) {
            if (!handle.equals(parentWinHandle)) {
                driver.switchTo().window(handle);
                // Thread.sleep(1000);
                Thread.sleep(5000);
            }
        }
    }


        @When("^I extract a GM  promotion$")
        public void i_extract_a_GM_promotion () throws Throwable {
        /*Set<String>ids=driver.getWindowHandles();
        System.out.println(ids);
        Iterator<String>it=ids.iterator();
        String Parent=it.next();
        String Child=it.next();

        driver.switchTo().window(Child);*/
        String CR = driver.getCurrentUrl();
        System.out.println("CR" + CR);
        WebElement SelectO = driver.findElement(By.xpath("//*[@id=\"tableContainer\"]/table/tbody/tr/td"));
        SelectO.click();
        /*if (SelectO.isDisplayed()) {
            SelectO.click();
        }
        else {
            System.out.println("TRY");
        }*/

        driver.findElement(By.id("extract")).click();
        WebDriverWait What = new WebDriverWait(driver, 5);
        What.until(ExpectedConditions.alertIsPresent());

    }


        @Then("^I confirmed promotion report generated$")
        public void i_confirmed_promotion_report_generated () throws Throwable {

    }


        @Given("^I choose Privilege Maintenance option$")
        public void i_choose_Privilege_Maintenance_option () throws Throwable {

        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover
        WebElement MAINTENANCE = driver.findElement(By.id("divoCMenu0_0"));
        //Mouse hover menuOption 'Music'
        actions.moveToElement(MAINTENANCE).perform();
        driver.findElement(By.xpath("/html/body/div[4]/div[13]")).click();

    }

        @When("^I remove item refund Privilege for advisor$")
        public void i_remove_item_refund_Privilege_for_advisor () throws Throwable {
        driver.findElement(By.id("form_searchText")).sendKeys("Item refund");
        driver.findElement(By.id("form_searchType0")).click();
        driver.findElement(By.name("searchButton")).click();

        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]")));

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]")).click();

        driver.findElement(By.id("form_searchText")).sendKeys("Advisor");
        driver.findElement(By.id("form_searchType0")).click();

        WebElement PVR = driver.findElement(By.xpath("//*[@id=\"accessPointRoleLinks\"]/tbody/tr/td[5]/input[1]"));
        if (PVR.isSelected())

        {
            PVR.click();
        }
        else {
            Batchneeded=false;
        }

    }

        @When("^I save the Privilege changes$")
        public void i_save_the_Privilege_changes () throws Throwable
        {// Write code here that turns the phrase above into concrete actions
            driver.findElement(By.name("saveButton")).click();
        }

        @When("^I add item refund Privilege for advisor$")
        public void i_add_item_refund_Privilege_for_advisor () throws Throwable {
        driver.findElement(By.id("form_searchText")).sendKeys("Item refund");
        driver.findElement(By.id("form_searchType0")).click();
        driver.findElement(By.name("searchButton")).click();

        WebDriverWait User = new WebDriverWait(driver, 6);
        User.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]")));

        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[4]")).click();

        driver.findElement(By.id("form_searchText")).sendKeys("Advisor");
        driver.findElement(By.id("form_searchType0")).click();

        WebElement PVR = driver.findElement(By.xpath("//*[@id=\"accessPointRoleLinks\"]/tbody/tr/td[5]/input[1]"));
        boolean RR = PVR.isSelected();

        if (RR == false)


        {
            Thread.sleep(10000);
            PVR.click();

        }

    }

        @Given("^I choose add button in GM promotions campaign$")
        public void i_choose_add_button_in_GM_promotions_campaign () throws Throwable {
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
    }

        @Given("^I choose add button in the selected Campaign$")
        public void I_choose_add_button_in_the_selected_Campaign () throws Throwable {
            driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        }

        @Given("^I entered the details for Buy x and get percentage off promotion$")
        public void I_entered_the_details_for_Buy_x_and_get_percentage_off_promotion () throws Throwable {
            //Promotion detail
            driver.findElement(By.id("name")).sendKeys(prop.getProperty("PromotionName1"));
            driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("F01C");
            driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
            driver.findElement(By.id("form_promotionDisplayer_promotion_includePriceCorrected")).click();
            driver.findElement(By.id("form_promotionDisplayer_promotion_includeReduced")).click();
            driver.findElement(By.id("upSellingMessageCheckBox")).click();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            String date = sdf.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 3);
            String enddate = sdf.format(cal.getTime());

            driver.findElement(By.id("form_startDate")).sendKeys(date);
            driver.findElement(By.id("form_endDate")).sendKeys(enddate);
            String PromotionId1 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
            System.out.println("Promotionid is "+ PromotionId1 );
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/Buyxget%offdetails.jpeg"));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

            //Promotion Rules
            driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue1"));
            driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("food");
            driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"benefit\"]")).sendKeys(prop.getProperty("PercentageOffValue1"));
            Select Percentage = new Select(driver.findElement(By.id("selectedBenefitOption")));
            Percentage.selectByVisibleText("% off all items in this promotion");
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            File source1 = ts1.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source1, new File("./target/cucumber-reports/PSB-543/Buyxget%offRule.jpeg"));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        }

        @Given("^I entered the details for Buy x and get percentage off with Single Redeemable Mobile Coupon promotion$")
        public void I_entered_the_details_for_Buy_x_and_get_percentage_off_with_Single_Redeemable_Mobile_Coupon_promotion () throws Throwable {
        //Promotion detail
        driver.findElement(By.id("name")).sendKeys(prop.getProperty("PromotionName4"));
        driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("F01C");
        driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includePriceCorrected")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includeReduced")).click();
        driver.findElement(By.id("upSellingMessageCheckBox")).click();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 3);
        String enddate = sdf.format(cal.getTime());

        driver.findElement(By.id("form_startDate")).sendKeys(date);
        driver.findElement(By.id("form_endDate")).sendKeys(enddate);
        String PromotionId4 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println("Promotionid is "+ PromotionId4 );
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/MobileCouponSRDetails.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

        //Promotion Rules
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue4"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("food");
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"benefit\"]")).sendKeys(prop.getProperty("PercentageOffValue2"));
        Select Percentage = new Select(driver.findElement(By.id("selectedBenefitOption")));
        Percentage.selectByVisibleText("% off all items in this promotion");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue4.1"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("Mobile Coupon");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            File source1 = ts1.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source1, new File("./target/cucumber-reports/PSB-543/MobileCouponSRRules.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
    }

        @Given("^I entered the details for Buy x and get y free with Multi Redeemable Mobile Coupon promotion$")
        public void I_entered_the_details_for_Buy_x_and_get_y_free_with_Multi_Redeemable_Mobile_Coupon_promotion () throws Throwable {
        //Promotion detail
        driver.findElement(By.id("name")).sendKeys(prop.getProperty("PromotionName5"));
        driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("F01C");
        driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includePriceCorrected")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includeReduced")).click();
        driver.findElement(By.id("upSellingMessageCheckBox")).click();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 3);
        String enddate = sdf.format(cal.getTime());

        driver.findElement(By.id("form_startDate")).sendKeys(date);
        driver.findElement(By.id("form_endDate")).sendKeys(enddate);
        String PromotionId5 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println("Promotionid is "+ PromotionId5 );
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/MobileCouponMRDetails.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

        //Promotion Rules
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue5"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("food");
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"benefit\"]")).sendKeys(prop.getProperty("GetValue1"));
        Select Percentage = new Select(driver.findElement(By.id("selectedBenefitOption")));
        Percentage.selectByVisibleText("free item/s from above target items");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue5.1"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("Mobile Coupon");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            File source1 = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/MobileCouponMRRules.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
    }

        @Given("^I entered the details for Buy x and get y free promotion$")
        public void I_entered_the_details_for_Buy_x_and_get_y_free_promotion () throws Throwable {
        //Promotion detail
        driver.findElement(By.id("name")).sendKeys(prop.getProperty("PromotionName2"));
        driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("F01C");
        driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includePriceCorrected")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includeReduced")).click();
        driver.findElement(By.id("upSellingMessageCheckBox")).click();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 3);
        String enddate = sdf.format(cal.getTime());

        driver.findElement(By.id("form_startDate")).sendKeys(date);
        driver.findElement(By.id("form_endDate")).sendKeys(enddate);
        String PromotionId2 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println("Promotionid is "+ PromotionId2 );
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/Buyxgetyfreedetails.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

        //Promotion Rules
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue2"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("food items");
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"benefit\"]")).sendKeys(prop.getProperty("GetValue"));
        Select Percentage = new Select(driver.findElement(By.id("selectedBenefitOption")));
        Percentage.selectByVisibleText("free item/s from above target items");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            File source1 = ts1.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source1, new File("./target/cucumber-reports/PSB-543/BuyxgetyfreeRule.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
    }

    @Given("^I entered the details for Buy x and get £y off promotion$")
    public void I_entered_the_details_for_Buy_x_and_get_£y_off_promotion () throws Throwable {
        //Promotion detail
        driver.findElement(By.id("name")).sendKeys(prop.getProperty("PromotionName3"));
        driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("F01C");
        driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includePriceCorrected")).click();
        driver.findElement(By.id("form_promotionDisplayer_promotion_includeReduced")).click();
        driver.findElement(By.id("upSellingMessageCheckBox")).click();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 3);
        String enddate = sdf.format(cal.getTime());

        driver.findElement(By.id("form_startDate")).sendKeys(date);
        driver.findElement(By.id("form_endDate")).sendKeys(enddate);
        String PromotionId3 = driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println("Promotionid is "+ PromotionId3 );
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/Buyxandget£yoffdetails.jpeg"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

        //Promotion Rules
        driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys(prop.getProperty("BuyValue3"));
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("food items");
        driver.findElement(By.xpath("//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"benefit\"]")).sendKeys(prop.getProperty("Get£yoffvalue"));
        Select Percentage = new Select(driver.findElement(By.id("selectedBenefitOption")));
        Percentage.selectByVisibleText("off transaction");
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        TakesScreenshot ts1 = (TakesScreenshot) driver;
        File source1 = ts1.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source1, new File("./target/cucumber-reports/PSB-543/Buyxandget£yoffRule.jpeg"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
    }

        @Given("^I added a product for Buy x and get percentage off$")
        public void I_added_a_product_for_Buy_x_and_get_percentage_off () throws Throwable {
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("productforBuyxandgetpercentageoff"));
            driver.findElement(By.id("addButton")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/ProductaddedforBuyxget%off.jpeg"));
            Thread.sleep(2000);
            //next button
            //driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

        }

        @Given("^I added product with Single Redeemable Mobile Coupon UPC for Buy x and get percentage off promotion$")
        public void I_added_product_with_Single_Redeemable_Mobile_Coupon_UPC_for_Buy_x_and_get_percentage_off_promotion () throws Throwable {
            //Rule1
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("productforBuyxandgetpercentageoffwithSR"));
            driver.findElement(By.id("addButton")).click();
            Thread.sleep(5000);
            driver.findElement(By.linkText("Rule 2")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/MobileCouponSRProducts.jpeg"));
            Thread.sleep(2000);
            //Rule2
            driver.findElement(By.id("entry")).sendKeys(prop.getProperty("VZSRMobileCouponUPC"));
            driver.findElement(By.id("addButton")).click();

    }

        @Given("^I added product with Multi Redeemable Mobile Coupon UPC for Buy x and get y free promotion$")
        public void I_added_product_with_Multi_Redeemable_Mobile_Coupon_UPC_for_Buy_x_and_get_y_free_promotion () throws Throwable {
        //Rule1
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("productforBuyxandgetyfreewithMR"));
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(5000);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/MobileCouponMRProducts.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.linkText("Rule 2")).click();
        //Rule2
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("VZMRMobileCouponUPC"));
        driver.findElement(By.id("addButton")).click();

    }

        @Given("^I added a product for Buy x and get y free$")
        public void I_added_a_product_for_Buy_x_and_get_y_free () throws Throwable {
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("productforBuyxandgetyfree"));
        driver.findElement(By.id("addButton")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/Buyxgetyfreeproduct.jpeg"));
            Thread.sleep(2000);
        //next button
        //driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

    }

        @Given("^I added a product for Buy x and get £y off$")
        public void I_added_a_product_for_Buy_x_and_get_£y_off () throws Throwable {
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("productforBuyxandget£yoff"));
        driver.findElement(By.id("addButton")).click();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/productforBuyxandget£yoff.jpeg"));
            Thread.sleep(2000);
        //next button
        //driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();

    }

        @Given("^I add a promotion details$")
        public void i_add_a_promotion_details () throws Throwable {

        //Promotion detail

        driver.findElement(By.id("name")).sendKeys("Buy2 Get 1 Free");
        driver.findElement(By.xpath("/*//*[@id=\"productGroupCode\"]")).sendKeys("T01");
        driver.findElement(By.id("form_promotion_maxTriggerQty")).sendKeys("2");
        driver.findElement(By.id("form_promotionDisplayer_promotion_maximiseBenefit")).click();
        //Date Currentdate = new Date();
        //String strDateFormat = "dd/MM/yyyy";
        //SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        //String date = objSDF.format(Currentdate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        String enddate = sdf.format(cal.getTime());


        driver.findElement(By.id("form_startDate")).sendKeys(date);
        driver.findElement(By.id("form_endDate")).sendKeys(enddate);
        driver.findElement(By.xpath("/*//*[@id=\"upSellingMessageCheckBox\"]")).click();
        Promotionid = driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/fieldset[1]/table/tbody/tr[3]/td[1]")).getText();
        System.out.println("Promotionid is " + Promotionid);
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();


        //Promotion Rules


        driver.findElement(By.xpath("/*//*[@id=\"selectedBuyOrSpend\"]")).click();
        Select Buy = new Select(driver.findElement(By.id("selectedBuyOrSpend")));
        Buy.selectByVisibleText("Buy");

        driver.findElement(By.xpath("/*//*[@id=\"target\"]")).sendKeys("2");
        driver.findElement(By.xpath("/*//*[@id=\"form_ruleDisplayer_name\"]")).sendKeys("GM Items");
        driver.findElement(By.xpath("/*//*[@id=\"form_ruleDisplayer_hasBenefit\"]")).click();

        driver.findElement(By.xpath("/*//*[@id=\"selectedPayOrGet\"]")).click();
        Select Get = new Select(driver.findElement(By.id("selectedPayOrGet")));
        Get.selectByVisibleText("Get");

        driver.findElement(By.xpath("/*//*[@id=\"selectedBenefitOption\"]")).click();
        Select Benefit = new Select(driver.findElement(By.id("selectedBenefitOption")));
        Benefit.selectByVisibleText("free item/s from above target items");
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input")).click();


    }

        @Given("^I add promotion members using Bulk upload$")
        public void i_add_promotion_members_using_Bulk_upload () throws Throwable {
        driver.findElement(By.id("bulkUploadButton")).click();
        String parentWinHandle = driver.getWindowHandle();
        System.out.println("Parent window handle: " + parentWinHandle);
        Set<String> winHandles = driver.getWindowHandles();
        // Loop through all handles
        for (String handle : winHandles) {
            if (!handle.equals(parentWinHandle)) {
                driver.switchTo().window(handle);
                // Thread.sleep(1000);
                Thread.sleep(5000);
            }
        }


        String Region = "PromotionBulkUploadData_!TestFile.xls";
        while (a == 0) {
            for (int i = 1; i <= 10; i++) {
                String msgID = driver.findElement(By.xpath("//*[@id=\"row\"]/tbody/tr[" + i + "]/td[1]")).getText();
                System.out.println(msgID);
                if (msgID.contains(Region)) {
                    driver.findElement(By.xpath("//*[@id=\"row\"]/tbody/tr[" + i + "]/td[2]/input")).click();
                    driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/input")).click();
                    WebDriverWait What = new WebDriverWait(driver, 2000);
                    What.until(ExpectedConditions.alertIsPresent());
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                    a = 1;
                    break;
                }

            }
            if (a == 0) {
                driver.findElement(By.linkText("Next")).click();

            }

        }
            driver.switchTo().window(parentWinHandle);
        Thread.sleep(5000);

    }

        @Given("^I add stores for the promotion$")
        public void i_add_stores_for_the_promotion () throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();
        driver.findElement(By.xpath(" /*//*[@id=\"nextButton\"]")).click();
        //staging.findElement(By.xpath(" /[@id=\"addButton\"]")).click();
        //Thread.sleep(4000);
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.xpath("/*//*[@id=\"entry\"]")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.xpath("/*//*[@id=\"addButton\"]")).click();

    }


        @Given("^I save the promotion$")
        public void i_save_the_promotion () throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input[1]")).click();

        driver.findElement(By.xpath("/*//*[@id=\"form\"]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr/td[3]/input")).click();

            driver.switchTo().alert().accept();
            Thread.sleep(2000);
            driver.findElement(By.id("cancelButton")).click();
            driver.switchTo().alert().accept();

    }

        @When("^I change promotion to a active status$")
        public void I_change_promotion_to_a_active_status () throws Throwable {
        driver.findElement(By.id("divoCMenu0_1")).click();
        //staging.findElement(By.id("divoCMenu0_1"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();

        //compagin
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys("GM PROMOTIONS");
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();

        //Promotion
        driver.findElement(By.id("form_searchPromCode")).sendKeys(Promotionid);
        driver.findElement(By.id("form_searchPromDescription")).sendKeys("Buy2 Get 1 Free");


        driver.findElement(By.xpath("/*//*[@id=\"form_selectedStatus\"]")).click();
        Select Status = new Select(driver.findElement(By.id("form_selectedStatus")));
        Status.selectByVisibleText("In Preparation");
        driver.findElement(By.id("form_selectedStatus")).click();
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("saveButton")).click();


        Alert Status1 = driver.switchTo().alert();
        /*String alertText = Status1.getText();


        String[] batchRef2 = alertText.split(" : ")[2].split("\n");
        String Valiadtion2= batchRef2[0].substring(0,batchRef2[0].length()-1);
        System.out.println("Valiadtion "+Valiadtion2);*/
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys("GM PROMOTIONS");
        Thread.sleep(5000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();

        //Promotion
        driver.findElement(By.id("form_searchPromCode")).sendKeys(Promotionid);
        driver.findElement(By.id("form_searchPromDescription")).sendKeys("Buy2 Get 1 Free");
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.id("nextButton")).click();
        driver.findElement(By.id("saveButton")).click();
    }

        @When("^I change Buy x and get percentage off promotion to a active status$")
        public void I_change_Buy_x_and_get_percentage_off_promotion_to_a_active_status () throws Throwable {
            driver.findElement(By.id("divoCMenu0_1")).click();
            //staging.findElement(By.id("divoCMenu0_1"));
            driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
            //Campaign selection
            driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
            Thread.sleep(2000);
            driver.findElement(By.name("searchButton")).click();
            driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
            //Promotion to InBSTest
            driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName1"));
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
            driver.findElement(By.id("saveButton")).click();
            driver.switchTo().alert().accept();
            //Campaign selection
            driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
            Thread.sleep(2000);
            driver.findElement(By.name("searchButton")).click();
            driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
            //Promotion to Active
            driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName1"));
            driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
            driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
            driver.findElement(By.id("saveButton")).click();
        }


        @When("^I change Buy x and get percentage off promotion with Single Redeemable Mobile Coupon to a active status$")
        public void I_change_Buy_x_and_get_percentage_off_promotion_with_Single_Redeemable_Mobile_Coupon_to_a_active_status () throws Throwable {
        driver.findElement(By.id("divoCMenu0_1")).click();
        //staging.findElement(By.id("divoCMenu0_1"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to InBSTest
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName4"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
        driver.switchTo().alert().accept();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to Active
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName4"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

        @When("^I change Buy x and get y free promotion with Multi Redeemable Mobile Coupon to a active status$")
        public void I_change_Buy_x_and_get_y_free_promotion_with_Multi_Redeemable_Mobile_Coupon_to_a_active_status () throws Throwable {
        driver.findElement(By.id("divoCMenu0_1")).click();
        //staging.findElement(By.id("divoCMenu0_1"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to InBSTest
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName5"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
        driver.switchTo().alert().accept();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to Active
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName5"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

        @When("^I change Buy x and get y free promotion to a active status$")
        public void I_change_Buy_x_and_get_y_free_promotion_to_a_active_status () throws Throwable {
        driver.findElement(By.id("divoCMenu0_1")).click();
        //staging.findElement(By.id("divoCMenu0_1"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to InBSTest
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName2"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
        driver.switchTo().alert().accept();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to Active
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName2"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

    @When("^I change Buy x and get £y off to a active status$")
    public void I_change_Buy_x_and_get_£y_off_to_a_active_status () throws Throwable {
        driver.findElement(By.id("divoCMenu0_1")).click();
        //staging.findElement(By.id("divoCMenu0_1"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to InBSTest
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName3"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
        driver.switchTo().alert().accept();
        //Campaign selection
        driver.findElement(By.xpath("/*//*[@id=\"form_searchText\"]")).sendKeys(prop.getProperty("CampaignName"));
        Thread.sleep(2000);
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("/*//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        //Promotion to Active
        driver.findElement(By.xpath("//*[@id=\"form_searchPromDescription\"]")).sendKeys(prop.getProperty("PromotionName3"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr[18]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[8]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"nextButton\"]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

        @Then("^I launch the staging dds$")
        public void I_launch_the_staging_dds () throws Throwable {
        /*ystem.setProperty("webdriver.chrome.driver", prop.getProperty("Chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();*/
        driver.get(prop.getProperty("StagingDDS"));


    }

        @Then("^I validated the batch reference number is audited in the staging dds$")
        public void i_validated_the_batch_reference_number_is_audited_in_the_staging_dds () throws Throwable {
       /* driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/a")).click();
        if (driver.getPageSource().contains(prop.getProperty("CenterVersion"))) {
            driver.navigate().back();
            Thread.sleep(2000);
        } else {
            org.junit.Assert.fail();
        }*/
        driver.findElement(By.name("searchBatch")).click();
        Thread.sleep(2000);
        driver.getPageSource();
        for (int i = 1; i <= 20; i++) {
            String msgID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[2]")).getText();
            if (msgID.contains(batchRefNumber)) {

                /*driver.close();
                driver.quit();*/
                break;
            } else {

                Assert.fail();
                /*driver.close();
                driver.quit();*/
            }

        }
    }

        @Given("^I create a new Customer survey message$")
        public void i_create_a_new_Customer_survey_message () throws Throwable {
        Select drpmessage = new Select(driver.findElement(By.id("form_typeId")));
        drpmessage.selectByVisibleText("Customer Survey");
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("messageName")).sendKeys(prop.getProperty("CSAT_name"));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        driver.findElement(By.name("startDate")).sendKeys(date1);
        driver.findElement(By.name("endDate")).sendKeys(date1);


        driver.findElement((By.name("transactionCount"))).sendKeys(prop.getProperty("Transaction_Count"));
        driver.findElement(By.name("surveyPriority")).sendKeys(prop.getProperty("Survey_priority"));
        driver.findElement(By.xpath("//*[@id=\"receiptDivId1\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_1"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.name("addLineButton")).click();
        // driver.findElement(By.xpath("//*[@id=\"receiptDivId3\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_2") );
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId5\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_3"));
        driver.findElement(By.xpath("//*[@id=\"receiptDivId5\"]/table/tbody/tr/td[5]/input")).click();
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId7\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_4"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId8\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_5"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId9\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_6"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId10\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_7"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId11\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_8"));
        driver.findElement(By.name("addLineButton")).click();
        driver.findElement(By.xpath("//*[@id=\"receiptDivId12\"]/table/tbody/tr/td[3]/textarea")).sendKeys(prop.getProperty("CSAT_9"));

    }

        @Given("^I set a customer assistant message$")
        public void i_set_a_customer_assistant_message () throws Throwable {
        driver.findElement((By.name("customerAssistantMessageText"))).sendKeys(prop.getProperty("CA_message"));

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/cucumber-reports/PSB-543/CustomerSurvey.jpeg"));
            Thread.sleep(2000);
        driver.findElement(By.name("nextButton")).click();
            WebElement Personality = driver.findElement(By.id("form_name"));
            Select select = new Select(Personality);
            select.selectByVisibleText("SCOT");
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("addButton")).click();
        driver.findElement(By.id("nextButton")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[2]")).click();
        driver.findElement(By.id("entry")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.id("addButton")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("saveButton")).click();
        driver.findElement(By.name("saveButton")).click();

    }

        @And("^the message is created successfully$")
        public void the_message_is_created_successfully () throws Throwable {
        Select drpmessage = new Select(driver.findElement(By.name("messageTypeId")));
        drpmessage.selectByVisibleText("Customer Survey");
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[3]")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.name("Set To Live")).click();


        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();


    }

        @Given("^I get deliveryID corresponding to BatchRefNumber$")
        public void i_get_deliveryID_corresponding_to_BatchRefNumber () throws Throwable {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\p9137006\\Documents\\Center_Till_Automation\\ReferanceNumber_test.txt"));
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                //  System.out.println("Line is "+line);
            }
            reader.close();
        } catch (IOException e) {

        }

        driver.findElement(By.name("batchDays")).sendKeys("3");
        driver.findElement(By.name("searchBatch")).click();
        String line1 = "20190909131025603";
        // driver.findElement(By.name("searchBatch"))
        //  String batches = driver.findElement(By.xpath("/html/body/div/form")).getText().trim().split("");
        b = 0;
        do {
            for (int i = 1; i <= 25; i++) {
                String batch = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[2]")).getText();
                if (batch.contains(line1)) {
                    Delivery = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[6]")).getText();

                    System.out.println(Delivery);
                    b = 1;
                    break;
                }
            }
            if (b == 0) {
                driver.findElement(By.name("forward")).click();
            }
        } while (b == 0);
    }

        @Given("^I search for a till$")
        public void i_search_for_a_till () throws Throwable {

        driver.findElement(By.linkText("Return to Main Reporting Index")).click();
        driver.findElement(By.name("device")).sendKeys("786");
        driver.findElement(By.name("searchDevice")).click();
        driver.findElement(By.linkText("show All")).click();

    }

        @When("^I get status for the delta$")
        public void i_get_status_for_the_delta () throws Throwable {


        c = 0;
        do {
            for (int i = 1; i <= 25; i++) {
                System.out.println("Inside the loop");
                String DeliveryID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[5]")).getText().trim();
                System.out.println("Come" + DeliveryID + Delivery);
                String Hardware = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[2]")).getText().trim();
                System.out.println("To" + Hardware + "mshrdmnsukc0001");
                if ((DeliveryID.contains(Delivery)) && (Hardware.contains("mshrdmnsukc0001"))) {
                    String Status = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[" + i + "]/td[7]/font")).getText();
                    System.out.println(Status);
                    c = 1;
                    Assert.assertEquals(Status, "Successfully Received and Applied");
                    break;
                }
            }
            if (c == 0) {
                driver.findElement(By.name("forward")).click();
            }
        } while (c == 0);
    }


        @Then("^I close the browser$")
        public void i_close_the_browser () throws Throwable {

    }

        @Given("^I Add a new location(\\d+)$")
        public void i_Add_a_new_location ( int arg1) throws Throwable {

    }

    @When("^I Add new message$")
    public void i_Add_new_message() throws Throwable {

    }

    @Given("^I Amend an existing device entry$")
    public void i_Amend_an_existing_device_entry() throws Throwable {
        driver.findElement(By.name("deviceSearch")).sendKeys("001");
        driver.findElement(By.name("organisationSearch")).sendKeys(prop.getProperty("StoreCode"));
        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//*[@id=\"displayTable\"]/tbody/tr/td[2]")).click();
        driver.findElement(By.name("device.name")).clear();
        String firstName = BO_Steps.generateRandomName(9); // 9 Characters long
        driver.findElement(By.name("device.name")).sendKeys(firstName);
        driver.findElement(By.name("saveButton")).click();
    }
}
