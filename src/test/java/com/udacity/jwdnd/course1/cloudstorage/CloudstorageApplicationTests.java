package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudstorageApplicationTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private HomePage homePage;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private ResultPage resultPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port);
        this.homePage = new HomePage((driver));
        this.loginPage = new LoginPage(driver);
        this.signupPage = new SignupPage(driver);
        this.resultPage = new ResultPage(driver);
    }

    /**
     * Test 1.1
     */
    @Test
	@Order(1)
    void testUnauthorizedUserTriesToAccessHomePage() {
        assertTrue(this.loginPage.isLoginPageVisible());
        this.loginPage.signup(driver);
        assertTrue(this.signupPage.isSignUpPageVisible());
        driver.get("http://localhost:" + port + "/home");
        assertTrue(this.loginPage.isLoginPageVisible());

    }

    /**
     * Test 1.2
     */
    @Test
	@Order(2)
    void testSignUpLoginValidateHomePageLogoutAccessHomePage() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("Max");
        this.signupPage.setInputLastName("Mustermann");
        this.signupPage.setInputUsername("maexchen");
        this.signupPage.setInputPassword("Igel1234");
        this.signupPage.submit();

        this.loginPage.setInputUsername("maexchen");
        this.loginPage.setInputPassword("Igel1234");
        this.loginPage.submit();

        assertTrue(this.homePage.isHomePageVisible());

        this.homePage.logout();

        assertTrue(this.loginPage.isLoginPageVisible());

        driver.get("http://localhost:" + port + "/home");

        assertTrue(this.loginPage.isLoginPageVisible());
    }

    /**
     * Test 2.1
     */
    @Test
	@Order(3)
    void testCreateNoteAndVerify() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("Helm");
        this.signupPage.setInputLastName("Peter");
        this.signupPage.setInputUsername("helmpeter");
        this.signupPage.setInputPassword("hsv");
        this.signupPage.submit();

        this.loginPage.setInputUsername("helmpeter");
        this.loginPage.setInputPassword("hsv");
        this.loginPage.submit();

        this.homePage.toNoteTab();
        this.homePage.addNote();
        this.homePage.addNoteDescription("Today i have to do my Homework");
        this.homePage.addNoteTitle("School");
        this.homePage.submitNote();

        this.resultPage.clickSuccessLink(driver);

        assertEquals("School", this.homePage.getNoteTitle(1));
        assertEquals("Today i have to do my Homework", this.homePage.getNoteDescription(1));

        this.homePage.deleteNote(1);

		this.resultPage.clickSuccessLink(driver);

        this.homePage.logout();

    }

    /**
     * Test 2.2
     */
    @Test
	@Order(4)
    void testEditingAnExistingNoteAndVerify() throws InterruptedException {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("TestUser1");
        this.signupPage.setInputLastName("Lastname");
        this.signupPage.setInputUsername("testuser1");
        this.signupPage.setInputPassword("admin");
        this.signupPage.submit();

        this.loginPage.setInputUsername("testuser1");
        this.loginPage.setInputPassword("admin");
        this.loginPage.submit();

        this.homePage.toNoteTab();
        this.homePage.addNote();
        this.homePage.addNoteTitle("School");
        this.homePage.addNoteDescription("Today i have to do my Homework");
        this.homePage.submitNote();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.editNote(1);
        this.homePage.addNoteTitle("New Title");
        this.homePage.submitNote();

        this.resultPage.clickSuccessLink(driver);

        assertEquals("New Title", this.homePage.getNoteTitle(1));

        this.homePage.deleteNote(1);

		this.resultPage.clickSuccessLink(driver);

        this.homePage.logout();
    }

    /**
     * Test 2.3
     */
    @Test
	@Order(5)
    void testCreateAndDeleteNoteAndVerify() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("TestUser2");
        this.signupPage.setInputLastName("Lastname");
        this.signupPage.setInputUsername("testuser2");
        this.signupPage.setInputPassword("admin2");
        this.signupPage.submit();

        this.loginPage.setInputUsername("testuser2");
        this.loginPage.setInputPassword("admin2");
        this.loginPage.submit();

        this.homePage.toNoteTab();
        this.homePage.addNote();
        this.homePage.addNoteTitle("School");
        this.homePage.addNoteDescription("Today i have to do my homework");
        this.homePage.submitNote();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.deleteNote(1);

        this.resultPage.clickSuccessLink(driver);

        assertTrue(this.homePage.isNoteTableEmpty());

		this.homePage.logout();
    }

    /**
     * Test 3.1
     */
    @Test
	@Order(6)
    void testCreateSetOfCredentialsVerifyAndVerifyPasswordIsEncrypted() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("TestUser3");
        this.signupPage.setInputLastName("Lastname");
        this.signupPage.setInputUsername("testuser3");
        this.signupPage.setInputPassword("admin3");
        this.signupPage.submit();

        this.loginPage.setInputUsername("testuser3");
        this.loginPage.setInputPassword("admin3");
        this.loginPage.submit();

        this.homePage.toCredentialTab();
        this.homePage.addCredential();
        this.homePage.addUrl("hsv.de");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("test1234");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.addCredential();
        this.homePage.addUrl("bild.de");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("hello20");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.addCredential();
        this.homePage.addUrl("udacity.com");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("Igel1234");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        assertEquals(3, this.homePage.getRowsCredentialTable(driver));
        assertNotEquals("test1234", this.homePage.getCredentialPasswordEnrypted(1));
        assertNotEquals("hello20", this.homePage.getCredentialPasswordEnrypted(2));
        assertNotEquals("Igel1234", this.homePage.getCredentialPasswordEnrypted(3));

		this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);
		this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);
		this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);

		this.homePage.logout();

    }

    /**
     * Test 3.2
     */
    @Test
	@Order(7)
    void testViewCredentialVerifyThatThePasswordIsUnencryptedEditCredentialAndVerifyChanges() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("TestUser4");
        this.signupPage.setInputLastName("Lastname");
        this.signupPage.setInputUsername("testuser4");
        this.signupPage.setInputPassword("summer21");
        this.signupPage.submit();

        this.loginPage.setInputUsername("testuser4");
        this.loginPage.setInputPassword("summer21");
        this.loginPage.submit();

        this.homePage.toCredentialTab();
        this.homePage.addCredential();
        this.homePage.addUrl("facebook.com");
        this.homePage.addUsernameCredential("zuckerberg");
        this.homePage.addCredentialPassword("money123");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        String enryptedPassword = this.homePage.getCredentialPasswordEnrypted(1);

        this.homePage.editCredential(1);

        String unenrcyptedPassword = this.homePage.getUnenryptedCredentialPassword();

        assertNotEquals(enryptedPassword, unenrcyptedPassword);
        assertEquals("money123", unenrcyptedPassword);

        this.homePage.addUrl("microsoft.com");
        this.homePage.addUsernameCredential("superuser100");
        this.homePage.addCredentialPassword("hello456");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        assertNotEquals(enryptedPassword, this.homePage.getCredentialPasswordEnrypted(1));
        assertEquals("microsoft.com", this.homePage.getCredentialUrl(1));
        assertEquals("superuser100", this.homePage.getCredentialUsername(1));

		this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);

		this.homePage.logout();

    }

    /**
     * 3.3
     */
    @Test
	@Order(8)
    void testCreateSetOfCredentialsDeleteSetOfCredentialsAndVerifyThatTheCredentialsAreNoLongerDisplayed() {
        this.loginPage.signup(driver);
        this.signupPage.setInputFirstName("TestUser5");
        this.signupPage.setInputLastName("Lastname");
        this.signupPage.setInputUsername("testuser5");
        this.signupPage.setInputPassword("admin5");
        this.signupPage.submit();

        this.loginPage.setInputUsername("testuser5");
        this.loginPage.setInputPassword("admin5");
        this.loginPage.submit();

        this.homePage.toCredentialTab();
        this.homePage.addCredential();
        this.homePage.addUrl("hsv.de");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("test1234");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.addCredential();
        this.homePage.addUrl("bild.de");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("hello20");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        this.homePage.addCredential();
        this.homePage.addUrl("udacity.com");
        this.homePage.addUsernameCredential("mickymouse");
        this.homePage.addCredentialPassword("Igel1234");
        this.homePage.credentialSubmit();

        this.resultPage.clickSuccessLink(driver);

        assertEquals(3, this.homePage.getRowsCredentialTable(driver));

        this.homePage.deleteCredential(1);
        this.resultPage.clickSuccessLink(driver);
        this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);
        this.homePage.deleteCredential(1);
		this.resultPage.clickSuccessLink(driver);

        assertTrue(this.homePage.idCredentialTableEmpty());

        this.homePage.logout();
    }

}
