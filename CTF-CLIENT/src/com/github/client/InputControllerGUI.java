package com.github.client;

public class InputControllerGUI {
	private String inputAtual = null;
	private int playerRotate = 0;
	
	public void setInputAtual(String input) {
		input = input.toLowerCase();
		this.inputAtual = input;
		this.setPlayerRotate();
		
	}
	
	public char getDir() { 
		if (this.inputAtual == null) {
			return 'k';  
		}
		if (this.inputAtual.equals("down")) {
			return 'b';
		}
		return this.inputAtual.charAt(0);
	}
	public void dirToZero() {
		this.inputAtual = null;
	}
	public void setPlayerRotate() {
		char moviment = this.inputAtual.charAt(0);
		if (this.inputAtual.equals("down")) {
			moviment = 'b';
		}
		if(moviment == 'w' || moviment == 'u') {
			this.playerRotate = 0;
		}
		if(moviment == 's' || moviment == 'b') {
			this.playerRotate = 180;
		}
		if(moviment == 'a' || moviment == 'l') {
			this.playerRotate = -90;
		}
		if(moviment == 'd' || moviment == 'r') {
			this.playerRotate = 90;
		}
		
	}
	public int getPlayerRotate() {
		return this.playerRotate;
	}
}
