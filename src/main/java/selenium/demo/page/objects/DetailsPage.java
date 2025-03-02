package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Getter
public class DetailsPage extends BaseClass {
    //    WebDriver driver;
    public DetailsPage(WebDriver driver) {
//        this.driver = driver;
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class, 'PersonName-givenName')]")
    private WebElement personGivenNameElement;

    public WebElement getPersonGivenNameElement() {
        return personGivenNameElement;
    }

    @FindBy(xpath = "//*[contains(@class, 'PersonName-familyName')]")
    private WebElement personFamilyNameElement;

    public WebElement getPersonFamilyNameElement() {
        return personFamilyNameElement;
    }

    @FindBy(xpath = "//em[text() = 'Patient ID']/following-sibling::span")
    private WebElement patientIdElement;

    public WebElement getPatientIdElement() {
        return patientIdElement;
    }

    public boolean verifyPatientNameInPatientDetailsPage(String name) {
        String[] nameArr = name.split(",");
        waitForVisibilityOfElement(getPersonGivenNameElement());
        String givName = getPersonGivenNameElement().getText().trim();
        String familyName = getPersonFamilyNameElement().getText().trim();
        return givName.equalsIgnoreCase(nameArr[0].trim()) && familyName.equalsIgnoreCase(nameArr[1].trim());
    }

    public String getPatientId() {
        return getPatientIdElement().getText().trim();
    }

//    public void waitForVisibilityOfElement(WebElement element){
//        FluentWait wait = new FluentWait(driver);
//        wait.withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//
//        Function<WebDriver, WebElement> element1 = new Function<WebDriver, WebElement>(){
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return element;
//            }
//        };
//        Function<WebDriver, WebElement> element2 = (driver) -> element;
//
//    }

}
