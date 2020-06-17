package com.github.client;

public class InputControllerGUI {
	private String inputAtual = null;
	
	public void setInputAtual(String input) {
		input = input.toLowerCase();
		this.inputAtual = input;
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
}
