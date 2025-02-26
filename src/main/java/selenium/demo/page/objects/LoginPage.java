package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;

@Getter
public class LoginPage extends BaseClass {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameElement;


    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordElement;


    @FindBy(xpath = "//input[@id= 'loginButton']")
    private WebElement loginbuttonElement;

    public void launchOpenMrsApplication(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public WebElement getUsernameElement() {
        return usernameElement;
    }

    public void setUsername(String userName) {
        getUsernameElement().sendKeys(userName);
    }

    public WebElement getPasswordElement() {
        return passwordElement;
    }

    public void setPassword(String password) {
        getPasswordElement().sendKeys(password);
    }

    public void clickModule(String moduleName) {
        driver.findElement(By.id(moduleName)).click();
    }

    public WebElement getLoginbuttonElement() {
        return loginbuttonElement;
    }

    public void clickLoginButton() {
        getLoginbuttonElement().click();
    }

    public void loginToOpenMrs(String userName, String password, String moduleName) {
//        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
//        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
//        driver.findElement(By.id(moduleName)).click();
//        driver.findElement(By.xpath("//input[@id= 'loginButton']")).click();

//        usernameElement.sendKeys(userName);
//        passwordElement.sendKeys(password);
//        driver.findElement(By.id(moduleName)).click();
//        loginbuttonElement.click();

        setUsername(userName);
        setPassword(password);
        clickModule(moduleName);
        clickLoginButton();
    }
}
