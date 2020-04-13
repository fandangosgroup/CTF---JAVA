package com.github.client;

import java.io.IOException;

public class Ctf {

	public static void main(String[] args) throws InterruptedException, IOException {
		//InputController input = new InputController();
		
		SocketRequest request = new SocketRequest();
		
		char[][] data = new char[30][30];
		int x,y;
		for(x = 0; x < 30; x++) {
			for(y = 0; y <30; y++) {
				//if(x == y) {
					//data[x][29 - y] = '$';
				//}
				//if(x == 15) {
					//data[x][y] = '-';
				//}
				//if(y == 15) {
					//data[x][y] = '-';
				//}
				if(x == 0 || y == 0) {
					data[x][y] = '#';
				}
				if(x == 29 || y == 29) {
					data[x][y] = '#';
				}
				
				//if(y == x) {
					//data[x][y] = 'H';
				//}
			}
		}
		
		//request.getServerData(data);
		boolean GameOver = false;
		PrintConsole print = new PrintConsole();
		while(!GameOver) {
			//Thread.sleep();
			CleanScreen();
			data = print.teste(data);
			print.setMatriz(data);
			print.RenderPrintConsole(true);
			
		}

	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
