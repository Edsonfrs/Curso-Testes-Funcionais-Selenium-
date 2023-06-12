package br.com.edsonfrs.automacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegrasDeNegociosTest {

    @Test
    public void deveValidarNomeObrigatorio() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        String texto = driver.findElement(By.id("elementosForm:nome")).getText();

        Assertions.assertEquals("Nome eh obrigatorio", textAlert);

        driver.quit();
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bram");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();

        Assertions.assertEquals("Sobrenome eh obrigatorio", textAlert);

        driver.quit();
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bram");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Stoker");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();

        alert.accept();

        Assertions.assertEquals("Sexo eh obrigatorio", textAlert);

        driver.quit();
    }

    @Test
    public void deveValidarComidaFavorita() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bram");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Stoker");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();

        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", textAlert);

        driver.quit();
    }

    @Test
    public void deveValidarSeUsuarioSabeOQueEhEsporte() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bram");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Stoker");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo2 = new Select(element);
        combo2.selectByVisibleText("Natacao");
        combo2.selectByVisibleText("O que eh esporte?");
        List<WebElement> options = combo2.getAllSelectedOptions();

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();

        Assertions.assertEquals("Voce faz esporte ou nao?", textAlert);

        driver.quit();
    }


}
