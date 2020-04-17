package com.github.client;

public class CriptoController {
	private String data;
	private String key;
	private int cesarNumber;
	private String finalString;
	
	
	public void inputData(String s) {
		this.data = s;
	}
	public void inputKey(String s) {
		this.key = s;
	}
	public void cesar() {
		this.cesarNumber = this.key.length();
		this.finalString = cesarExecute(this.data, this.cesarNumber);
	}
	public String getFinalString(){
		return this.finalString;
	}
	
	 private String cesarExecute(String texto, int chave){
         
         StringBuilder textoCifrado = new StringBuilder();
          
         int tamanhoTexto = texto.length();
           
          
         for(int c=0; c < tamanhoTexto; c++){
             
            int letraCifradaASCII = ((int) texto.charAt(c)) + chave;
              
            if (texto.charAt(c) > 97 && texto.charAt(c) <  122 ) {
                while (letraCifradaASCII > 122) {
                    letraCifradaASCII = 97; }
            }
            if (texto.charAt(c) > 65 && texto.charAt(c) <  60 ) {
                while (letraCifradaASCII > 90) {
                    letraCifradaASCII = 65;
                 }
            }

        textoCifrado.append( (char)letraCifradaASCII );
         }
           
         return textoCifrado.toString();
	} 
}
