package com.github.client;

import java.util.ArrayList;

public class MatrixController {
    //vai receber um response do servidor que vai ter uma matriz
    private char[][] data = new char[30][30];
    private String id;
    private Integer positionX;
    private Integer positionY;
    private boolean nop;
    public MatrixController(String id, char[][] d){
    	this.nop = false;
    	this.id = "12760978";
        setData(d);
        this.drawMatrizLimit();
    }
    
    public char[][] dataManipulation(InputController input, String dados){
       dados = "M4X12760945-10-0,12760978-5-1,12761371-8-3,";
       char moviment = input.getDir();
       System.out.println(this.id);
       System.out.println(dados);
       String[] processedData = null;
       this.processDados(dados);
      
       /*this.findPosition();
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
    private void findPosition(){
        for (int x = 0; x < getData().length; x++){
            for (int y = 0; y < getData().length; y++){
                if(this.data[x][y] == 'X'){
                    this.positionX = x;
                    this.positionY = y;
                    break;
                }
            }
        }
    }
    private void processDados(String dados){
    	String data = dados.substring(3, dados.length());
    	String[] colunas = null;
    	boolean ret;
    	int myPosition = 102938;
    	if(dados != null) {
    		colunas = data.split(",");
    		for(int x = 0; x < colunas.length; x++) {
    			ret = colunas[x].matches(this.id + "(.*)");
    			if(ret == true) {
    				myPosition = x;
    			}else {
    				System.out.printf("id nao encontrado");
    			}
    			
    		}
    	this.positionX = this.getX(myPosition, colunas);
    	this.positionY = this.getY(myPosition, colunas);
    	
    	System.out.printf("x é %d\n",this.positionX);
    	System.out.printf("y é %d\n",this.positionX);
    		System.exit(0);
    	}
    	 
    }
    private Integer getX(int x, String[] data){
    	String[] aux;
    	if(x == 102938) {
    		this.nop = true;
    		return x;
    	}
    	aux = data[x].split("-");
    	return Integer.parseInt(aux[1]);
    }
    
    private Integer getY(int y, String[] data) {
    	String[] aux;
    	if(y == 102938) {
    		this.nop = true;
    		return y;
    	}
    	aux = data[y].split("-");
    	for(int x = 0; x < aux.length; x++) {
    		System.out.println(aux[x]);
    	}
    	return Integer.parseInt(aux[1]);
    }
    private void clearCurrenPostion(){
       this.data[this.positionX][this.positionY] = ' ';
    }
    private void drawMatrizLimit() {
    	for (int x = 0; x < getData().length; x++) {
    		for (int y = 0; y < getData().length; y++) {
    			if(x == 0 || x == 29 || y == 0 || y == 29) {
    				this.data[x][y] = '#';
    			}
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