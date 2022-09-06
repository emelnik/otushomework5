import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;


public class OtusLKTest {

    private WebDriver driver;

    private final Logger logger = LogManager.getLogger();

    Properties prop = System.getProperties();
    Object addProperties = prop.setProperty("base.url","https://otus.ru");
    String base_url = prop.getProperty("base.url");

    @BeforeClass
        public static void startDriver(){
            WebDriverManager.chromedriver().setup();
    }

    @After
    public void end(){
        if(driver != null){
            driver.quit();
        }
        logger.info("Драйвер успешно закрыт");
    }

    @Test
    public void otusLKTest() {

        startDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);

        MainPage mp = new MainPage(driver);

        mp.clickRegButton();

        getElement(mp.modalWindowLocator);

        mp.setEmailAndPassword();

        mp.clickSubmit();

        getElement(By.xpath(mp.menuXpathLocator));

        AboutMyselfPage amp = new AboutMyselfPage(driver);

        amp.actionsMoveToElement();

        amp.setFirstName();

        amp.setLastName();

        amp.setBlogName();

        amp.setDateOfBirth();

        amp.setCountryName();

        amp.setCityName();

        amp.setEnglishLevel();

        amp.setContact1();

        amp.setContact2();

        amp.clickButtonSave();

        driver.close();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);

        mp = new MainPage(driver);

        mp.clickRegButton();

        getElement(mp.modalWindowLocator);

        mp.setEmailAndPassword();

        mp.clickSubmit();

        getElement(By.xpath(amp.menuXpathLocator));

        amp = new AboutMyselfPage(driver);

        amp.actionsMoveToElement();

            String factName = driver.findElement(By.xpath("//input[@name='fname']")).getAttribute("value");
            Assert.assertEquals(FildsList.ВАСИЛИЙ.toString(),factName);

            String factNameLatin = driver.findElement(By.xpath("//input[@name='fname_latin']")).getAttribute("value");
            Assert.assertEquals(FildsList.VASILIY.toString(),factNameLatin);

            String factLastName = driver.findElement(By.xpath("//input[@name='lname']")).getAttribute("value");
            Assert.assertEquals(FildsList.ТЕСТИРОВИЧ.toString(),factLastName);

            String factLastNameL = driver.findElement(By.xpath("//input[@name='lname_latin']")).getAttribute("value");
            Assert.assertEquals(FildsList.TESTIROVICH.toString(),factLastNameL);

            String factNameBlog = driver.findElement(By.xpath("//input[@name='blog_name']")).getAttribute("value");
            Assert.assertEquals(FildsList.MELNIK.toString(),factNameBlog);

            String factBD = driver.findElement(By.xpath("//input[@name='date_of_birth']")).getAttribute("value");
            Assert.assertEquals("10.11.1980",factBD);

        }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
