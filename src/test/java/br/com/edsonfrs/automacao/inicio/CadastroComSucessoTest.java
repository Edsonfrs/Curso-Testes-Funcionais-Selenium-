package br.com.edsonfrs.automacao.inicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CadastroComSucessoTest {

    @Test
    public void deveRealizarUmCadastroComSucesso() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bram");
        //String nome = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Stoker");
        //String sobrenome = driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value");

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        //String sexo = driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value");

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        //String comida = driver.findElement(By.id("elementosForm:comidaFavorita:0")).getAttribute("value");

        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Superior");
        //String formacao = combo.getFirstSelectedOption().getText();

        WebElement elemento2 = driver.findElement(By.id("elementosForm:esportes"));
        Select combo2 = new Select(elemento2);
        combo2.selectByVisibleText("Natacao");
        combo2.selectByVisibleText("Karate");
        List<WebElement> options = combo2.getAllSelectedOptions();

        WebElement botao = driver.findElement(By.id("elementosForm:cadastrar"));
        botao.click();

        Assertions.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Bram"));
        Assertions.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Stoker"));
        Assertions.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
        Assertions.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Carne"));
        Assertions.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
        Assertions.assertEquals(2, options.size());
        Assertions.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Natacao Karate"));

        driver.quit();
    }
}
