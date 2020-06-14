package com.github.client;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {
    public String id = "";
    private Boolean isIded = false;
    private String inputController;
    private InputStream servidor;
    public String data = "";
    public int select = 0;
    public String text;
    public String key;
    public Recebedor(InputStream servidor) {
        this.servidor = servidor;
    }
    

    public void run() {
        // recebe msgs do servidor e imprime na tela
        Scanner s = new Scanner(this.servidor);
        while (s.hasNextLine()) {
        	this.inputController = s.nextLine();
        	//System.out.println(this.inputController);
        	this.inputSelect(); 
        }
        s.close();
    }
    
    
    private void inputSelect(){
    	if(this.inputController.substring(0, 7).equals("idUser:") && this.isIded.equals(false)){
    		this.id = this.inputController.substring(7, this.inputController.length());
    		this.isIded = true;
    		this.select = 1;
    	}
    	if(this.inputController.substring(0, 3).equals("M4X")){
    		this.data = inputController;
    		this.select = 2;
    	}
    	if(this.inputController.equals("PrimeiroPlayer")) {
    		this.select = 3;
    	}
    	if(this.inputController.substring(0, 8).equals("GameOver")){
    		String[] aux = this.inputController.split("-");
    		this.text = aux[1];
    		this.key = aux[2];
    		this.select = 4;
    	}
    
    }
}
