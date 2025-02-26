package selenium.demo.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BaseClass {
    //    WebDriver driver;
    public RegistrationPage(WebDriver driver) {
//        this.driver = driver;
        super(driver);
    }

    public void enterName(String name) {
        String[] nameArr = name.split(",");
        driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
        driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
    }

    public void selectGender(String gender) {
        Select genderDrpDown = new Select(driver.findElement(By.id("gender-field")));
//        genderDrpDown.selectByIndex(0);
//        genderDrpDown.selectByValue("M");
        genderDrpDown.selectByVisibleText("Male");
//        List<WebElement> allOptions = genderDrpDown.getOptions();
//        System.out.println(allOptions.size());
//        System.out.println(genderDrpDown.getFirstSelectedOption().getAttribute("value"));
//        System.out.println(genderDrpDown.getFirstSelectedOption().getText());
    }

    public void enterDOB(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
        Select mnthDrpDown = new Select(driver.findElement(By.id("birthdateMonth-field")));
        mnthDrpDown.selectByVisibleText(dateOfBirthArr[1].trim());
        driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
    }

    public void enterAddress(String adress) {
        String[] addressArr = adress.split(",");
        driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
        driver.findElement(By.id("cityVillage")).sendKeys(addressArr[1].trim());
        driver.findElement(By.id("stateProvince")).sendKeys(addressArr[2].trim());
        driver.findElement(By.id("country")).sendKeys(addressArr[3].trim());
        driver.findElement(By.id("postalCode")).sendKeys(addressArr[4].trim());
    }

    public void enterPhoneNo(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    public void enterRelation(String relation) {
//        String[] relationArr = relation.split("-");
//        Select relShipType = new Select(driver.findElement(By.id("relationship_type")));
//        relShipType.selectByVisibleText(relationArr[1].trim());
//        relShipType.selectByVisibleText(relationArr[0].trim());
        Select relShipType = new Select(driver.findElement(By.xpath("//select[@id='relationship_type']")));
        relShipType.selectByVisibleText("Doctor".trim());
        driver.findElement(By.xpath("//input[@placeholder=\'Person Name\']")).sendKeys("Ramesh");
//        driver.findElement(By.xpath("//input[@placeholder=\'Person Name\']")).sendKeys(relationArr[1].trim());
    }

    public void clickNext() {

        driver.findElement(By.id("next-button")).click();
    }

    public boolean verifyDetailsConfirm(String name, String gender, String birthDate, String address, String phoneNo, String relatives) {
        String actName = driver.findElement(By.xpath("//span[text() = 'Name: ']/parent::p")).getText().trim();
        String actGender = driver.findElement(By.xpath("//span[text() = 'Gender: ']/parent::p")).getText().trim();
        String actBirthDate = driver.findElement(By.xpath("//span[text() = 'Birthdate: ']/parent::p")).getText().trim();
        String act_address = driver.findElement(By.xpath("//span[text()= 'Address: ']/parent::p")).getText().trim();
        String act_phoneNo = driver.findElement(By.xpath("//span[text()= 'Phone Number: ']/parent::p")).getText().trim();
        String act_relatives = driver.findElement(By.xpath("//span[text()= 'Relatives: ']/parent::p")).getText().trim();
        //return act_name.contains(name) && act_gender.contains(gender) && act_birthDate.contains(birthDate) && act_address.contains("S.R Nagar") && act_phoneNo.contains("9618927073") && act_relatives.contains("Ramesh - Doctor");
        return actName.contains(name) && actGender.contains(gender) && actBirthDate.contains(birthDate) && act_address.contains(address) && act_phoneNo.contains(phoneNo) && act_relatives.contains(relatives);
    }
    public void clickConfirm() {
        driver.findElement(By.xpath("//input[@id = 'submit']")).click();
    }
    public void clickCancel() {
        driver.findElement(By.xpath("//input[@id = 'cancelSubmission']")).click();
    }
    public void enterRegistrationdetails(String name,String gender,String dateOfBirth, String address, String phoneno,String relation){
        enterName(name);
        clickNext();
        selectGender(gender);
        clickNext();
        enterDOB(dateOfBirth);
        clickNext();
        enterAddress(address);
        clickNext();
        enterPhoneNo(phoneno);
        clickNext();
        enterRelation(relation);
        clickNext();
    }
}
