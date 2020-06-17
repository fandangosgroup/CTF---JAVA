package com.github.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClientConsole {
	
	private String serverIP;
	private String porta;

	public SocketRequestControl game;
	private BufferedReader arquivo;
 
	public void StartGame() throws IOException {
		arquivo = new BufferedReader(new FileReader("endereço.txt"));
		String ip = arquivo.readLine();
		setServerIP(ip);
		String porta = arquivo.readLine();
		setPorta(porta);
		
		String serverip = getServerIP();
		System.out.println("Conectando em "+serverip +"....");
		
		this.game = new SocketRequestControl(serverip, Integer.parseInt(getPorta()));
		this.game.setConsole(true);
		this.game.executa();
	}
	
	public String getServerIP() {
		return serverIP;
	}
	
	public SocketRequestControl getGame() {
		return game;
	}
	
	public void setServerIP(String IP) {
		this.serverIP = IP;
	}
	
	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
