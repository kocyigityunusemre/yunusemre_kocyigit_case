package pages;

import config.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){ super(driver); }

    public void open(){
        driver.get(Variables.BASE_URL);
        acceptCookiesIfPresent();
    }

    public boolean isHomeOpened(){
        try {
            return isVisible(By.xpath(Variables.HOME_HERO_XP));
        } catch (Exception e){
            return title().toLowerCase().contains("insider");
        }
    }

    public CareersPage goToCareers(){
        click(By.xpath(Variables.COMPANY_MENU_XP));
        click(By.xpath(Variables.CAREERS_IN_MENU_XP));
        acceptCookiesIfPresent();
        return new CareersPage(driver);
    }
}
