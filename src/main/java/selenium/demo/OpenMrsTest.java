package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class OpenMrsTest {
    // User Login
    // Register A new Patient
    // Find Patient using Patient name OR ID
    // Start Visit and Add Attachments
    //Delete Patient Record
    // Search User After Deleting
    // LogOut
    static WebDriver driver;

    static void launchOpenMrsApplication(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/ChromeDrivers/chromedriver132v.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
    }


    static void loginToOpenMrs(String userName, String password, String moduleName) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
        driver.findElement(By.id(moduleName)).click();
        driver.findElement(By.xpath("//input[@id= 'loginButton']")).click();
    }

    static boolean verifyPageTitle(String title) {
        return driver.getTitle().trim().equalsIgnoreCase(title);
    }

    static boolean verifyTile(String tileName) {
        return driver.findElement(By.partialLinkText(tileName)).isDisplayed();
    }

    static void clickRegisterTile(String tileName) {
        driver.findElement(By.partialLinkText(tileName)).click();
    }

    static boolean verifyPageName(String pageName) {
        return driver.findElement(By.xpath("//h2[contains(text(), '" + pageName + "')]")).isDisplayed();
    }

    static void enterName(String name) {
        String[] nameArr = name.split(",");
        driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
        driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
    }

    static void selectGender(String gender) {
        Select genderDrpDown = new Select(driver.findElement(By.id("gender-field")));
//        genderDrpDown.selectByIndex(0);
//        genderDrpDown.selectByValue("M");
        genderDrpDown.selectByVisibleText("Male");
//        List<WebElement> allOptions = genderDrpDown.getOptions();
//        System.out.println(allOptions.size());
//        System.out.println(genderDrpDown.getFirstSelectedOption().getAttribute("value"));
//        System.out.println(genderDrpDown.getFirstSelectedOption().getText());
    }

    static void enterDOB(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
        Select mnthDrpDown = new Select(driver.findElement(By.id("birthdateMonth-field")));
        mnthDrpDown.selectByVisibleText(dateOfBirthArr[1].trim());
        driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
    }

    static void enterAddress(String adress) {
        String[] addressArr = adress.split(",");
        driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
        driver.findElement(By.id("cityVillage")).sendKeys(addressArr[1].trim());
        driver.findElement(By.id("stateProvince")).sendKeys(addressArr[2].trim());
        driver.findElement(By.id("country")).sendKeys(addressArr[3].trim());
        driver.findElement(By.id("postalCode")).sendKeys(addressArr[4].trim());
    }

    static void enterPhoneNo(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    static void enterRelation(String relation) {
//        String[] relationArr = relation.split("-");
//        Select relShipType = new Select(driver.findElement(By.id("relationship_type")));
//        relShipType.selectByVisibleText(relationArr[1].trim());
//        relShipType.selectByVisibleText(relationArr[0].trim());
        Select relShipType = new Select(driver.findElement(By.xpath("//select[@id='relationship_type']")));
        relShipType.selectByVisibleText("Doctor".trim());
        driver.findElement(By.xpath("//input[@placeholder=\'Person Name\']")).sendKeys("Ramesh");
//        driver.findElement(By.xpath("//input[@placeholder=\'Person Name\']")).sendKeys(relationArr[1].trim());
    }

    static void clickNext() {

        driver.findElement(By.id("next-button")).click();
    }

    static boolean verifyDetailsConfirm(String name, String gender, String birthDate) {
        String actName = driver.findElement(By.xpath("//span[text() = 'Name: ']/parent::p")).getText().trim();
        String actGender = driver.findElement(By.xpath("//span[text() = 'Gender: ']/parent::p")).getText().trim();
        String actBirthDate = driver.findElement(By.xpath("//span[text() = 'Birthdate: ']/parent::p")).getText().trim();
        String act_address = driver.findElement(By.xpath("//span[text()= 'Address: ']/parent::p")).getText().trim();
        String act_phoneNo = driver.findElement(By.xpath("//span[text()= 'Phone Number: ']/parent::p")).getText().trim();
        String act_relatives = driver.findElement(By.xpath("//span[text()= 'Relatives: ']/parent::p")).getText().trim();
        //return act_name.contains(name) && act_gender.contains(gender) && act_birthDate.contains(birthDate) && act_address.contains("S.R Nagar") && act_phoneNo.contains("9618927073") && act_relatives.contains("Ramesh - Doctor");
        return actName.contains(name) && actGender.contains(gender) && actBirthDate.contains(birthDate);
    }

    static void clickConfirm() {

        driver.findElement(By.xpath("//input[@id = 'submit']")).click();
    }

    static void clickCancel() {

        driver.findElement(By.xpath("//input[@id = 'cancelSubmission']")).click();
    }

    static void ClickHomeLogo() {
        driver.findElement(By.xpath("//i[@class='icon-home small']")).click();
    }

//    static boolean verifyTestAfterRegistration(String text) {
//        return driver.findElement(By.xpath("//h3[text() = 'General Actions']")).isDisplayed();
//    }
//    static void clickOnHomePage(String text){
//        driver.findElement(By.xpath("//i[@class='icon-home small']")).click();
//        return driver.findElement(By.xpath("//h4")).isDisplayed();
//    }

    static boolean verifyFindRecordTile(String tileName) {
        return driver.findElement(By.partialLinkText(tileName)).isDisplayed();
    }

    static void clickFindRecordTile(String tileName) {
        driver.findElement(By.partialLinkText(tileName)).click();
    }

    static boolean verifyFindRecordPageTitle(String title) {
        return driver.findElement(By.xpath("//h2")).isDisplayed();
    }

    static void patientSearch(String name) {
        driver.findElement(By.id("patient-search")).sendKeys(name, Keys.ENTER);
    }

    static boolean verifyPatientNameInPatientDetailsPage(String name) {
        String[] nameArr = name.split(",");
        String givName = driver.findElement(By.className("PersonName-givenName")).getText().trim();
        String familyName = driver.findElement(By.className("PersonName-familyName")).getText().trim();
        return givName.equalsIgnoreCase(nameArr[0].trim()) && familyName.equalsIgnoreCase(nameArr[1].trim());
    }

    static String getPatientId() {
        return driver.findElement(By.xpath("//em[text() = 'Patient ID']/following-sibling::span")).getText().trim();
    }

    static WebElement logoutElement() {

        return driver.findElement(By.partialLinkText("Logout"));
    }

    static boolean verifyElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }


    public static void main(String[] args) {
        launchOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");
        //driver.navigate().forward();
        // User Login
        if (verifyPageTitle("Login")) {
            loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (verifyElementIsDisplayed(logoutElement()) && verifyPageTitle("Home")) {
                System.out.println("Login Test Successful");
                // Register a Patient
                if (verifyTile("Register a patient")) {
                    clickRegisterTile("Register a patient");
                    if (verifyPageName("Register a patient")) {
                        enterName("Venu, M");
                        clickNext();
                        selectGender("Male");
                        clickNext();
                        enterDOB("01, January, 1995");
                        clickNext();
                        enterAddress("S.R Nagar, Hyderabad, Telangana, India, 500038");
                        clickNext();
                        enterPhoneNo("9618927073");
                        clickNext();
                        enterRelation("Doctor, Ramesh");
                        clickNext();
//                        clickNext();
//                        clickNext();

//                        System.out.println(name);
//                        System.out.println(gender);
//                        System.out.println(birthDate);
//                        System.out.println(address);
//                        System.out.println(phoneNo);
//                        System.out.println(relatives);

                        if (verifyDetailsConfirm("Venu, M", "Male", "01, January, 1995")) {
                            System.out.println("Details are properly displaying, Clicking on Confirm button!");
                            clickConfirm();
                            ClickHomeLogo();
                            if (verifyFindRecordTile("Find Patient Record")) {
                                clickFindRecordTile("Find Patient Record");
                                if (verifyFindRecordPageTitle("Find Patient Record")) {
                                    System.out.println("Find Patient Record");
                                    patientSearch("Venu");
                                    if (verifyPatientNameInPatientDetailsPage("Venu, M")) {
                                        System.out.println("patient name is displayed in patient details page.");
                                        System.out.println(getPatientId());
                                        //Thread.sleep(5000);
                                        driver.findElement(By.xpath("//div[normalize-space(text())='Delete Patient']")).click();
                                        WebElement Reason = driver.findElement(By.xpath("//input[@id = 'delete-reason']"));
                                        Reason.sendKeys("Others");
                                        System.out.println(Reason.getText());
                                        driver.findElement(By.xpath("//div[@id = 'delete-patient-creation-dialog']/div[2]/button[text() = 'Confirm']")).click();
                                        driver.findElement(By.xpath("//div[@id = 'delete-patient-creation-dialog']/div[2]/button[text() = 'Cancel']")).click();
                                        if (driver.findElement(By.xpath("//h2")).isDisplayed()) {
                                            WebElement findPatient = driver.findElement(By.xpath("//input[@id = 'patient-search']"));
                                            findPatient.sendKeys("Venu");
                                            driver.findElement(By.xpath("//i[@id = 'patient-search-clear-button']")).click();
                                            //Thread.sleep(5000);
                                            WebElement logout = driver.findElement(By.partialLinkText("Logout"));
                                            logout.click();
                                            if (driver.findElement(By.xpath("//a[@id = 'cantLogin']")).isDisplayed() && driver.getTitle().trim().equalsIgnoreCase("Login")) {
                                                WebElement cnntlogin = driver.findElement(By.xpath("//a[@id = 'cantLogin']"));
                                                System.out.println(cnntlogin.getText());
                                                cnntlogin.click();
                                                //Thread.sleep(3000);
                                                driver.findElement(By.xpath("//button[text() = 'Okay']")).click();
                                                driver.close();
                                            } else {
                                                System.out.println(driver.getTitle());
                                            }
                                        } else {
                                            System.out.println(driver.getTitle());
                                        }
                                    } else {
                                        driver.close();
                                    }
                                } else {
                                    System.out.println("Patient Deatails Displayed.");
                                }
                            } else {
                                System.out.println("Test is not matched");
                            }
                        } else {
                            System.out.println("Registered details are not matched properly, Clicking on Cancel button!");
                            clickCancel();
                        }


                    } else {
                        System.out.println("Register a patient not available..");
                    }

                } else {
                    System.out.println("Register a patient tile Not Available..");
                }


            } else {
                System.out.println("Login failed");
            }
        } else {
            System.out.println("Login Page is not available");
        }


        driver.navigate().back();
        driver.close();


    }


}
