package TradingBO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static TradingBO.BO_Steps.driver;

public class OWB_Steps {


    //public static WebDriver driver = null;
    public static Properties prop;
    public static WebDriverWait waitVar = new WebDriverWait(driver,30);
   // private WebDriver driver1;

    public OWB_Steps() {
        prop = prop = new Properties();
        try {
            FileInputStream fp = new FileInputStream("C:\\\\Users\\\\p9137006\\\\Documents\\\\Center_Till_Automation\\\\config.properties");
            System.out.println("File Path" + fp);
            prop.load(fp);
        } catch (IOException ex) {

        }
    }

    /*@Given("^the chrome driver is initiated$")
    public void the_chrome_driver_is_initiatedO() throws Throwable {
        System.setProperty("webdriver.chrome.driver", prop.getProperty("Chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitVar = new WebDriverWait(driver, 4000);




    }*/
    @Given("^I launch the OWB$")
    public void i_launch_the_OWB() throws Throwable {
        driver.get(prop.getProperty("url"));// Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Given("^I login to OWB$")
    public void i_login_to_OWB() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[2]/input")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[2]/input")).sendKeys(prop.getProperty("password"));
        /*String OWB = prop.getProperty("OWB");
        System.out.println("OWB URL is " + OWB);*/

        driver.findElement(By.name("submit")).click();
        Thread.sleep(5000);

String OWB=prop.getProperty("Environment");

        System.out.println("OWB URL is " + OWB);

//        driver.findElement(By.name("submit")).click();

        /*String owbprompt = "Component Initialize Error";
        if (driver.getPageSource().contains(owbprompt) && OWB.equals("PS1")) {
            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/button/span")).click();
        } else {
            if (OWB.equals("FT1"))

                for (int i = 11; i >= 5; i -= 2) {


                    System.out.println(i);
                    Thread.sleep(1000);
                    driver.findElement(By.xpath("/html/body/div[" + i + "]/div[2]/div/button/span")).click();

                }*/
        }






    @When("^I keyin the StoreID$")
    public void i_keyin_the_StoreID() throws Throwable {
        driver.findElement(By.id("quickfind_storeCode")).sendKeys(prop.getProperty("store"));
    }

    @When("^I keyin the TillID$")
    public void i_keyin_the_TillID() throws Throwable {
        driver.findElement(By.id("quickfind_tillShortDescOrHardwareID")).sendKeys(prop.getProperty("OWB_Till"));

    }

    @When("^I click on search button$")
    public void i_click_on_search_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"gotoTillButton\"]/span[1]")).click();
        Thread.sleep(3000);
    }

    @Then("^the till status page appears$")
    public void the_till_status_page_appears() throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[1]"));
