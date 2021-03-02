package com.telran.scheduler.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class EventHelper extends HelperBase {
    public EventHelper(AppiumDriver driver) {
        super(driver);
    }

    public void tapOnPlusButton() {
        tap(By.id("fab_main"));

    }

    public void fillEventCreationForm(String title, String type, int breaks, String wage) {

        type(By.id("info_title_input"), title);
        type(By.id("info_type_input"), type);
        hideKeyboard();
        if (breaks > 0) {
            for (int i = 0; i < breaks; i++) {
                tapOnAddBreakButton();
            }
        }
        addWage(wage);
    }

    private void addWage(String wage) {
        tap(By.id("info_wage_edit"));
        type(By.id("info_wage_input"), wage);
        tap(By.id("info_wage_save"));
    }

    public void tapOnAddBreakButton() {
        tap(By.id("info_break_plus_btn"));
    }

    public void TapOnAddEventButton() {
        tap(By.id("info_save_btn"));
    }

    public void tapOnPencil() {
        tap(By.id("fab_add_event"));
    }

    public boolean isEventPresent() {
        waitForElement(By.id("row_container_main"), 20);
        return isElementPresent(By.id("row_container_main"));
    }

    public int getEventCountByMonth(String month) {

        String displayedmonth = driver.findElement(By.id("nav_month_name")).getText();
        while (!displayedmonth.equals(month.toUpperCase())) {
            tap(By.id("nav_next_img"));
            displayedmonth = driver.findElement(By.id("nav_month_name")).getText();
        }

        return driver.findElements(By.id("row_container_main")).size();
    }

    public void selectDate(String type, String month, String dayOfMonth) {
        if (!getSelectedMonth().equals(month)) {
            if (type.equals("past")) {
                swipeToRightUntilNeededMonth(month);

                if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
                    swipeToRightUntillNeededDayOfMonth(dayOfMonth);
                }

            } else if (type.equals("future")) {
                swipeToLeftUntilNeededMonth(month);
                if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
                    swipeToLeftUntillNeededDayOfMonth(dayOfMonth);
                }
            }
        } else if (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            if (type.equals("past")) {
                swipeToRightUntillNeededDayOfMonth(dayOfMonth);
            } else if (type.equals("future")) {
                swipeToLeftUntillNeededDayOfMonth(dayOfMonth);
            }
        }
    }


    private void swipeToLeftUntillNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    private void swipeToRightUntillNeededDayOfMonth(String dayOfMonth) {
        while (!getSelectedDayOfMonth().equals(dayOfMonth)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedDayOfMonth();
        }
    }

    private void swipeToRightUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }

    private void moveElementToRight(By locator) {
        TouchAction action = new TouchAction(driver);
        //get activity points
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.5);

//get Element's point
        WebElement element = driver.findElement(locator);

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .longPress(PointOption.point(leftPoint, middleY))
                .moveTo(PointOption.point(rightPoint, middleY))
                .release()
                .perform();
    }

    public void moveElementToLeft(By locator) {
        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.5);

        WebElement element = driver.findElement(locator);

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .longPress(PointOption.point(rightPoint, middleY))
                .moveTo(PointOption.point(leftPoint, middleY))
                .release()
                .perform();
    }

    private void swipeToLeftUntilNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedMonth();
        }

    }

    private String getSelectedMonth() {
        WebElement selectedDate = driver.findElement(By.id("date_container_layout"));
        return selectedDate.findElement(By
                .xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_month_txt']"))
                .getText();
    }

    private String getSelectedDayOfMonth() {
        WebElement selectedDate = driver.findElement(By.id("date_container_layout"));
        return selectedDate.findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']"))
                .getText();
    }

}
