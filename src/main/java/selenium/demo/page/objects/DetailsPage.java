package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

    @FindBy(xpath = "//div[contains(text(), 'Start Visit')]//ancestor::a")
    private WebElement startVisitElement;

    @FindBy(id = "start-visit-with-visittype-confirm")
    private WebElement startVisitConfirmButton;

    public WebElement getStartVisitElement() {
        return startVisitElement;
    }

    public WebElement getStartVisitConfirmButton() {
        return startVisitConfirmButton;
    }

    public void clickStartVisitElement() {
        getStartVisitElement().click();
        getStartVisitConfirmButton().click();
    }

    @FindBy(xpath = "//a[contains(text(), 'Visits') and contains(@href, 'visits')]")
    private WebElement visitTabElement;

    public WebElement getVisitTabElement() {
        return visitTabElement;
    }

    public boolean veriftVisitTab() {
        return getVisitTabElement().isDisplayed();
    }

    @FindBy(xpath = "//a[contains(@href, '/attachments')]")
    private WebElement attachmentsLink;

    public WebElement getAttachmentsLink() {
        return attachmentsLink;
    }

    public void clickAttachmentLink() {
        getAttachmentsLink().click();
    }

    @FindBy(xpath = "//div[contains(text(), 'Click or drop a file here.')]")
    private WebElement fileButton;

    public WebElement getFileButton() {
        return fileButton;
    }

    public void clickFileButton() {
        getFileButton().click();
    }

    @FindBy(xpath = "//h3[contains(text(), 'Caption')]/following-sibling::textarea")
    private WebElement captionElement;

    public WebElement getCaptionElement() {
        return captionElement;
    }

    @FindBy(xpath = "//button[contains(text(), 'Upload file')]")
    private WebElement uploadBtnElement;

    public WebElement getUploadBtnElement(){
        return uploadBtnElement;
    }

    @FindBy(xpath = "//div[contains(text(), 'Delete Patient')]/ancestor::a")
    private WebElement deletePatiendRecord;

    public WebElement getDeletePatiendRecord() {
        return deletePatiendRecord;
    }

    public void clickDeletePatient() {
        getDeletePatiendRecord().click();
    }

    @FindBy(id = "delete-reason")
    private WebElement deletePatientReasonElement;

    public WebElement getDeletePatientReasonElement() {
        return deletePatientReasonElement;
    }

    public void enterDeleteReason(String reason) {
        getDeletePatientReasonElement().sendKeys(reason);
    }

    @FindBy(css = "#delete-patient-creation-dialog button.confirm.right")
    private WebElement deleteConfirmButton;

    public WebElement getDeleteConfirmButton() {
        return deleteConfirmButton;
    }

    public void clickDeleteConfirmButton() {
        getDeleteConfirmButton().click();
    }

    public void uploadFile(String filePath, String caption) {
        clickFileButton();
        try {
            Thread.sleep(10000);
            StringSelection selectStr = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectStr, null);
            // Robot Class
            Robot robot = new Robot();// using robot class we can perform mouse and keyboard actions...

            // Simulate Ctrl + V to paste file path
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            Thread.sleep(5000);
            // Press Enter to upload the file
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(5000);
            getCaptionElement().sendKeys(caption);

            if (getUploadBtnElement().isEnabled()) {
                getUploadBtnElement().click();
            }
        } catch (Exception e) {
            System.out.println("Exception While Upload a File");
        }
    }

    public boolean verifyFileUpload(String caption) {
        waitForVisibilityOfElement(driver.findElement(By.xpath("//p[contains(text(), '" + caption + "')]")));
        return driver.findElement(By.xpath("//p[contains(text(), '" + caption + "')]")).isDisplayed();
    }


    public void waitForVisibilityOfElement(WebElement element) {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        Function<WebDriver, WebElement> element1 = new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return element;
            }
        };
        Function<WebDriver, WebElement> element2 = (driver) -> element;

    }

}
