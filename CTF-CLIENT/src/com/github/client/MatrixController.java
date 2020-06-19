package com.github.client;

import java.util.ArrayList;

public class MatrixController {
    private char[][] data = new char[30][30];
    private String[] positions = null;
    private ArrayList<Integer> enimyPositions = new ArrayList<Integer>();
    private ArrayList<Integer> teamPositions = new ArrayList<Integer>();
    private String id;
    private Integer positionX;
    private Integer positionY;
    private boolean nop;
    private String myTeam;
    private Integer flagPositionX;
    private Integer flagPositionY;
    private boolean isFlagPresent = false;
    
    public MatrixController(char[][] d){
    	this.nop = false;
        setData(d);
    }
   
    public char[][] dataManipulation(InputControllerGUI input, String dados, String id){
        this.id = id;
        char moviment = input.getDir();
        this.processDados(dados);
        this.movePlayer(moviment, input);
        //System.out.println(dados);
        //System.out.println("meu x é: " + this.positionX.toString());
        //System.out.println("meu y é: " + this.positionY.toString());
        //System.out.println("meu time é: " + this.myTeam);
        //System.out.println("teamPosistions" + this.teamPositions);
        this.clearMatriz(); 
        this.drawMatriz();
        this.enimyPositions.clear();
        this.teamPositions.clear();
        this.isFlagPresent = false; 
       
        return data;
   }

    private void processDados(String dados){
    	String data = dados.substring(3, dados.length());
    	String[] colunas = null;
    	boolean ret = false;
    	int myPosition = 102938;
    	if(dados != null) {
    		colunas = data.split(",");
    		for(int x = 0; x < colunas.length; x++) {
    			ret = colunas[x].matches(this.id + "(.*)");
    			if(ret == true) {
    				myPosition = x;
    			}
    		}
    	this.positions = colunas;
    	this.captureTheFlag();
    	this.positionX = this.getX(myPosition);
    	this.positionY = this.getY(myPosition);
    	this.myTeam = this.getTeam(myPosition);
    	this.getOthersPositions(myPosition);
    	}
    	 
    }
    
    private String getTeam(int x) {
    	String[] aux;
    	aux = this.positions[x].split("-");
    	for(int y = 0; y < this.positions.length; y++) {
    		//System.out.println("posições" + this.positions[y]);
    	}
    	for(x = 0; x < aux.length; x++) {
    		//System.out.println("buscando time em no array " + aux[x]);
    	}
    	
    	return aux[3];
    }
    
    private Integer getX(int x){
    	String[] aux;
    	if(x == 102938) {
    		this.nop = true;
    		return x;
    	}
    	aux = this.positions[x].split("-");
    	return Integer.parseInt(aux[1]);
    }
    
    private Integer getY(int y) {
    	String[] aux;
    	if(y == 102938) {
    		this.nop = true;
    		return y;
    	}
    	aux = this.positions[y].split("-");
    	return Integer.parseInt(aux[2]);
    }
    
    private void clearCurrentPostion(){
       this.data[this.positionX][this.positionY] = ' ';
    }
    private void clearMatriz(){
    	for (int x = 0; x < getData().length; x++) {
    		for (int y = 0; y < getData().length; y++) {
    			this.data[x][y] = ' ';
    		}
    	}
    }
    
    private void drawMatriz() {
    	for (int x = 0; x < getData().length; x++) {
    		for (int y = 0; y < getData().length; y++) { 
    			if(x == 0 || x == 29 || y == 0 || y == 29) {
    				this.data[x][y] = '#';
    			}
    			if(x == 15 && y != 0 && y != 29) {
    				this.data[x][y] = '-';
    			}
    			if((!this.nop) && x == this.positionX && y == this.positionY) {
    				this.data[x][y] = 'X';
    			}
    		}
    	}
    	for(int x = this.teamPositions.size() - 1; x > 0; x--) {
			this.data[this.teamPositions.get(x--)][this.teamPositions.get(x)] = 'Y';
		}
		for(int x = this.enimyPositions.size() - 1; x > 0; x--) {
			this.data[this.enimyPositions.get(x--)][this.enimyPositions.get(x)] = 'O';
		}
		for (int x = 0; x < getData().length; x++) {
    		for (int y = 0; y < getData().length; y++) {
    			if(this.isFlagPresent == true) {
    				if(x == this.flagPositionX && y == this.flagPositionY) {
        				this.data[x][y] = '@';
    				}
    			}
    		}
		}
		
    	this.nop = false;
    }
    
    private void getOthersPositions(int myPosition){
    	String[] aux;
    	for(int x = 0; x < this.positions.length; x++){
    		if(myPosition == x) {
    			this.positions[x] = null;
    		}
    		if(this.positions[x] != null) {
    			aux = this.positions[x].split("-");
    			if(!(aux[3].equals(this.myTeam))){
    				this.enimyPositions.add(Integer.parseInt(aux[2]));
        			this.enimyPositions.add(Integer.parseInt(aux[1]));
    			}else{
    				this.teamPositions.add(Integer.parseInt(aux[2]));
        			this.teamPositions.add(Integer.parseInt(aux[1]));
    			}
    		}
    	}
    }
    private void movePlayer(char moviment, InputControllerGUI input){
         //pra cima == -1 no x
         if(moviment == 'w' || moviment == 'u') {
        	 if(this.positionX > 1) {
        		 this.clearCurrentPostion();
                 this.positionX = this.positionX - 1;
        	 }
         }
         //pra baixo == +1 no x
         if(moviment == 's' || moviment == 'b') {
        	 if(this.positionX < 28) {
        		 this.clearCurrentPostion();
        		 this.positionX = this.positionX + 1;
        	 }
    	}
         //pra esquerda = y-1
         if(moviment == 'a' || moviment == 'l') {
        	 if(this.positionY > 1) {
                 this.clearCurrentPostion();
                 this.positionY = this.positionY - 1;
            	 }
         }        	 
         //pra direita = y + 1
         if(moviment == 'd' || moviment == 'r') {
        	 if(this.positionY < 28) {
        		 this.clearCurrentPostion();
        		 this.positionY = this.positionY + 1;
        	 }
         }
         input.dirToZero();
    }
    private void captureTheFlag() {
    	for(int x =0; x < this.positions.length; x++) {
    		String[] aux;
    		if(this.positions[x].matches("FL4G(.*)")) {
    			this.isFlagPresent = true;
    			
    			aux = this.positions[x].split("-");
    			this.flagPositionX = Integer.parseInt(aux[1]);
    			this.flagPositionY = Integer.parseInt(aux[2]);
    			this.positions[x] = null;
    		}
    		//System.out.println(this.positions[x]);
    	}
    }
    public char[][] getData() {
        return data;
    }
    public void setData(char[][] data) {
        this.data = data;
    }
    public Integer getPosX(){
    	return this.positionX;
    }
    public Integer getPosY(){
    	return this.positionY;
    }
}