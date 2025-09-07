package pages;

import config.Variables;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait  = new WebDriverWait(driver, Duration.ofSeconds(Variables.EXPLICIT_WAIT));
    }

    protected WebElement visible(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**  Click */
    protected void click(By by){
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        try {
            el.click();
        } catch (ElementClickInterceptedException e1) {
            try {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
                new Actions(driver).moveToElement(el).pause(Duration.ofMillis(120)).perform();
                el.click();
            } catch (Exception e2) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", el);
            }
        }
    }

    protected boolean isVisible(By by){
        try { visible(by); return true; } catch (TimeoutException e){ return false; }
    }

    public String url(){ return driver.getCurrentUrl(); }
    public String title(){ return driver.getTitle(); }

    /** Cookie banner: önce ana sayfa, yoksa tüm iframelerde ara; bulunursa JS ile tıkla. */
    protected void acceptCookiesIfPresent(){
        By xp = By.xpath(Variables.ACCEPT_ALL_COOKIES_XP);

        if (tryClickCookieInCurrentContext(xp)) return;

        // 2) ıFrame Kontrol
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        for (WebElement f : frames){
            try {
                driver.switchTo().frame(f);
                if (tryClickCookieInCurrentContext(xp)) {
                    driver.switchTo().defaultContent();
                    return;
                }
            } catch (Exception ignored) {
            } finally {
                try { driver.switchTo().defaultContent(); } catch (Exception ignored2) {}
            }
        }
    }

    private boolean tryClickCookieInCurrentContext(By xp){
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(6));
            WebElement btn = shortWait.until(ExpectedConditions.presenceOfElementLocated(xp));
            if (!btn.isDisplayed()) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].style.display='block';", btn);
            }
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            try { btn.click(); }
            catch (Exception e) { ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn); }
            // banner kapanışını kısa bekle
            try { Thread.sleep(250); } catch (InterruptedException ignored) {}
            return true;
        } catch (TimeoutException notFound){
            return false;
        }
    }
}
