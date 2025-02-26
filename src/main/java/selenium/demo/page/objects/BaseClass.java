package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BaseClass {
    public static WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".icon-home.small")
    private WebElement homeIconElement;


    public boolean verifyPageTitle(String title) {
        return driver.getTitle().trim().equalsIgnoreCase(title);
    }
    public boolean verifyPageName(String pageName) {
        return driver.findElement(By.xpath("//h2[contains(text(), '" + pageName + "')]")).isDisplayed();
    }
    public boolean verifyElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    public WebElement getHomeIconElement() {
        return homeIconElement;
    }
    public void clickHomeIcon(){
        getHomeIconElement().click();
    }
}
