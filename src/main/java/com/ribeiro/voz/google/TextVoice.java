package com.ribeiro.voz.google;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TextVoice {

	private static TextVoice instance;
	private static ChromeOptions options = new ChromeOptions();
	private static WebDriver driver = null;
	
	private TextVoice() {
		
		// Localização Driver
		//System.setProperty("webdriver.chrome.driver", "C:/temp/VoiceText/driver/chromedriver.exe");
		
		// Esconder Browser
		options.addArguments("--headless");
		
		// Nova Instancia do Google Chrome
		driver = new ChromeDriver(options);
		
		// Navegue até o Google Translate
		Navigation nav = driver.navigate();
		nav.to("https://translate.google.com/?hl=pt-BR&sl=pt&tl=pt&op=translate");
		
	}
	
	public static TextVoice getInstance() {
		
		if (instance == null) {			
			instance = new TextVoice();			
		}
		
		return instance;
	}
	
	
	public void textToVoice(String var) {
		try {
			
			WebElement textArea = driver.findElement(By.tagName("textarea"));
			textArea.clear();
			textArea.sendKeys(var);
			
			// Aguarde o botão ficar disponivel
			while(driver.getPageSource().indexOf("VfPpkd-Bz112c-LgbsSe fzRBVc tmJved SSgGrd m0Qfkd") == -1) {Thread.sleep(1000);}
			
			// Pega a Lista de botões disponiveis
			List<WebElement> buttonList = driver.findElements(By.tagName("button"));
			
			for(WebElement button : buttonList) {
				
				// Clica no botão
				if(button.getText().contains("volume_up")) {
					button.click();
					break;
				}
				
			}
			
			// Tempo de espera para próxima solicitação
			long waiT = var.split(" ").length * 500;
			Thread.sleep(waiT + 2000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
