package pages;

import config.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobDetailsPage extends BasePage {

    public JobDetailsPage(WebDriver driver){ super(driver); }

    public boolean isLeverFormOpened(){
        acceptCookiesIfPresent();

        boolean urlOk = url().contains("jobs.lever.co")
                || title().toLowerCase().contains("lever");

        boolean formOk =
                !driver.findElements(By.xpath(Variables.LEVER_APPLY_BTN_XP)).isEmpty()
                        || !driver.findElements(By.xpath(Variables.LEVER_FORM_XP)).isEmpty();

        return urlOk || formOk;
    }
}
