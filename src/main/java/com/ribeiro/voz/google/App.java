package com.ribeiro.voz.google;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class App {
	
	//private static boolean imprimir;
	
	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",dir + "/driver/chromedriver.exe");
		TextVoice t = TextVoice.getInstance();
		
		try {
			
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(9090);
			
			while(true) {
				//System.out.println("Aguardando Conexão");
				//System.out.println("Conexão Aceita");
				Socket client = server.accept();
				
				//System.out.println("Mensagem Recebida");
				InputStream r = client.getInputStream();
				String voice_text = new String(r.readAllBytes(), StandardCharsets.UTF_8);
				//System.out.println("Chama função voz");
				t.textToVoice(voice_text);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro > " + e.getMessage());
		}
		
	}
	
}
