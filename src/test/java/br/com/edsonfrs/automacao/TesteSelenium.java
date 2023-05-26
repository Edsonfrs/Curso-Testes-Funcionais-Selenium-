package br.com.edsonfrs.automacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteSelenium {

    @Test
    public void deveAcessarPaginaGoogle(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com.br");
        //System.out.println(driver.getTitle());
        Assertions.assertEquals("Google", driver.getTitle());;


    }
}
