package com.telran.scheduler.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase {

    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (app.user().isLoginButtonPresent()) {
            app.user().defaultLogin();
        }
    }

    @Test
    public void testEventCreation() {

        app.event().tapOnPlusButton();
        app.event().tapOnPencil();

        app.event().fillEventCreationForm("Event", "1", 3, "150");
        app.event().TapOnAddEventButton();
    }

    @Test
    public void testEventCreationChangeDate() {

        app.event().tapOnPlusButton();
        app.event().tapOnPencil();

        app.event().selectDate("future", "FEBRUARY", "11");

        app.event().fillEventCreationForm("Event", "1", 0, "50");
        app.event().TapOnAddEventButton();

        Assert.assertTrue(app.event().isEventPresent());
    }
}
