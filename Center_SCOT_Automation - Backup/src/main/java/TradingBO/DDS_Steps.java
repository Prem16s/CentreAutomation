package TradingBO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static TradingBO.BO_Steps.driver;

/**
 * Created by p9137006 on 8/29/2019.
 */
public class DDS_Steps {

    Properties prop = new Properties();
    FileInputStream inp = new FileInputStream("C:\\\\Users\\\\p9137006\\\\Documents\\\\Center_Till_Automation\\\\config.properties");


    public DDS_Steps() throws IOException {
        prop.load(inp);
    }


    @Given("^I launched Trading DDS$")
    public void i_launched_Trading_DDS() throws Throwable {
        driver.get(prop.getProperty("TradingDDS"));
    }

    @Given("^I navigate to main index page$")
    public void i_navigate_to_main_index_page() throws Throwable {
        WebElement elem = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/a"));
        elem.click();

    }

    @When("^I enter Scot number and Store number under Request new Cache a Scot in Cache Service manager$")
    public void i_enter_Scot_number_and_Store_number_under_Request_new_Cache_a_Scot_in_Cache_Service_manager() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCache_param0\"]")).sendKeys(prop.getProperty("Store"));
        driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCache_param1\"]")).sendKeys(prop.getProperty("Till"));
    }

    @When("^I click on New Cache for Scot button$")
    public void i_click_on_New_Cache_for_Till_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCache_submit\"]")).click();

    }

    @Then("^I capture the prompt$")
    public void i_capture_the_prompt() throws Throwable {
        String Message=driver.findElement(By.xpath("//*[@id=\"dialogResponseParagraph\"]/font")).getText();
        System.out.println(Message);
        if(Message.contains(prop.getProperty("Store")))
        {
            System.out.println("Success");
        }
    }

    @Then("^store number is populated in OrgUnitsBeingBuilt under CacheBuilderManager$")
    public void store_number_is_populated_in_OrgUnitsBeingBuilt_under_CacheBuilderManager() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[20]/div[3]/div/button/span")).click();
        driver.navigate().refresh();
        Thread.sleep(5000);
        String store = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[2]")).getText();
        if (store.contains(prop.getProperty("Store") ))
        {
            System.out.println("Successful");
        }
        /*driver.close();
        driver.quit();*/

    }

    @When("^I validate the entire page layout$")
    public void i_validate_the_entire_page_layout() throws Throwable {

        //Build version
        //String A="S3.83.41.2";
        WebElement elem1 = driver.findElement(By.xpath("/html/body/p/table[1]/tbody/tr/td[2]/p[1]"));
        String s1=elem1.getText();
        System.out.println("Text obtainded is " +s1);
        if(s1.contains(prop.getProperty("Patchset")))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        //First 3 buttons validation

        WebElement elem2 = driver.findElement(By.xpath("/html/body/p/table[2]/tbody/tr[1]/td[2]/button"));
        String s2=elem2.getText();
        System.out.println("Text obtainded is " +s2);
        if(s2.equals("Reporting"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem3 = driver.findElement(By.xpath("/html/body/p/table[2]/tbody/tr[2]/td[2]/button"));
        String s3=elem3.getText();
        System.out.println("Text obtainded is " +s3);
        if(s3.equals("Info"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem4 = driver.findElement(By.xpath("/html/body/p/table[2]/tbody/tr[3]/td[2]/button"));
        String s4=elem4.getText();
        System.out.println("Text obtainded is " +s4);
        if(s4.equals("Store Cache"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        //CacheBuilderManager

        WebElement elem5 = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[1]"));
        String s5=elem5.getText();
        System.out.println("Text obtainded is " +s5);
        if(s5.equals("OrgUnitsBeingBuilt"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem6 = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[3]/td[1]"));
        String s6=elem6.getText();
        System.out.println("Text obtainded is " +s6);
        if(s6.equals("OrgUnitsProcessed"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem7 = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[4]/td[1]"));
        String s7=elem7.getText();
        System.out.println("Text obtainded is " +s7);
        if(s7.equals("NumOrgUnitsProcessedHeld"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem8 = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[5]/td[1]"));
        String s8=elem8.getText();
        System.out.println("Text obtainded is " +s8);
        if(s8.equals("HasStartedOkay"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem9 = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[6]/td[1]"));
        String s9=elem9.getText();
        System.out.println("Text obtainded is " +s9);
        if(s9.equals("FailedOrgUnits"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem10 = driver.findElement(By.xpath("//*[@id=\"formCacheBuilderManagerresetFailedOrgUnits_submit\"]"));
        String s10=elem10.getAttribute("value");
        System.out.println("Text obtainded is " +s10);
        if(s10.equals("Reset Failed Org Units"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        //CacheBuilderProcessorManager

        WebElement elem11 = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[1]"));
        String s11=elem11.getText();
        System.out.println("Text obtainded is " +s11);
        if(s11.equals("OrgUnits"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem12 = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[3]/td[1]"));
        String s12=elem12.getText();
        System.out.println("Text obtainded is " +s12);
        if(s12.equals("Busy"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem13 = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/td[1]"));
        String s13=elem13.getText();
        System.out.println("Text obtainded is " +s13);
        if(s13.equals("CurrentLookupName"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem14 = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[5]/td[1]"));
        String s14=elem14.getText();
        System.out.println("Text obtainded is " +s14);
        if(s14.equals("RecordCounter"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem15 = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[6]/td[1]"));
        String s15=elem15.getText();
        System.out.println("Text obtainded is " +s15);
        if(s15.equals("CacheType"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        //CacheServiceManager



        WebElement elem16 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCache_submit\"]"));
        String s16=elem16.getAttribute("value");
        System.out.println("Text obtainded is " +s16);
        if(s16.equals("Cache for Till"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem17 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCache_submit\"]"));
        String s17=elem17.getAttribute("value");
        System.out.println("Text obtainded is " +s17);
        if(s17.equals("New Cache for Till"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem18 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCacheAllTills_submit\"]"));
        String s18=elem18.getAttribute("value");
        System.out.println("Text obtainded is " +s18);
        if(s18.equals("Cache for all Tills in Store"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem19 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCacheAllTills_submit\"]"));
        String s19=elem19.getAttribute("value");
        System.out.println("Text obtainded is " +s19);
        if(s19.equals("New Cache for all Tills in Store"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem20 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCacheMultipleStores_submit\"]"));
        String s20=elem20.getAttribute("value");
        System.out.println("Text obtainded is " +s20);
        if(s20.equals("Cache for multiple Stores"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem21 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCacheMultipleStores_submit\"]"));
        String s21=elem21.getAttribute("value");
        System.out.println("Text obtainded is " +s21);
        if(s21.equals("New Cache for multiple Stores"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem22 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCacheForOwnChildren_submit\"]"));
        String s22=elem22.getAttribute("value");
        System.out.println("Text obtainded is " +s22);
        if(s22.equals("Cache for Own Children"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem23 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestNewCacheForOwnChildren_submit\"]"));
        String s23=elem23.getAttribute("value");
        System.out.println("Text obtainded is " +s23);
        if(s23.equals("New Cache for Own Children"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem24 = driver.findElement(By.xpath("//*[@id=\"formCacheServiceManagerrequestCommissionCache_submit\"]"));
        String s24=elem24.getAttribute("value");
        System.out.println("Text obtainded is " +s24);
        if(s24.equals("Commission Cache for Till"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        //ChangeDistributorManager

        WebElement elem25 = driver.findElement(By.xpath("/html/body/div[11]/table/tbody/tr[2]/td[1]"));
        String s25=elem25.getText();
        System.out.println("Text obtainded is " +s25);
        if(s25.equals("HasStartedOkay"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem26 = driver.findElement(By.xpath("/html/body/div[11]/table/tbody/tr[3]/td[1]"));
        String s26=elem26.getText();
        System.out.println("Text obtainded is " +s26);
        if(s26.equals("Busy"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem27 = driver.findElement(By.xpath("/html/body/div[11]/table/tbody/tr[4]/td[1]"));
        String s27=elem27.getText();
        System.out.println("Text obtainded is " +s27);
        if(s27.equals("Suspended"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem28 = driver.findElement(By.xpath("/html/body/div[11]/table/tbody/tr[5]/td[1]"));
        String s28=elem28.getText();
        System.out.println("Text obtainded is " +s28);
        if(s28.equals("PausedCounter"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem29 = driver.findElement(By.xpath("/html/body/div[11]/table/tbody/tr[6]/td[1]"));
        String s29=elem29.getText();
        System.out.println("Text obtainded is " +s29);
        if(s29.equals("ChangesAvailable"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        //ChangeMonitorManager

        WebElement elem30 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[2]/td[1]"));
        String s30=elem30.getText();
        System.out.println("Text obtainded is " +s30);
        if(s30.equals("Running"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem31 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[3]/td[1]"));
        String s31=elem31.getText();
        System.out.println("Text obtainded is " +s31);
        if(s31.equals("CurrentDeltaSet"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem32 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[4]/td[1]"));
        String s32=elem32.getText();
        System.out.println("Text obtainded is " +s32);
        if(s32.equals("UsingDBQueue"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem33 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[5]/td[1]"));
        String s33=elem33.getText();
        System.out.println("Text obtainded is " +s33);
        if(s33.equals("DBQueuePaused"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem34 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[6]/td[1]"));
        String s34=elem34.getText();
        System.out.println("Text obtainded is " +s34);
        if(s34.equals("ProcessingXML1"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem35 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[7]/td[1]"));
        String s35=elem35.getText();
        System.out.println("Text obtainded is " +s35);
        if(s35.equals("DocumentsProcessed"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem36 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[8]/td[1]"));
        String s36=elem36.getText();
        System.out.println("Text obtainded is " +s36);
        if(s36.equals("HasStartedOkay"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem37 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[9]/td[1]"));
        String s37=elem37.getText();
        System.out.println("Text obtainded is " +s37);
        if(s37.equals("XMLQueue"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem38 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[10]/td[1]"));
        String s38=elem38.getText();
        System.out.println("Text obtainded is " +s38);
        if(s38.equals("CacheBuildAtEop"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem39 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[11]/td[1]"));
        String s39=elem39.getText();
        System.out.println("Text obtainded is " +s39);
        if(s39.equals("WaitingForCacheBuilder"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem40 = driver.findElement(By.xpath("/html/body/div[12]/table/tbody/tr[12]/td[1]"));
        String s40=elem40.getText();
        System.out.println("Text obtainded is " +s40);
        if(s40.equals("NumOfHandlers"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        //ChangeProcessorManager

        WebElement elem41 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[2]/td[1]"));
        String s41=elem41.getText();
        System.out.println("Text obtainded is " +s41);
        if(s41.equals("Busy"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem42 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[3]/td[1]"));
        String s42=elem42.getText();
        System.out.println("Text obtainded is " +s42);
        if(s42.equals("Alive"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem43 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[4]/td[1]"));
        String s43=elem43.getText();
        System.out.println("Text obtainded is " +s43);
        if(s43.equals("WaitForKick"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem44 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[5]/td[1]"));
        String s44=elem44.getText();
        System.out.println("Text obtainded is " +s44);
        if(s44.equals("HasStartedOkay"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem45 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[6]/td[1]"));
        String s45=elem45.getText();
        System.out.println("Text obtainded is " +s45);
        if(s45.equals("BatchReference"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem46 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[7]/td[1]"));
        String s46=elem46.getText();
        System.out.println("Text obtainded is " +s46);
        if(s46.equals("FileReference"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem47 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[8]/td[1]"));
        String s47=elem47.getText();
        System.out.println("Text obtainded is " +s47);
        if(s47.equals("TransactionReference"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        WebElement elem48 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[9]/td[1]"));
        String s48=elem48.getText();
        System.out.println("Text obtainded is " +s48);
        if(s48.equals("DocumentReference"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem49 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[10]/td[1]"));
        String s49=elem49.getText();
        System.out.println("Text obtainded is " +s49);
        if(s49.equals("DocumentType"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem50 = driver.findElement(By.xpath("/html/body/div[13]/table/tbody/tr[11]/td[1]"));
        String s50=elem50.getText();
        System.out.println("Text obtainded is " +s50);
        if(s50.equals("EndOfProcessing"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem51 = driver.findElement(By.xpath("//*[@id=\"formChangeProcessorManagerwakeUpChangeProcessor_submit\"]"));
        String s51=elem51.getAttribute("value");
        System.out.println("Text obtainded is " +s51);
        if(s51.equals("Wake Up Change Processor"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem52 = driver.findElement(By.xpath("//*[@id=\"formChangeProcessorManagerwakeUpChangeProcessorAfterError_submit\"]"));
        String s52=elem52.getAttribute("value");
        System.out.println("Text obtainded is " +s52);
        if(s52.equals("Wake Up Change Processor After Error"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        //DDSControllerManager


        WebElement elem53 = driver.findElement(By.xpath("/html/body/div[14]/table/tbody/tr[2]/td[1]"));
        String s53=elem53.getText();
        System.out.println("Text obtainded is " +s53);
        if(s53.equals("Configuration"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem54 = driver.findElement(By.xpath("//*[@id=\"formDDSControllerManagerreloadConfiguration_submit\"]"));
        String s54=elem54.getAttribute("value");
        System.out.println("Text obtainded is " +s54);
        if(s54.equals("Reload Configuration"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }


        //DataSourceListenerManager

        WebElement elem55 = driver.findElement(By.xpath("/html/body/div[16]/table/tbody/tr[2]/td[1]"));
        String s55=elem55.getText();
        System.out.println("Text obtainded is " +s55);
        if(s55.equals("HasStartedOkay"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        //XMLFileStoreAccessorManager Attributes


        WebElement elem56 = driver.findElement(By.xpath("/html/body/div[17]/table/tbody/tr[2]/td[1]"));
        String s56=elem56.getText();
        System.out.println("Text obtainded is " +s56);
        if(s56.equals("HousekeepingHour"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem57 = driver.findElement(By.xpath("/html/body/div[17]/table/tbody/tr[3]/td[1]"));
        String s57=elem57.getText();
        System.out.println("Text obtainded is " +s57);
        if(s57.equals("HouseKeepingMinute"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem58 = driver.findElement(By.xpath("/html/body/div[17]/table/tbody/tr[4]/td[1]"));
        String s58=elem58.getText();
        System.out.println("Text obtainded is " +s58);
        if(s58.equals("DeltaSetsSinceHousekeepingCount"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        WebElement elem59 = driver.findElement(By.xpath("/html/body/div[17]/table/tbody/tr[5]/td[1]"));
        String s59=elem59.getText();
        System.out.println("Text obtainded is " +s59);
        if(s59.equals("NumDeltasBeforeTriggeringHousekeeping"))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Fail");
        }

        int count = 0;


        for(int i=2;i<10;i++)
        {
            WebElement elemaa = driver.findElement(By.xpath("/html/body/strong["+i+"]/h3/font"));
            String aa= elemaa.getText();
            if(aa.contains("CacheBuilderProcessorManager"))
            {
                count++;
            }

        }



        System.out.println("No.of times CacheBuilderProcessorManager present is " +count);






    }




    @Then("^layout is as per latest design$")
    public void layout_is_as_per_latest_design() throws Throwable {
        /*driver.close();
        driver.quit();*/

    }
    @When("^I enter Scot number and Store number under Request Cache a Scot in Cache Service manager$")
    public void i_enter_Scot_number_and_Store_number_under_Request_Cache_a_Scot_in_Cache_Service_manager() throws Throwable {
        driver.findElement(By.id("formCacheServiceManagerrequestCache_param0")).sendKeys(prop.getProperty("Cache_Till"));
        driver.findElement(By.id("formCacheServiceManagerrequestCache_param1")).sendKeys(prop.getProperty("Cache_Store"));


    }

    @When("^I click on Cache for Scot button$")
    public void i_click_on_Cache_for_Scot_button() throws Throwable {
        driver.findElement(By.id("formCacheServiceManagerrequestCache_submit")).click();
    }

    @Then("^I capture the prompt for cache$")
    public void i_capture_the_prompt_for_cache() throws Throwable {
        String E=driver.findElement(By.xpath("//*[@id=\"dialogResponseParagraph\"]/font")).getText();
        String A="required cache is ready for use and will be sent straight away";
if (E.contains(A))
{
    System.out.println("Cache sent");
        /*driver.close();
        driver.quit();*/

    }
    else {
    Assert.fail();
    }

    }




    @When("^I enter Store number under Request Cache a Scot in Cache Service manager$")
    public void i_enter_Store_number_under_Request_Cache_a_Scot_in_Cache_Service_manager() throws Throwable {
        driver.findElement(By.id("formCacheServiceManagerrequestCacheAllTills_param0")).sendKeys(prop.getProperty("CacheForStore"));

    }

    @When("^I click on Cache for Store button$")
    public void i_click_on_Cache_for_Store_button() throws Throwable {

        driver.findElement(By.id("formCacheServiceManagerrequestCacheAllTills_submit")).click();

    }

    @Then("^I capture the prompt for cache for Store$")
    public void i_capture_the_prompt_for_cache_for_Store() throws Throwable {

Thread.sleep(6000);
        String E = driver.findElement(By.xpath("//*[@id=\"dialogResponseParagraph\"]/font")).getText();
        String A = "required cache is ready for use and will be sent straight away";
        if (E.contains(A)) {
            System.out.println("Cache sent");
            /*driver.close();
            driver.quit();*/

        } else {
            Assert.fail();
        }

    }
    @When("^I enter Store number under Request new Cache a Scot in Cache Service manager$")
    public void i_enter_Store_number_under_Request_new_Cache_a_Scot_in_Cache_Service_manager() throws Throwable {
driver.findElement(By.xpath("//input[@id='formCacheServiceManagerrequestNewCacheAllTills_param0']")).sendKeys(prop.getProperty("NewCachestore"));
driver.findElement(By.id("formCacheServiceManagerrequestNewCacheAllTills_submit")).click();
    }
}

