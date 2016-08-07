package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Deposit {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        baseUrl = "http://finance.liga.net";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testDeposit() throws Exception {
        driver.get(baseUrl + "/finmarket/calculators.htm");
        driver.findElement(By.id("txtSumOfDep")).clear();
        driver.findElement(By.id("txtSumOfDep")).sendKeys("500000");
        driver.findElement(By.id("count_months")).clear();
        driver.findElement(By.id("count_months")).sendKeys("12");
        driver.findElement(By.id("percent_dep")).clear();
        driver.findElement(By.id("percent_dep")).sendKeys("25");
        new Select(driver.findElement(By.id("ddl2"))).selectByVisibleText("Выплата процентов в конце срока");
        driver.findElement(By.cssSelector("input.btn")).click();
        Assert.assertEquals(driver.findElement(By.id("deposit_result1")).getText().replaceAll("\\s", " "), "Сумма процентов, начисленных за один период 125000.00");
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
