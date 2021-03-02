package com.telran.scheduler.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void testLogin() throws InterruptedException {
        app.user().login();
        Thread.sleep(5000);

        Assert.assertFalse(app.user().isLoginButtonPresent());
    }
}
