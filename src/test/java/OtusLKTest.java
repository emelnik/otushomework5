import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OtusLKTest {

    private WebDriver driver;
    private WebDriver driverClean;

    private final Logger logger = LogManager.getLogger();

    private String url = "https://otus.ru";
    private String name = "Василий";
    private String nameL = "Vasya";
    private String lastName = "Тестирович";
    private String lastNameL = "Testirovich";
    private String nameBlog = "melnik";
    private final String email = "sahis54797@altpano.com";
    private final String password = "Qaz123456qaz!";
    private final String menuXpath = "//p[contains(@class,'header2-menu__item-text__username')]";

    By modalWindow = By.xpath("//div[@data-mode='login']");
    By aboutMyself = By.xpath("//h3[contains(text(),'Персональные данные')]");

    @BeforeClass
        public static void startDriver(){
            WebDriverManager.chromedriver().setup();
    }

    @After
    public void End(){
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
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        driver.get(url);

        driver.findElement(By.xpath("//button[@data-modal-id='new-log-reg']"))
                .click();
        getElementForCreateData(modalWindow);
        driver.findElement(By.xpath("//form[@action='/login/']//input[@name='email']"))
                .sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);

        driver.findElement(By.xpath("//form[@action='/login/']//button[@type='submit']"))
                .click();
        getElementForCreateData(By.xpath(menuXpath));

        WebElement menu = driver.findElement(By.xpath(menuXpath));

        new Actions(driver).moveToElement(menu).moveToElement(driver
                .findElement(By.xpath("//*[text()[contains(.,'Мой профиль')]]")))
                .click()
                .build()
                .perform();
        getElementForCreateData(aboutMyself);

        driver.findElement(By.xpath("//input[@name='fname']")).clear();
        driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='fname_latin']")).sendKeys(nameL);

        driver.findElement(By.xpath("//input[@name='lname']")).clear();
        driver.findElement(By.xpath("//input[@name='lname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='lname_latin']")).sendKeys(lastNameL);

        driver.findElement(By.xpath("//input[@name='blog_name']")).clear();
        driver.findElement(By.xpath("//input[@name='blog_name']")).sendKeys(nameBlog);

        WebElement bd = driver.findElement(By.xpath("//input[@name='date_of_birth']"));

        driver.findElement(By.xpath("//input[@name='date_of_birth']")).clear();
        driver.findElement(By.xpath("//input[@name='date_of_birth']")).click();
        bd.sendKeys("10.11.1980");
        bd.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//div[@data-slave-selector]/label/div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Россия')]")).click();

        WebElement city = driver.findElement(By.xpath("//input[@data-title='Город']"));
        jse.executeScript("arguments[0].removeAttribute('disabled')", city);
        driver.findElement(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div")).click();
        getElementForCreateData(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div"));
        getClckableElement(By.xpath("//button[contains(text(),'Москва')]"));
        driver.findElement(By.xpath("//button[contains(text(),'Москва')]")).click();

        driver.findElement(By.xpath("//input[@name='english_level']/following::div[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Начальный уровень (Beginner)')]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//input[@name='contact-0-service']/following::div[1]")).click();
        getClckableElement(By.xpath("//button[normalize-space()='WhatsApp']"));
        driver.findElement(By.xpath("//button[normalize-space()='WhatsApp']")).click();
        driver.findElement(By.xpath("//input[@id='id_contact-0-value']")).sendKeys("+7 (777) 777-77-77");

        driver.findElement(By.xpath("//input[@name='contact-1-service']/following::div[1]")).click();
        getClckableElement(By.xpath("(//button[@data-value='telegram'])[2]"));
        driver.findElement(By.xpath("(//button[@data-value='telegram'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id='id_contact-1-value'])[1]")).sendKeys("+7 (777) 777-77-77");

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и заполнить позже')]")).click();

        if(driver != null){
            driver.quit();
        }

        driverClean = new ChromeDriver();
        driverClean.manage().window().maximize();
        driverClean.get(url);

        driverClean.findElement(By.xpath("//button[@data-modal-id='new-log-reg']"))
                .click();

        getElementNew(modalWindow);

        driverClean.findElement(By.xpath("//form[@action='/login/']//input[@name='email']"))
                .sendKeys(email);
        driverClean.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);

        driverClean.findElement(By.xpath("//form[@action='/login/']//button[@type='submit']"))
                .click();
        getElementNew(By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']"));

        WebElement menu2 = driverClean.findElement(By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']"));

        new Actions(driverClean).moveToElement(menu2).moveToElement(driverClean
                    .findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")))
                    .click()
                    .build()
                    .perform();
            getElementNew(aboutMyself);

            String factName = driverClean.findElement(By.xpath("//input[@name='fname']")).getAttribute("value");
            Assert.assertEquals(name,factName);

            String factNameLatin = driverClean.findElement(By.xpath("//input[@name='fname_latin']")).getAttribute("value");
            Assert.assertEquals(nameL,factNameLatin);

            String factLastName = driverClean.findElement(By.xpath("//input[@name='lname']")).getAttribute("value");
            Assert.assertEquals(lastName,factLastName);

            String factLastNameL = driverClean.findElement(By.xpath("//input[@name='lname_latin']")).getAttribute("value");
            Assert.assertEquals(lastNameL,factLastNameL);

            String factNameBlog = driverClean.findElement(By.xpath("//input[@name='blog_name']")).getAttribute("value");
            Assert.assertEquals(nameBlog,factNameBlog);

            String factBD = driverClean.findElement(By.xpath("//input[@name='date_of_birth']")).getAttribute("value");
            Assert.assertEquals("10.11.1980",factBD);

        }

    private WebElement getElementForCreateData(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement getElementNew(By locator){
        WebDriverWait wait = new WebDriverWait(driverClean, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement getClckableElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
