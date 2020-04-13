package com.github.client;

public class MatrixController {
    //vai receber um response do servidor que vai ter uma matriz
    private char[][] data = new char[30][30];
    private int positionX;
    private int positionY;
    public MatrixController(char[][] d){
        setData(d);
    }
    
    public char[][] dataManipulation(InputController input){
       char moviment = input.getDir();
       this.findPosition();
       switch(moviment){
            //pra cima == -1 no x
            case 'w':
                this.clearCurrenPostion();
                this.positionX -= this.positionX;
                this.data[this.positionX][this.positionY] = 'X';
                break;
            //pra baixo == +1 no x
            case 's':
                this.clearCurrenPostion();
               this.positionX += this.positionX;
                this.data[this.positionX][this.positionY] = 'X';
                break;
            //pra esquerda = y-1
            case 'a':
                this.clearCurrenPostion();
                this.positionY -= this.positionY;
                this.data[this.positionX][this.positionY] = 'X';
                break;
            //pra direita = y + 1
            case 'd':
                this.clearCurrenPostion();
                 this.positionY += this.positionY;
                this.data[this.positionX][this.positionY] = 'X';
                break;
       }
       
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
    private void clearCurrenPostion(){
       this.data[this.positionX][this.positionY] = ' ';
    }
    public char[][] getData() {
        return data;
    }
    public void setData(char[][] data) {
        this.data = data;
    }
}