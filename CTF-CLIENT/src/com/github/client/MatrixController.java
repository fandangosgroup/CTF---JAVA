package com.github.client;

import java.util.ArrayList;

public class MatrixController {
    private char[][] data = new char[30][30];
    private String[] positions = null;
    private ArrayList<Integer> enimyPositions = new ArrayList<Integer>();
    private String id;
    private Integer positionX;
    private Integer positionY;
    private boolean nop;
    
    
    public MatrixController(String id, char[][] d){
    	this.nop = false;
    	this.id = id;
        setData(d);
    }
    
    public char[][] dataManipulation(InputController input, String dados){
       System.out.println("Recebendo essa String: " + dados);
       char moviment = input.getDir();
       String[] processedData = null;
       this.processDados(dados);
       System.out.println(this.enimyPositions);
       System.out.println("MINHA POSICAO É X:" + this.positionX);
       System.out.println("MINHA POSICAO É Y:" + this.positionY);
       this.drawMatriz();
       this.enimyPositions.clear();
       
       /*
       switch(moviment){
            //pra cima == -1 no x
            case 'w':
                this.clearCurrenPostion();
                this.positionX = this.positionX - 1;
                this.data[this.positionX][this.positionY] = 'X';
               input.dirToZero();
                break;
            //pra baixo == +1 no x
            case 's':
                this.clearCurrenPostion();
                this.positionX = this.positionX + 1;
                this.data[this.positionX][this.positionY] = 'X';
                input.dirToZero();
                break;
            //pra esquerda = y-1
            case 'a':
                this.clearCurrenPostion();
                this.positionY = this.positionY - 1;
                this.data[this.positionX][this.positionY] = 'X';
                input.dirToZero();
                break;
            //pra direita = y + 1
            case 'd':
                this.clearCurrenPostion();
                 this.positionY = this.positionY + 1;
                this.data[this.positionX][this.positionY] = 'X';
                input.dirToZero();
                break;
       }*/
       
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
    			}else{
    			}
    		}
    	this.positions = colunas;
    	this.positionX = this.getX(myPosition);
    	this.positionY = this.getY(myPosition);
    	this.getEnemiesPositions(myPosition);
    	}
    	 
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
    
    private void clearCurrenPostion(){
       this.data[this.positionX][this.positionY] = ' ';
    }
    private void drawMatriz() {
    	for (int x = 0; x < getData().length; x++) {
    		for (int y = 0; y < getData().length; y++) {
    			if(x == 0 || x == 29 || y == 0 || y == 29) {
    				this.data[x][y] = '#';
    			}
    			if((!this.nop) && x == this.positionX && y == this.positionY) {
    				this.data[x][y] = 'X';
    			}
    			
    		}
    	}
		for(int x = this.enimyPositions.size() - 1; x > 0; x--) {
			this.data[this.enimyPositions.get(x--)][this.enimyPositions.get(x)] = 'O';
		}
    	this.nop = false;
    }
    
    private void getEnemiesPositions(int myPosition){
    	String[] aux;
    	int y = 0;
    	for(int x = 0; x < this.positions.length; x++){
    		if(myPosition == x) {
    			this.positions[x] = null;
    		}
    		if(this.positions[x] != null) {
    			aux = this.positions[x].split("-");
    			this.enimyPositions.add(Integer.parseInt(aux[1]));
    			this.enimyPositions.add(Integer.parseInt(aux[2]));
    		}
    	}
    }
    
    public char[][] getData() {
        return data;
    }
    public void setData(char[][] data) {
        this.data = data;
    }
}