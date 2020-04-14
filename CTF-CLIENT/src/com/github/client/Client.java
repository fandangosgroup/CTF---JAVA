package com.github.client;

import java.io.IOException;

public class Client {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		new SocketRequestControl("127.0.0.1", 12345).executa();
		
	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
