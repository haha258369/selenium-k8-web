package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JSExecutor implements Urls {

    // Define selectors

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + floatingMenuSlug);
            Thread.sleep(1000);

            // Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            // Scroll to top
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
            Thread.sleep(1000);


        } catch (Exception e){
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }
}
