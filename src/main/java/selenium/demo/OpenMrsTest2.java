package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.demo.page.objects.*;

import java.util.concurrent.TimeUnit;

public class OpenMrsTest2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/ChromeDrivers/chromedriver132v.exe");
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        RegistrationPage registerPage = new RegistrationPage(driver);
        DetailsPage detailsPage = new DetailsPage(driver);
        FindPatientRecord findPatientRecord = new FindPatientRecord(driver);
        loginPage.launchOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");

        // Verify Login
        if (loginPage.verifyPageTitle("Login")) {
            loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (homePage.verifyElementIsDisplayed(homePage.getLogoutElement()) && homePage.verifyPageTitle("Home")) {
                System.out.println("Login Test Successful");
                // Register a Patient
                if (homePage.verifyTile("Register a patient")) {
                    homePage.clickRegisterTile("Register a patient");
                    if (registerPage.verifyPageName("Register a patient")) {
                        registerPage.enterRegistrationdetails("Venu, M", "Male", "01, January, 1995", "S.R Nagar, Hyderabad, Telangana, India, 500038", "9618927073", "Doctor, Ramesh");
//                        registerPage.enterName("Venu, M");
//                        registerPage.clickNext();
//                        registerPage.selectGender("Male");
//                        registerPage.clickNext();
//                        registerPage.enterDOB("01, January, 1995");
//                        registerPage.clickNext();
//                        registerPage.enterAddress("S.R Nagar, Hyderabad, Telangana, India, 500038");
//                        registerPage.clickNext();
//                        registerPage.enterPhoneNo("9618927073");
//                        registerPage.clickNext();
//                        registerPage.enterRelation("Doctor, Ramesh");
//                        registerPage.clickNext();

                        if (registerPage.verifyDetailsConfirm("Venu, M", "Male", "01, January, 1995", "S.R Nagar, Hyderabad, Telangana, India, 500038", "9618927073", "Ramesh - Doctor")) {
                            System.out.println("Details are properly displaying, Clicking on Confirm button!");
                            registerPage.clickConfirm();

                            //Verify register Page
                            if (detailsPage.verifyPatientNameInPatientDetailsPage("Venu, M")) {
                                System.out.println("patient name is displayed correctly in patient details page.");
                                System.out.println(detailsPage.getPatientId());

                                //Find Record...
                                detailsPage.clickHomeIcon();
                                homePage.clickTile("Find Patient Record");
                                findPatientRecord.verifyPageName("Find Patient Record");
                                findPatientRecord.enterpatientSearchValue("Venu M");

                                if (findPatientRecord.getFindPatientTableColumnValue("Name").equalsIgnoreCase("Venu M")) {
                                    System.out.println("Filtered patient record correct...");
                                    findPatientRecord.clickFindPatientTableFirstRecord();
                                    if (detailsPage.verifyPatientNameInPatientDetailsPage("Venu, M")) {
                                        System.out.println("Find record working as expected...");
                                    } else {
                                        System.out.println("Find record working as not expected");
                                    }
                                } else {
                                    System.out.println("Filtered patient record not correct");
                                }
                                //homePage.clicklogoutElement();
                            } else {
                                System.out.println("patient name is displayed not correctly in patient details page....");
                            }
                        } else {
                            System.out.println("Details are showing incorrect, clicking on Cancel");
                            registerPage.clickCancel();
                        }
                    } else {
                        System.out.println("Register Patient Tile Is Not Displayed");
                    }
                } else {
                    System.out.println("Register a patient tile is not available");
                }
            } else {
                System.out.println("Login Failed");
            }
        } else {
            System.out.println("Login Page Not Available");
        }
    }
}



