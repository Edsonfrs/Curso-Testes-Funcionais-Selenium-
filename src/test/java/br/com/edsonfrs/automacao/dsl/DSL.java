package br.com.edsonfrs.automacao.dsl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    public WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto) {
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obtemValorCampo(String id_campo) {
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    public void clicarRadio(String id) {
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String obterValorElemento(String id, String valor) {
         return driver.findElement(By.id(id)).getAttribute(valor);
    }

    public void clicarLink(String id) {
        driver.findElement(By.linkText(id)).click();
    }

    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }
}
