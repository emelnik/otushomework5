import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutMyselfPage {

    private WebDriver driver;

    public AboutMyselfPage(WebDriver driver) {
        this.driver = driver;
    }

    public String menuXpathLocator = "//p[contains(@class,'header2-menu__item-text__username')]";

    public void actionsMoveToElement(){
        WebElement menu = driver.findElement(By.xpath(menuXpathLocator));
        By aboutMyselfLocator = By.xpath("//h3[contains(text(),'Персональные данные')]");
        new Actions(driver).moveToElement(menu).moveToElement(driver
                .findElement(By.xpath("//*[text()[contains(.,'Мой профиль')]]")))
                .click()
                .build()
                .perform();
        getElement(aboutMyselfLocator);
    }

    public void setFirstName(){
        driver.findElement(By.xpath("//input[@name='fname']")).clear();
        driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(FildsList.ВАСИЛИЙ.toString());
        driver.findElement(By.xpath("//input[@name='fname_latin']")).clear();
        driver.findElement(By.xpath("//input[@name='fname_latin']")).sendKeys(FildsList.VASILIY.toString());

    }

    public void setLastName(){
        driver.findElement(By.xpath("//input[@name='lname']")).clear();
        driver.findElement(By.xpath("//input[@name='lname']")).sendKeys(FildsList.ТЕСТИРОВИЧ.toString());
        driver.findElement(By.xpath("//input[@name='lname_latin']")).clear();
        driver.findElement(By.xpath("//input[@name='lname_latin']")).sendKeys(FildsList.TESTIROVICH.toString());
    }

    public void setBlogName(){
        driver.findElement(By.xpath("//input[@name='blog_name']")).clear();
        driver.findElement(By.xpath("//input[@name='blog_name']")).sendKeys(FildsList.MELNIK.toString());
    }

    public void setDateOfBirth(){
        WebElement bd = driver.findElement(By.xpath("//input[@name='date_of_birth']"));

        driver.findElement(By.xpath("//input[@name='date_of_birth']")).clear();
        driver.findElement(By.xpath("//input[@name='date_of_birth']")).click();
        bd.sendKeys("10.11.1980");
        bd.sendKeys(Keys.ENTER);
    }

    public void setCountryName(){
        driver.findElement(By.xpath("//div[@data-slave-selector]/label/div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Россия')]")).click();
    }

    public void setCityName(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement city = driver.findElement(By.xpath("//input[@data-title='Город']"));
        jse.executeScript("arguments[0].removeAttribute('disabled')", city);
        driver.findElement(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div")).click();
        getElement(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div"));
        getClckableElement(By.xpath("//button[contains(text(),'Москва')]"));
        driver.findElement(By.xpath("//button[contains(text(),'Москва')]")).click();
    }

    public void setEnglishLevel(){
        driver.findElement(By.xpath("//input[@name='english_level']/following::div[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Начальный уровень (Beginner)')]")).click();
    }

    public void setContact1(){
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//input[@name='contact-0-service']/following::div[1]")).click();
        getClckableElement(By.xpath("//button[normalize-space()='WhatsApp']"));
        driver.findElement(By.xpath("//button[normalize-space()='WhatsApp']")).click();
        driver.findElement(By.xpath("//input[@id='id_contact-0-value']")).clear();
        driver.findElement(By.xpath("//input[@id='id_contact-0-value']")).sendKeys("+7 (777) 777-77-77");
    }

    public void setContact2(){
        driver.findElement(By.xpath("//input[@name='contact-1-service']/following::div[1]")).click();
        getClckableElement(By.xpath("(//button[@data-value='telegram'])[2]"));
        driver.findElement(By.xpath("(//button[@data-value='telegram'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id='id_contact-1-value'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@id='id_contact-1-value'])[1]")).sendKeys("+7 (777) 777-77-77");
    }

    public void clickButtonSave(){
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и заполнить позже')]")).click();
    }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement getClckableElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
