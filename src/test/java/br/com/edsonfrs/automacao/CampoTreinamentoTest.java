package br.com.edsonfrs.automacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CampoTreinamentoTest {

    @Test
    public void deveInteragirComTextField() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste\n\n de\n\n escrita\n\n TextField");
        String texto = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

        Assertions.assertEquals("Teste de escrita TextField", texto );

        //driver.quit();
    }

    @Test
    public void deveInteragirComTextArea() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita TextArea");
        String texto = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");

        Assertions.assertEquals("Teste de escrita TextArea", texto );

        //driver.quit();
    }


}
