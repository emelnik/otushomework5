package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        WebElement elementInputFirstNameRus = driver.findElement(By.xpath("//input[@name='fname']"));
        WebElement elementInputFirstNameLatin = driver.findElement(By.xpath("//input[@name='fname_latin']"));
        elementInputFirstNameRus.clear();
        elementInputFirstNameRus.sendKeys(FildsList.ВАСИЛИЙ.toString());
        elementInputFirstNameLatin.clear();
        elementInputFirstNameLatin.sendKeys(FildsList.VASILIY.toString());
    }

    public void setLastName(){
        WebElement elementInputLastNameRus = driver.findElement(By.xpath("//input[@name='lname']"));
        WebElement elementInputLastNameLatin = driver.findElement(By.xpath("//input[@name='lname_latin']"));
        elementInputLastNameRus.clear();
        elementInputLastNameRus.sendKeys(FildsList.ТЕСТИРОВИЧ.toString());
        elementInputLastNameLatin.clear();
        elementInputLastNameLatin.sendKeys(FildsList.TESTIROVICH.toString());
    }

    public void setBlogName(){
        WebElement elementBlogName = driver.findElement(By.xpath("//input[@name='blog_name']"));
        elementBlogName.clear();
        elementBlogName.sendKeys(FildsList.MELNIK.toString());
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        dateOfBirth = dateOfBirth.minusYears(18);
        WebElement elementDateOfBirth = driver.findElement(By.xpath("//input[@name='date_of_birth']"));
        elementDateOfBirth.clear();
        elementDateOfBirth.click();
        elementDateOfBirth.sendKeys(dateOfBirth.toString());
        elementDateOfBirth.sendKeys(Keys.ENTER);
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

    public void clearOldContact(){
        List<WebElement> listButtonDelete = driver
                .findElements(By.xpath("//div[contains(@class,'container__col_md-0')]//button[contains(text(),'Удалить')]"));
        if(listButtonDelete != null){
            for (WebElement list:listButtonDelete) {
                list.click();
            }
        }
    }

    public void setContact(String contactType, String contactNumber){

        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]"))
                .click();
        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]"))
                .click();
        getClckableElement(By.xpath("(//button[@data-value=\"" + contactType + "\"])[last()]"));
        driver.findElement(By.xpath("(//button[@data-value=\"" + contactType + "\"])[last()]"))
                .click();
        WebElement elementContact = driver
                .findElement(By.xpath("(//div[contains(@class, 'container__col_12')]/input[contains(@name,'value')])[last()]"));
        elementContact
                .clear();
        elementContact
                .sendKeys(contactNumber);
    }

    public void clickButtonSave(){
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и заполнить позже')]")).click();
    }

    public void assertFactName(){
        String factName = driver.findElement(By.xpath("//input[@name='fname']")).getAttribute("value");
        Assert.assertEquals(FildsList.ВАСИЛИЙ.toString(),factName);
    }

    public void assertFactNameLatin(){
        String factNameLatin = driver.findElement(By.xpath("//input[@name='fname_latin']")).getAttribute("value");
        Assert.assertEquals(FildsList.VASILIY.toString(),factNameLatin);
    }

    public void assertFactLastName(){
        String factLastName = driver.findElement(By.xpath("//input[@name='lname']")).getAttribute("value");
        Assert.assertEquals(FildsList.ТЕСТИРОВИЧ.toString(),factLastName);
    }

    public void assertFactLastNameLatin(){
        String factLastNameL = driver.findElement(By.xpath("//input[@name='lname_latin']")).getAttribute("value");
        Assert.assertEquals(FildsList.TESTIROVICH.toString(),factLastNameL);
    }

    public void assertFactNameBlog(){
        String factNameBlog = driver.findElement(By.xpath("//input[@name='blog_name']")).getAttribute("value");
        Assert.assertEquals(FildsList.MELNIK.toString(),factNameBlog);
    }

    public void assertFactBD(){
        String factBD = driver.findElement(By.xpath("//input[@name='date_of_birth']")).getAttribute("value");
        Assert.assertEquals(LocalDate.now().minusYears(18).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),factBD);
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
