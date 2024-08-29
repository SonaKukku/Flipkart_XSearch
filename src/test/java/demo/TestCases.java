package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    //testCase01: Go to www.flipkart.com. Search "Washing Machine". Sort by popularity and print the count of items with rating less than or equal to 4 stars.
   @Test
     public void testCase01() throws InterruptedException{
    driver.get("https://www.flipkart.com/");
    WebElement Search=driver.findElement(By.xpath("//input[@name='q']"));
    Wrappers.EnterText(Search, "Washing Machine");
    Search.submit();
   // WebElement  firstProduct= driver.findElement(By.xpath("(//span[contains(text(),'washing machine')])[1]"));
   // firstProduct.click();
    Thread.sleep(3000);
    WebElement popularity= driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
    popularity.click();
    //call method ratinglist from wrappers
    Thread.sleep(3000);
    Wrappers.ratinglist(driver);
}
//testCase02: Search "iPhone", print the Titles and discount % of items with more than 17% discount
@Test
public void testCase02() throws InterruptedException{
    driver.get("https://www.flipkart.com/");
    Thread.sleep(2000);
    // try {
    //     WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    //     WebElement closePopupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), '✕')]")));
    //     closePopupButton.click();
    // } catch (Exception e) {
    //     // No popup appeared
    // }

    WebElement Search=driver.findElement(By.xpath("//input[@name='q']"));
    Wrappers.EnterText(Search, "iPhone");
    Thread.sleep(4000);
     String iphoneText=Search.getText().trim();
     if(iphoneText.contains("iPhone")){
        System.out.println("The text 'iPhone' was  entered into the search input.");
      return;
     }
    
    Thread.sleep(3000);
    Search.submit();
    Thread.sleep(3000);
   
//ensure that entered text is iphone in search box
    WebElement iphone= driver.findElement(By.xpath("(//div[@class='KzDlHZ'])[1]"));
    String iphoneTextnew= iphone.getText();
    if(iphoneTextnew.contains(iphoneText)){
        System.out.println("Iphone is searched");
    }
     

        
    //call the method discount_Title
    Thread.sleep(3000);
    Wrappers.discount_Title(driver);
  
    Thread.sleep(3000);

}
//testCase03: Search "Coffee Mug", select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews
@Test
public void testCase03() throws InterruptedException{
    driver.get("https://www.flipkart.com/");
    WebElement Search=driver.findElement(By.xpath("//input[@name='q']"));
    Wrappers.EnterText(Search, "Coffee Mug");
    Search.submit();
    Thread.sleep(3000);
    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='XqNaEv'])[1]")));
    WebElement fourstar=driver.findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));
    fourstar.click();
    Thread.sleep(4000);
    WebElement popularty=driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
    popularty.click();
    Thread.sleep(4000);

    //call the method discount_Title
    Thread.sleep(3000);
    Wrappers.Title_Image(driver);
  
    Thread.sleep(3000);

}
    
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}