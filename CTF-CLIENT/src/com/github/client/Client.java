package com.github.client;

import java.io.IOException;

public class Client {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//new SocketRequestControl("127.0.0.1", 12345).executa();
		char[][] data = new char[30][30];
	       InputController input = new InputController();
	       MatrixController matrix = new MatrixController(data);
			int x,y;
			for(x = 0; x < 30; x++) {
				for(y = 0; y <30; y++) {
					if(x == 0 || y == 0) {
						data[x][y] = '#';
					}
					if(x == 29 || y == 29) {
						data[x][y] = '#';
					}

				}
			}
	                data[10][10] = 'X';
			//request.getServerData(data);
			boolean GameOver = false;
			PrintConsole print = new PrintConsole();
			while(!GameOver) {
	                        data = matrix.dataManipulation(input);
				CleanScreen();
				print.setMatriz(data);
				print.RenderPrintConsole(true);
			}
	}
	
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}
