package com.github.client;

public class PrintConsole {
	private char[][] matriz;
	
	public void setMatriz(char[][] matriz) {
		this.matriz = matriz;
	}
	
	public void RenderPrintConsole() {
		int x,y;
			for(x = 0; x < 30; x++) {
				for(y = 0; y < 30; y++) {
					System.out.print(this.matriz[x][y]);
				}
				System.out.println(' ');
			}
	}
	
	public char[][] teste(char[][] data) {
		
		int x,y;
		for(x = 1; x < 29; x++) {
			for(y = 1; y <29; y++) {
			
				if(data[x][y] != '-') {
					data[x][y] = '-';
					return data;
				}
				
			}
		}
		
		return data;
	}
}
