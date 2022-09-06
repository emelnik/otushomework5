import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    By modalWindowLocator = By.xpath("//div[@data-mode='login']");
    public String menuXpathLocator = "//p[contains(@class,'header2-menu__item-text__username')]";

    private final String email = "sahis54797@altpano.com";
    private final String password = "Qaz123456qaz!";

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
