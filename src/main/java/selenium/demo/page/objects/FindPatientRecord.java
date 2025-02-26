package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class FindPatientRecord extends BaseClass {
    public FindPatientRecord(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "patient-search")
    private WebElement findpatientSearchElement;


    public WebElement getpatientSearch() {
        return findpatientSearchElement;
    }

    public void enterpatientSearchValue(String value) {
        getpatientSearch().sendKeys(value);
    }

}
