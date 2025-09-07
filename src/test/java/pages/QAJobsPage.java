package pages;

import config.Variables;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class QAJobsPage extends BasePage {

    public QAJobsPage(WebDriver driver){ super(driver); }

    public void clickSeeAll(){
        acceptCookiesIfPresent();

        try {
            if (driver.getCurrentUrl().startsWith(Variables.QA_LANDING_URL)) {
                By seeAll = By.xpath(Variables.SEE_ALL_QA_XP);
                if (isVisible(seeAll)) {
                    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", driver.findElement(seeAll));
                    click(seeAll);
                }
            }
        } catch (Exception ignored) {}

        driver.get(Variables.OPEN_POSITIONS_QA_TR_URL);
        acceptCookiesIfPresent();

        // Filtre şeridi gelmezse sadece QA
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Variables.DEP_TOGGLE_XP)));
        } catch (TimeoutException e) {
            driver.get(Variables.OPEN_POSITIONS_QA_URL);
            acceptCookiesIfPresent();
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Variables.DEP_TOGGLE_XP)));
            } catch (TimeoutException ignored) {}
        }
    }

    public void filterJobs(){
        try { visible(By.xpath(Variables.DEP_TOGGLE_XP)); } catch (TimeoutException ignored) {}
        if (hasAnyResult()) return;

        driver.get(Variables.OPEN_POSITIONS_QA_URL);
        acceptCookiesIfPresent();
        try { wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Variables.DEP_TOGGLE_XP))); }
        catch (TimeoutException ignored) {}
    }

    public boolean areFiltersApplied(){
        String u = url();
        boolean viaUrl =
                u.contains("department=qualityassurance") &&
                        (u.contains("location=istanbul-turkey") || u.contains("/open-positions/"));

        try {
            String depText = driver.findElement(By.xpath(Variables.DEP_TOGGLE_XP)).getText().toLowerCase();
            String locText = driver.findElement(By.xpath(Variables.LOC_TOGGLE_XP)).getText().toLowerCase();
            boolean viaUi = depText.contains("quality assurance") &&
                    (locText.contains("istanbul") || locText.contains("turkey"));
            return viaUrl || viaUi;
        } catch (Exception e) {
            return viaUrl;
        }
    }

    public boolean hasAnyResult(){
        List<WebElement> links = driver.findElements(By.xpath(Variables.JOB_VIEW_ROLE_BTNS_XP));
        return !links.isEmpty();
    }

    public JobDetailsPage openFirstJob(){
        List<WebElement> links = driver.findElements(By.xpath(Variables.JOB_VIEW_ROLE_BTNS_XP));
        if (links.isEmpty()) throw new RuntimeException("Görüntülenecek ilan bulunamadı (0 result).");

        String current = driver.getWindowHandle();
        int handleCountBefore = driver.getWindowHandles().size();

        WebElement first = links.get(0);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
        try { first.click(); }
        catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", first);
        }

        // Yeni sekme açıldıysa ona geç
        try {
            wait.until(d -> d.getWindowHandles().size() > handleCountBefore);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        } catch (Exception ignored) {

        }

        // Lever yüklenmesini bekle (url ya da apply-form elementi)
        try {
            wait.until(d -> d.getCurrentUrl().contains("jobs.lever.co")
                    || !d.findElements(By.xpath(Variables.LEVER_APPLY_BTN_XP)).isEmpty()
                    || !d.findElements(By.xpath(Variables.LEVER_FORM_XP)).isEmpty());
        } catch (TimeoutException ignored) {}

        acceptCookiesIfPresent();
        return new JobDetailsPage(driver);
    }
}
