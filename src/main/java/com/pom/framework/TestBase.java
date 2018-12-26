package com.pom.framework;



import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.pom.listeners.TestEventHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends FrameworkBase{

	@BeforeSuite
	public void setup() {
		DOMConfigurator.configure("log4j.xml");
		log = Logger.getLogger(this.getClass());
	}
	@BeforeMethod
	public EventFiringWebDriver config() {
		String browser = prop.getProperty("browser");
		if(browser.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			wbDriver=new FirefoxDriver();
			log.info("FF Driver initialized");
		}else if(browser.equals("Chrome")) {
			WebDriverManager.chromedriver();
			wbDriver=new ChromeDriver();
			log.info("Chrome Driver initialized");
		}
		
		driver = new EventFiringWebDriver(wbDriver);
		eventListener = new TestEventHandler();
		driver.register(eventListener);
		
		js = (JavascriptExecutor)wbDriver;
		wbDriver.manage().window().maximize();
		return driver;
		
	}
	//@Parameter-optional
	public void navigate(String url) {
		driver.navigate().to(url);
		log.info("URL launched successfully");
	}
	
}
