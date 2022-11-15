package starter.utils;

import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NavigateActions {

    public String duckDuckGoUrl = "https://duckduckgo.com/";
    public String dromUrl = "https://auto.drom.ru/";
    public void openUrl(String url, WebDriver driver) {
        driver.get(url);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
