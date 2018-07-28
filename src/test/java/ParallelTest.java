import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest {
    public WebDriver driver;
    @Parameters("browser")
    @BeforeClass
    // Passing Browser parameter from TestNG xml
    public void beforeTest(String browser) {
        // If the browser is Firefox, then do this
        if (browser.equalsIgnoreCase("firefox")) {
        	System.setProperty("webdriver.gecko.driver","D:\\Selenium\\mozilla\\geckodriver.exe");	
            driver = new FirefoxDriver();
            // If browser is chrome, then do this
        } else if (browser.equalsIgnoreCase("chrome")) {
            // Here I am setting up the path for my Chrome
        	System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        // Doesn't the browser type, lauch the Website
        driver.get("https://www.ebay.com.au/");
        driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // Once Before method is completed, Test method will start
    @Test
    public void Addtocart() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	driver.findElement(By.id("gh-ac")).sendKeys("adidas");
    	driver.findElement(By.id("gh-btn")).click();
        String product = driver.findElement(By.xpath("//*[@id=\"srp-river-results-listing1\"]/div/div[2]/a/h3")).getText();
    	System.out.println(product);
    	
    	driver.findElement(By.xpath("//*[@id=\"srp-river-results-listing1\"]/div/div[2]/a/h3")).click();
    	driver.findElement(By.id("atcRedesignId_btn")).click();
    	String window1=driver.getWindowHandle();
    	driver.switchTo().window(window1);
    	System.out.println(window1);
        driver.findElement(By.xpath("//*[@id=\"atcRedesignId_overlay-atc-container\"]/div/div[1]/div/div[2]/a[2]/span/span")).click();
    	String productcart = driver.findElement(By.xpath("//*[@id=\"142236394587_title\"]/a")).getText();
    	System.out.println(productcart);
    	Assert.assertEquals(product, productcart);
    	driver.findElement(By.id("gh-ac")).sendKeys("shirt");
    	driver.findElement(By.id("gh-btn")).click();
        String product1 = driver.findElement(By.xpath("//*[@id=\"srp-river-results-listing1\"]/div/div[2]/a/h3")).getText();
    	System.out.println(product1);
    	driver.findElement(By.xpath("//*[@id=\"srp-river-results-listing1\"]/div/div[2]/a/h3")).click();
    	driver.findElement(By.id("atcRedesignId_btn")).click();
    	String window2=driver.getWindowHandle();
    	driver.switchTo().window(window2);
    	System.out.println(window2);
    	driver.findElement(By.xpath("//*[@id=\"atcRedesignId_overlay-atc-container\"]/div/div[1]/div/div[2]/a[2]/span/span")).click();
    	String productcart1 = driver.findElement(By.xpath("//*[@id=\"172731601888_title\"]/a")).getText();
    	System.out.println(productcart1);
    	Assert.assertEquals(product1, productcart1);
    	Assert.assertEquals(product, productcart);
    }

    @AfterClass public void afterTest() {
        driver.quit();
    }
}