//        driver.findElement(By.linkText(prop.getProperty("store")));
        driver.findElement(By.linkText("Update till status")).click();
        Thread.sleep(15000);
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoTillForm")));
        String str = driver.findElement(By.xpath("//*[@id=\"dataTableSpacing\"]/tbody/tr[10]/td[2]")).getText();
        System.out.println(str);
        if (str.contains("instant")) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/PSB-846/Till status.png)"));
        }
    }

    @When("^I click on reboot$")
    public void i_click_on_reboot() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"rightCol\"]/table/tbody/tr[9]/td[2]/a")).click();
    }

    @Then("^confirmation prompt appears$")
    public void confirmation_prompt_appears() throws Throwable {
        driver.findElement(By.id("queryDialog_title"));

    }

    @When("^I click on yes button$")
    public void i_click_on_yes_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"dijit_layout_ContentPane_1\"]/span[1]")).click();
    }

    @Then("^the request is sent successfully$")
    public void the_request_is_sent_successfully() throws Throwable {
        String statustext_actual, statustext_expected;
        Thread.sleep(7000);
        statustext_actual=driver.findElement(By.id("statusTextIntervention")).getText();
        statustext_expected="Reboot request successfully sent to till (monitor the till for actual status of reboot).";

        Assert.assertEquals(statustext_actual,statustext_expected);
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-163,PSB-79/OWBReboot.png)"));

    }

    @When("^I check on the events$")
    public void i_check_on_the_events() throws Throwable {
        driver.findElement(By.id("showEvents")).click();
    }

    @Then("^the shutdown event is audited$")
    public void the_shutdown_event_is_audited() throws Throwable {
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//*[@id=\"dojox_grid_DataGrid_0-page-0\"]/div[1]/table/tbody/tr/td[5]"));
        String event_Actual, event_Expected;
        event_Actual= driver.findElement(By.xpath("//*[@id=\"dojox_grid_DataGrid_0-page-0\"]/div[1]/table/tbody/tr/td[5]")).getText();// Write code here that turns the phrase above into concrete actions
        System.out.println (event_Actual);
        event_Expected ="Application end: POS shutdown";
        Assert.assertEquals(event_Actual,event_Expected);
        driver.findElement(By.xpath("//*[@id=\"crumbtrail\"]/a")) .click();
    }
    @When("^I click on Estate status link$")
    public void i_click_on_Estate_status_link() throws Throwable {
        Actions actions = new Actions(driver);
        WebElement target = driver.findElement(By.xpath("//*[@id=\"mymenu\"]/li[1]/a"));
        actions.moveToElement(target).perform();
        driver.findElement(By.linkText("Estate Status") ).click();

    }

    @Then("^estate status page appears$")
    public void estate_status_page_appears() throws Throwable {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[3]/div"));

    }

    @When("^I click on StoreID$")
    public void i_click_on_StoreID() throws Throwable {
        String storename= prop.getProperty("storeName");
        driver.findElement(By.cssSelector("[title^='"+storename+"']")).click();

    }

    @Then("^Store status page appears$")
    public void store_status_page_appears() throws Throwable {
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-762/Store.png)"));
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[2]/p[1]/span"));


    }

    @When("^I click on TillID$")
    public void i_click_on_TillID() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"tillListGrid\"]/div[1]/div/div/div/table/tbody/tr/th[6]")).click();
        driver.findElement(By.xpath("//*[@id=\"tillListGrid-page-0\"]/div[1]/table/tbody/tr/td[2]")).click();
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-762/Till.png)"));


    }
    @When("^I click on data distribution page$")
    public void i_click_on_data_distribution_page() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[3]/span[1]")).click();


    }

    @Then("^batch processing page appears$")
    public void batch_processing_page_appears() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"crumbtrail\"]/span"));

    }

    @When("^I select the date$")
    public void i_select_the_date() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"startTime\"]")).click();
        for (int i=0; i<5; i++)
        {
            driver.findElement(By.xpath("//*[@id=\"startTime\"]")).sendKeys(Keys.BACK_SPACE);
        }
        driver.findElement(By.id("startTime")).click();
        driver.findElement(By.id("startTime")).sendKeys(prop.getProperty("time"));
        driver.findElement(By.xpath("//*[@id=\"dijit__TimePicker_0\"]/div[2]/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[2]/button")).click();

    }

    @Then("^all the batch details are listed with current status$")
    public void all_the_batch_details_are_listed_with_current_status() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"gridDD\"]/div[1]/div/div/div/table/tbody/tr/th[2]/div"));
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-407,PSB-353/Batch processing.png)"));

    }
    @When("^I click on cache option$")
    public void i_click_on_cache_option() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"twistie_cacheInformation\"]")) .click();

    }

    @When("^I click on request cache$")
    public void i_click_on_request_cache() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"dataTableSpacing\"]/tbody/tr[1]/td[4]/table/tbody/tr[1]/td/a")).click();

    }

    @Then("^response message is shown$")
    public void response_message_is_shown() throws Throwable {
        String s1 = "Cache requested for till " + prop.getProperty("OWB_Till") + " in store " + prop.getProperty("store") + ": required cache is ready for use and will be sent straight away";
        String s2 = driver.findElement(By.xpath("//*[@id=\"strutsStaticMessages\"]/ul/li[2]/span")).getText();
        Assert.assertEquals(s1, s2);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-664/Request cache.png)"));

    }

    @Then("^I check for status layout$")
    public void i_check_for_status_layout() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[5]/div[1]"));
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[4]/div[1]"));
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[3]/div"));
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-34/Store Summary.png)"));


    }
    @Then("^all the parameters listed in the page$")
    public void all_the_parameters_listed_in_the_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.linkText(prop.getProperty("store")));
        driver.findElement(By.linkText("Update till status")).click();
        Thread.sleep(5000);
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.id("gotoTillForm")));
        String str= driver.findElement(By.xpath("//*[@id=\"dataTableSpacing\"]/tbody/tr[10]/td[2]")).getText();
        System.out.println(str);
        if (str.contains("instant")) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/PSB-846/Till status.png)"));


        }
        //throw new PendingException();
    }

    @When("^I click on estate status option$")
    public void i_click_on_estate_status_option() throws Throwable {
        driver.findElement(By.linkText("Estate Status")).click();
    }

    @Then("^it navigates to home page$")
    public void it_navigates_to_home_page() throws Throwable {
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"crumbtrail\"]/span")));
        //String str= driver.findElement(By.xpath("//*[@id=\"dataTableSpacing\"]/tbody/tr[10]/td[2]")).getText();

    }
    @When("^the summary page appears$")
    public void the_summary_page_appears() throws Throwable {
        Thread.sleep(1000);
        String Actualpage=driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[1]")).getText();
        String Expectedpage="Store Status";
        Assert.assertEquals(Expectedpage,Actualpage);

    }

    @Then("^I check for the layout$")
    public void i_check_for_the_layout() throws Throwable {
        String heading=driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[3]/div[1]")).getText();
        String Actualheading="Devices";
        Assert.assertEquals(heading,Actualheading);
        String subheading1=driver.findElement(By.xpath("//*[@id=\"dijit_layout__TabButton_0\"]")).getText();
        String Actualsubheading1="Till List";
        Assert.assertEquals(subheading1,Actualsubheading1);
        Select drpTilltype=new Select(driver.findElement(By.id("tillType")));
        drpTilltype.selectByVisibleText("SCOT");
        driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[2]/div[2]/a")).click();
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pageContent\"]/div[2]/div[1]")));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/PSB-129/Store Summary.png)"));





    }
    @When("^I click on store search button$")
    public void i_click_on_store_search_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"gotoStoreButton\"]/span[1]")).click();
    }
    @Given("^I launch DDS$")
    public void i_launch_DDS() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaisali.devendran\\.m2\\Dependencies\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitVar = new WebDriverWait(driver, 4000);

        System.out.println(prop.getProperty("DDS_URL"));
        driver.get("http://bstradingft1.webapps.marksandspencercate.com:9082/data/reporting.jsp");
        // Write code here that turns the phrase above into concrete actions
        // new PendingException();
    }

    @When("^I request a new cache for a till$")
    public void i_request_a_new_cache_for_a_till() throws Throwable {

        driver.findElement(By.linkText("Return to main Index")).click();
        driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCacheAllTills_param0\"]")).sendKeys(prop.getProperty("storeCache"));
        driver.findElement(By.id("formCacheServiceManagerrequestCacheAllTills_submit")).click();
        String message=driver.findElement(By.xpath("//*[@id=\"dialogResponseParagraph\"]/font/text()")).getText();
        System.out.println(message);

        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^the required prompt is displayed$")
    public void the_required_prompt_is_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
    }


}
