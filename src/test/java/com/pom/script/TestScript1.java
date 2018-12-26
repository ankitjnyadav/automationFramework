package com.pom.script;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pom.framework.TestBase;

@Listeners(com.pom.listeners.TestListener.class)
public class TestScript1 extends TestBase{

	@Test
	void testMethod() {
		navigate(readProp().getProperty("url"));
		
		
	}
}
