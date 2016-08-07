package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Credit {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        baseUrl = "http://finance.liga.net";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCredit() throws Exception {
        driver.get(baseUrl + "/finmarket/calculators.htm");
        driver.findElement(By.id("txtSumOfCredit")).clear();
        driver.findElement(By.id("txtSumOfCredit")).sendKeys("1000000");
        driver.findElement(By.id("credit_count_months")).clear();
        driver.findElement(By.id("credit_count_months")).sendKeys("36");
        driver.findElement(By.id("percent_credit")).clear();
        driver.findElement(By.id("percent_credit")).sendKeys("12");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Классический (% на остаток задолженности)");
        driver.findElement(By.cssSelector("#credit_form > input.btn")).click();
        Assert.assertEquals(driver.findElement(By.id("credit_result2")).getText().replaceAll("\\s", " "), "Ежемесячный платеж по кредиту первый месяц 37777.78 последний месяц 28055.56");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}
