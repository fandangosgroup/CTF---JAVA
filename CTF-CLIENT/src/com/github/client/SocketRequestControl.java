package com.github.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
  
public class SocketRequestControl {
  
    private String host;
    private int porta;
    public boolean isKey = false;
    public SocketRequestControl (String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

	public void executa() throws UnknownHostException, IOException, InterruptedException {
        Socket cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor!");
     // thread para receber mensagens do servidor
	    Recebedor r = new Recebedor(cliente.getInputStream());
        new Thread(r).start();
        
        char[][] data = new char[30][30];
	    InputController input = new InputController();
	    MatrixController matrix = new MatrixController(data);
	    PrintConsole print = new PrintConsole();
	    
        // lê msgs do teclado e manda pro servidor
       
        PrintStream saida = new PrintStream(cliente.getOutputStream());
 	       
 	       	boolean GameOver = false;
			
			while(!GameOver) {
				String request = "M4X";
				Thread.sleep(140);
				saida.println("Me envie a matriz!");
				if(r.select == 0 || r.select == 1){
					saida.println("Me envie a matriz!");
				}
				if(r.select == 2){
					data = matrix.dataManipulation(input, r.data, r.id);
					Client.CleanScreen();
					print.setMatriz(data);
					print.RenderPrintConsole(true);
					request += r.id;
					request += "-" + matrix.getPosX().toString();
					request += "-" + matrix.getPosY().toString();
					saida.println(request);
					saida.println("Me envie a matriz!");
				}
				if(r.select == 3 && this.isKey == false) {
					Scanner s = new Scanner(System.in);
					System.out.println("CAPTURE DE FLAG by BRUNO SAMPAIO, FABRICIO GALUDO, HOBITO e MESTRE CABELO");
					System.out.println("DIGITE A FRASE QUE DESEJA ESCONDER");
					CriptoController cpt = new CriptoController();
					cpt.inputData(s.nextLine());
					System.out.println("DIGITE A CHAVE PARA ENCRIPTAR!");
					cpt.inputKey(s.nextLine());
					s.close();
					saida.println("VTNC");
					cpt.cesar();
					saida.println("K3Y-" + cpt.getFinalString() + "-" + cpt.getKey());
					this.isKey = true;
					System.out.print("Salvando.....");
				}
				if(r.select == 4) {
					System.out.println("a frase é:" + r.text + "a key é: " + r.key);
					GameOver = true;
				}
				
			}
			saida.close();
	        cliente.close();
    }                
     
	
	//CLASSE DIFERENTE
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
            	this.inputSelect(); 
            }
        }
        
        
        private void inputSelect(){
        	if(this.inputController.substring(0, 7).equals("idUser:") && this.isIded.equals(false)){
        		this.id = this.inputController.substring(7, 15);
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
        	if(this.inputController.substring(0, 7).equals("GameOver")){
        		String[] aux = this.inputController.split("-");
        		this.text = aux[1];
        		this.key = aux[2];
        		this.select = 4;
        	}
        
        }
    }
}