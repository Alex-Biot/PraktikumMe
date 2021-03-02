package com.telran.scheduler.fw;


import com.telran.scheduler.model.User;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase {
    public UserHelper(AppiumDriver driver) {
        super(driver);
    }

    public void fillLoginForm(User user) {
        waitForElementAndType(By.id("log_email_input"), 5, user.getEmail());
        type(By.id("log_password_input"), user.getPassword());
    }

    public void tapLoginButton() {
        tap(By.id("login_btn"));
    }

    public boolean isLoginButtonPresent() {
        return isElementPresent(By.id("login_btn"));
    }

    public void defaultLogin() throws InterruptedException {
       fillLoginForm(new User()
                .withEmail("aa@gmail.com")
                .withPassword("Aa1234567"));
        hideKeyboard();
        tapLoginButton();
        Thread.sleep(5000);

    }

    public  void login(){
        fillLoginForm(new User()
                .withEmail("aa@gmail.com")
                .withPassword("Aa1234567"));
        hideKeyboard();
        tapLoginButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
