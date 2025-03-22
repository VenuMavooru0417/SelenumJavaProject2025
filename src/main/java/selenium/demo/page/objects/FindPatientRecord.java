package selenium.demo.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FindPatientRecord extends BaseClass {
    public FindPatientRecord(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "patient-search")
    private WebElement findpatientSearchElement;

    @FindBy(xpath = "//table[@id='patient-search-results-table']/thead/tr/th/div")
    private List<WebElement> FindPatienttableHeaderElement;

    @FindBy(xpath = "//table[@id='patient-search-results-table']/tbody/tr[1]")
    private WebElement FindPatienttableFirstRecord;

    public WebElement getFindPatienttableFirstRecord() {
        return FindPatienttableFirstRecord;
    }

    public List<WebElement> getFindpatientTableHeaderElement() {
        return FindPatienttableHeaderElement;
    }

    public WebElement getpatientSearch() {
        return findpatientSearchElement;
    }

    public void enterpatientSearchValue(String value) {
        getpatientSearch().sendKeys(value);
    }

    public int getFindpatientTableColumnIndex(String columnname) {
        Map<String, Integer> tableHeadersMap = new HashMap<>();
        int index = 1;
        for (WebElement eachColumnElement : getFindpatientTableHeaderElement()) {
            tableHeadersMap.put(eachColumnElement.getText().trim(), index);
            index++;
        }
        return tableHeadersMap.get(columnname);
    }

    public String getFindPatientTableColumnValue(String columnName) {
        return driver.findElement(By.xpath("//table[@id='patient-search-results-table']/tbody/tr/td[" + getFindpatientTableColumnIndex(columnName) + "]")).getText().trim();

    }

    public void clickFindPatientTableFirstRecord() {
        getFindPatienttableFirstRecord().click();
    }

    @FindBy(xpath = "//td[contains(text(), 'No matching records found')]")
    private WebElement noMatchingRecordsFoundElement;

    public WebElement getNoMatchingRecordsFoundElement() {
        return noMatchingRecordsFoundElement;
    }

    public boolean verifynoMatchingRecordsFoundMessage() {
        return getNoMatchingRecordsFoundElement().isDisplayed();
    }

}
