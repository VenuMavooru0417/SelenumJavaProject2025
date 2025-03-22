package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.demo.page.objects.HomePage;
import selenium.demo.page.objects.LoginPage;

import java.util.concurrent.TimeUnit;

public class WelcomePageTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/ChromeDrivers/chromedriver134v.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.automationtestplanet.com/practise-application");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

//        WebElement sameWindow = driver.findElement(By.linkText("OpenMRS in Same Window"));
//        sameWindow.click();
        driver.switchTo().frame("frame1");
        Thread.sleep(10000);
        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        homePage.clicklogoutElement();




    }

}
