package com.github.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClientConsole {
	
	private static String serverIP;
	private static String porta;

	public static SocketRequestControl game;
	private static BufferedReader arquivo;
	
	public static void main(String[] args) throws IOException, InterruptedException {
        StartGame();
    }
 
	public static void StartGame() throws IOException, InterruptedException {
		arquivo = new BufferedReader(new FileReader("endereço.txt"));
		String ip = arquivo.readLine();
		setServerIP(ip);
		String porta = arquivo.readLine();
		setPorta(porta);
		
		String serverip = getServerIP();
		System.out.println("Conectando em "+serverip +"....");
		
		game = new SocketRequestControl(serverip, Integer.parseInt(getPorta()));
		game.setConsole(true);
		game.executa();
		game.runGame();
	}
	
	public static String getServerIP() {
		return serverIP;
	}
	
	public SocketRequestControl getGame() {
		return game;
	}
	
	public static void setServerIP(String IP) {
		serverIP = IP;
	}
	
	public static String getPorta() {
		return porta;
	}

	public static void setPorta(String portaa) {
		porta = portaa;
	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
