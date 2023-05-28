package br.com.edsonfrs.automacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CampoTreinamentoTest {

    @Test
    public void deveInteragirComTextField() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste\n\n de\n\n escrita\n\n TextField");
        String texto = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

        Assertions.assertEquals("Teste de escrita TextField", texto );

        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita TextArea");
        String texto = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");

        Assertions.assertEquals("Teste de escrita TextArea", texto );

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        String texto = driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value");

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        String texto = driver.findElement(By.id("elementosForm:comidaFavorita:2")).getAttribute("value");

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComCombo() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Mestrado");
        //combo.selectByIndex(5);
        //combo.selectByValue("superior");
        String texto = combo.getFirstSelectedOption().getText();

        Assertions.assertEquals("Mestrado",texto);

        driver.quit();
    }
    @Test
    public void deveVerificarValoresDoCombo() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();

        //Checando a quantidade de opções do combo
        Assertions.assertEquals(8, options.size());

        //Checando se um determinado status do combo foi encontrado
        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
            }
        }
        Assertions.assertTrue(encontrou);

        driver.quit();
    }




}
