package br.com.edsonfrs.automacao.inicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteSelenium {

    @Test
    public void deveAcessarPaginaGoogle(){
        //System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        // Definindo posição e tamanho da janela do navegador
        //driver.manage().window().setPosition(new Point(1, 1));
        //driver.manage().window().setSize(new Dimension(600, 383));
        //driver.manage().window().maximize(); // Tela maximizada

        driver.get("https://www.google.com.br");
        //System.out.println(driver.getTitle());
        Assertions.assertEquals("Google", driver.getTitle());

        driver.quit();


    }
}
