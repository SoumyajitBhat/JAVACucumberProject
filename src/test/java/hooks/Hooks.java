package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    
    @Before
    public void beforeScenario() {
        System.out.println("Starting scenario");
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.youtube.com/");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
        System.out.println("Ending scenario");
        DriverManager.quitDriver();
    }

    /**
     * Take screenshot on test failure and attach to report
     */
    public void takeScreenshot(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        try {
            // Take screenshot as bytes
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            
            // Attach screenshot to Cucumber report
            scenario.attach(screenshot, "image/png", "Failure-Screenshot");

            // Also save to file for backup
            String screenshotDir = "target/screenshots";
            Files.createDirectories(Paths.get(screenshotDir));

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";
            String filePath = screenshotDir + "/" + fileName;

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), Paths.get(filePath));

            System.out.println("Screenshot taken and embedded in report: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
