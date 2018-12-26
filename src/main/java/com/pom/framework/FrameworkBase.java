package com.pom.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.pom.listeners.TestEventHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class FrameworkBase {

	WebDriver wbDriver;
	JavascriptExecutor js;
	static protected Logger log;
	public EventFiringWebDriver driver;
	TestEventHandler eventListener;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected String WORKINGDIR = System.getProperty("user.dir");
	public static Properties prop = new Properties();
	static {
		readProp();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		String logPath = System.getProperty("user.dir")+"/Log";
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		System.setProperty("logPath", logPath);
	}
	
	public static Properties readProp() {
		InputStream input = null;
		try {
			input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Prop.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
