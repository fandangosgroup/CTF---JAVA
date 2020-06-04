package com.github.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
	
	private String serverIP;
	public SocketRequestControl game;
	public boolean status;

	public void StartGame() throws IOException {
		String serverip = getserverIP();
		System.out.println("Conectando em "+serverip +"....");
		
		this.game = new SocketRequestControl(serverip, 12345);
		this.status = this.game.executa();
	}
	
	public String getserverIP() {
		return serverIP;
	}
	
	public SocketRequestControl getGame() {
		return game;
	}
	
	public void setserverIP(String IP) {
		this.serverIP = IP;
	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
