/*
 * @author : MuthuKalaiyarasi T
 */

package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the ThreadLocal driver based on the given browser.
     *
     * @param browser
     * @return the WebDriver instance
     */
    public WebDriver init_driver(String browser) {
        System.out.println("Initializing browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        } 
        else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        // Configure the driver
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    /**
     * This method retrieves the WebDriver instance from ThreadLocal.
     *
     * @return the WebDriver instance
     */
    public static synchronized WebDriver getDriver() {
        if (tlDriver.get() == null) {
            throw new IllegalStateException("Driver has not been initialized. Please call init_driver() first.");
        }
        return tlDriver.get();
    }

    /**
     * This method is used to close the driver and clean up the ThreadLocal instance.
     */
    public static void closeDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
