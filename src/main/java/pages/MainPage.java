package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public By modalWindowLocator = By.xpath("//div[@data-mode='login']");
    public String menuXpathLocator = "//p[contains(@class,'header2-menu__item-text__username')]";

    private final String email = System.getProperty("login.email");
    private final String password = System.getProperty("login.password");

    public void clickRegButton(){
        driver.findElement(By.xpath("//button[@data-modal-id='new-log-reg']"))
                .click();
    }

    public void setEmailAndPassword(){
        driver.findElement(By.xpath("//form[@action='/login/']//input[@name='email']"))
                .sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);
    }

    public MainPage clickSubmit(){
        driver.findElement(By.xpath("//form[@action='/login/']//button[@type='submit']"))
                .click();
        return new MainPage(driver);
    }

}
