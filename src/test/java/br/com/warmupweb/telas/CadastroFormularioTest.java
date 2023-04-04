package br.com.warmupweb.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CadastroFormularioTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://warmupweb.com.br");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();

    }

    @Test
    public void CadastroPreenchimentoAll() throws InterruptedException {
        //Preenchimento de formulário de cadastro
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2700)", "");
        Thread.sleep(2000);
        driver.findElement(By.id("wpforms-25625-field_5")).sendKeys("Teste");
        driver.findElement(By.id("wpforms-25625-field_2")).sendKeys("testeautomatizado@yopmail.com");
        driver.findElement(By.id("wpforms-25625-field_9")).sendKeys("Teste Automatizado WarmUP");
        driver.findElement(By.id("wpforms-25625-field_3")).sendKeys("84990909090");
        Thread.sleep(3000);

        //Dropdown - Dinâmico
        driver.findElement(By.xpath("//div[@class='choices__item choices__placeholder choices__item--selectable']")).click();
        driver.findElement(By.xpath("//input[@aria-label='Célula de Testes']")).sendKeys("Automação de Testes");
        driver.findElement(By.id("choices--wpforms-25625-field_4-item-choice-3")).click();
        driver.findElement(By.id("wpforms-25625-field_6_1")).click();
        driver.findElement(By.id("wpforms-25625-field_7")).clear();
        driver.findElement(By.id("wpforms-25625-field_7")).sendKeys("Estão sendo realizados testes automatizados no formulário, obrigado.");
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(2000);
        driver.findElement(By.id("wpforms-submit-25625")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"wpforms-confirmation-25625\"]/p")).getText(), "Teste obrigado por entrar em contato conosco! Entraremos em contato contigo em breve.");
    }

    @Test
    public void CadastroSemPreenchimentoNome() throws InterruptedException {
        //Preenchimento de formulário de cadastro
        driver.findElement(By.xpath("//img[@alt='WarmUP']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2700)", "");
        Thread.sleep(2000);
        driver.findElement(By.id("wpforms-25625-field_2")).sendKeys("testeautomatizado@yopmail.com");
        driver.findElement(By.id("wpforms-25625-field_9")).sendKeys("Teste Automatizado WarmUP");
        driver.findElement(By.id("wpforms-25625-field_3")).sendKeys("84990909090");
        Thread.sleep(3000);

        //Dropdown - Dinâmico
        driver.findElement(By.xpath("//div[@class='choices__item choices__placeholder choices__item--selectable']")).click();
        driver.findElement(By.xpath("//input[@aria-label='Célula de Testes']")).sendKeys("Automação de Testes");
        driver.findElement(By.id("choices--wpforms-25625-field_4-item-choice-3")).click();

        driver.findElement(By.id("wpforms-25625-field_6_1")).click();
        driver.findElement(By.id("wpforms-25625-field_7")).sendKeys("Estão sendo realizados testes automatizados no formulário, obrigado.");
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(2000);
        driver.findElement(By.id("wpforms-submit-25625")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.id("wpforms-25625-field_5-error")).getText(), "Este campo é obrigatório.");
    }

}
