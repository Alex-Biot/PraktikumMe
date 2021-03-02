package com.telran.scheduler.tests;

import com.telran.scheduler.fw.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        app.init();
    }

    @BeforeMethod
    public void logMethodStart(Method m){
        logger.info("start method "+ m.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void logMethodStop(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: Test method " + result.getMethod().getMethodName());
        } else logger.error("FAILED: Test method" + result.getMethod().getMethodName());

    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        app.stop();
    }

}
