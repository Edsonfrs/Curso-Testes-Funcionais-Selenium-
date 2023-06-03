package br.com.edsonfrs.automacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {

    @Test
    public void deveInteragirComAlertSimples() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();

        Assertions.assertEquals("Alert Simples", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);

        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirmOk() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();

        Assertions.assertEquals("Confirm Simples", texto);
        alert.accept();
        alert.accept();


        driver.quit();

    }

    @Test
    public void deveInteragirComAlertConfirmCancelado() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert =  driver.switchTo().alert();
        String texto = alert.getText();

        Assertions.assertEquals("Confirm Simples", texto);
        alert.dismiss();

        String texto2 = alert.getText();
        Assertions.assertEquals("Negado", texto2);
        alert.accept();

        driver.quit();

    }

    @Test
    public void deveInteragirComPromptOK() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        driver.findElement(By.id("prompt")).click();
        Alert alert =  driver.switchTo().alert();
        alert.sendKeys("31");
        alert.accept();
        String texto = alert.getText();

        Assertions.assertEquals("Era 31?", texto);
        alert.accept();

        String texto2 = alert.getText();
        Assertions.assertEquals(":D", texto2);
        alert.accept();

        driver.quit();
    }

    @Test
    public void deveInteragirComPromptCancelado1() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        // Achando botão do prompt e clicando no mesmo
        driver.findElement(By.id("prompt")).click();
        // Alternando para alert / cancelando a operação / pegando o texto do alerta
        Alert alert =  driver.switchTo().alert();
        alert.dismiss();
        String texto = alert.getText();
        // Validando texto do alert com texto esperado
        Assertions.assertEquals("Era null?", texto);
        alert.accept();
        String texto2 = alert.getText();

        Assertions.assertEquals(":D", texto2);
        alert.accept();

        driver.quit();
    }

    @Test
    public void deveInteragirComPromptCancelado2() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");

        // Achando botão do prompt e clicando no mesmo
        driver.findElement(By.id("prompt")).click();
        // Alternando para alert / cancelando a operação / pegando o texto do alerta
        Alert alert =  driver.switchTo().alert();
        alert.dismiss();
        String texto = alert.getText();

        // Validando texto do alert com texto esperado
        Assertions.assertEquals("Era null?", texto);
        alert.dismiss();
        String texto2 = alert.getText();

        Assertions.assertEquals(":(", texto2);
        alert.accept();



        driver.quit();
    }

}
