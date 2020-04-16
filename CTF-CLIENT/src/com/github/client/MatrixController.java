package com.github.client;

import java.util.ArrayList;

public class MatrixController {
    //vai receber um response do servidor que vai ter uma matriz
    private char[][] data = new char[30][30];
    private String id;
    private int positionX;
    private int positionY;
    public MatrixController(String id, char[][] d){
    	this.id = id;
        setData(d);
        this.drawMatrizLimit();
    }
    
    public char[][] dataManipulation(InputController input, String dados){
       dados = "M4X12760945-10-0,12760978-5-1,12761371-8-3,";
       char moviment = input.getDir();
       System.out.println(this.id);
       System.out.println(dados);
       String[] processedData = null;
       processedData = this.processDados(dados);
       if(processedData != null) {
    	   for(int x = 0; x < processedData.length; x++) {
        	   System.out.println(processedData);
           }
       }
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
    private String[] processDados(String dados){
    	String[] colunas = null;
    	String[] linhas = null;
    	if(dados != null) {
    		colunas = dados.split(",");
    		for(int x = 0; x < colunas.length; x++) {
    			System.out.println(colunas[x]);
    		}
    		System.exit(0);
    	}
    	 
    	return colunas;
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