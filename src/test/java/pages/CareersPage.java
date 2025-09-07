package pages;

import config.Variables;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CareersPage extends BasePage {

    public CareersPage(WebDriver driver){ super(driver); }


    public boolean ensureBlocksAndOpenTeams(){
        try {
            By seeAllTeams = By.xpath(Variables.SEE_ALL_TEAMS_BTN_XP);
            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 800);");
            visible(seeAllTeams);
            click(seeAllTeams);
            wait.until(ExpectedConditions.urlContains("/careers/"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public QAJobsPage goToQAJobs(){
        try {
            By qaTile = By.xpath(Variables.QA_TEAM_TILE_XP);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", driver.findElement(qaTile));
            click(qaTile);
        } catch (Exception ignored){
            driver.get(Variables.QA_LANDING_URL); // garanti
        }
        acceptCookiesIfPresent();
        return new QAJobsPage(driver);
    }
}
