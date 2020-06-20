package com.github.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class SocketRequestControl {
  
    private static boolean firstplayer;
	private String host;
    private int porta;
    public boolean isKey = false;
    public ArrayList<Meteoro> meteoros = new ArrayList<Meteoro>();
    
    private boolean Console = false;
    
    private Socket cliente;
    private Recebedor recebedor;
    private PrintStream saida; 
    protected InputControllerGUI input;
    private String key = "empty";
    private String hash = "empty";
    private MatrixController matrix;
    private boolean gamestatus = true;
    
    private char[][] matriz;
    
    public SocketRequestControl (String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
    
    public void runGame() throws InterruptedException, IOException {
    	
    	Socket cliente = this.cliente;
    	Recebedor r = this.recebedor;
    	PrintStream saida = this.saida;
    	
    	char[][] data = new char[30][30];
    	
    	if(this.Console == true) {
    		InputController inputC = new InputController();
    	}
	    
    	this.matrix = new MatrixController(data);
	    PrintConsole print = new PrintConsole();
	    
	    boolean GameOver = false;
		
		while(!GameOver) {
			String request = "M4X";
			Thread.sleep(230);
			
			saida.println("Me envie a matriz!");
			if(r.select == 0 || r.select == 1){
				saida.println("Me envie a matriz!");
			}
			if(r.select == 2){
				//System.out.println("GAME IS ONLINE!!");
				if(this.Console) {
					//data = matrix.dataManipulation(inputC, r.data, r.id);
					ClientConsole.CleanScreen();
					print.setMatriz(data);
					print.RenderPrintConsole();
				}else {
					data = matrix.dataManipulation(input, r.data, r.id);
					this.setMatriz(data);
					request += r.id;
					request += "-" + matrix.getPosX().toString();
					request += "-" + matrix.getPosY().toString();
					saida.println(request);
				}
				
				saida.println("Me envie a matriz!");
			}
			if(r.select == 3 && this.isKey == false) {
				SocketRequestControl.firstplayer = true;
				CriptoController cpt = new CriptoController();
				
				if(this.Console) {
					Scanner s = new Scanner(System.in);
					System.out.println("CAPTURE DE FLAG by BRUNO SAMPAIO, FABRICIO GALUDO, HOBITO e MESTRE CABELO");
					System.out.println("DIGITE A FRASE QUE DESEJA ESCONDER");
					cpt.inputData(s.nextLine());
					System.out.println("DIGITE A CHAVE PARA ENCRIPTAR!");
					cpt.inputKey(s.nextLine());
					s.close();
				}else {
					while(this.hash == "empty" && this.key == "empty") {
						System.out.println("Esperando Chave");
						System.out.println(this.hash);
						System.out.println(this.key);
						
					}
					cpt.inputData(this.hash);
					cpt.inputKey(this.key);
				}
				
				cpt.cesar();
				saida.println("K3Y-" + cpt.getFinalString() + "-" + cpt.getKey());
				this.isKey = true;
				System.out.print("Salvando.....");
			}
			if(r.select == 4) {
				System.out.println("a frase é:" + r.text + "a key é: " + r.key);
				GameOver = true;
				this.gamestatus = false;
			}
			
		}
		saida.close();
        cliente.close();
    }

	private void setMatriz(char[][] data) {
		this.matriz = data;
		
	}
	
	public char[][] getMatriz() {
		return this.matriz;
		
	}

	public boolean executa() throws IOException {
		
        try {
			this.cliente = new Socket(this.host, this.porta);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ConnectException e) {
			e.printStackTrace();
			return false;
		}
        
        System.out.println("O cliente se conectou ao servidor!");
        
        // thread para receber mensagens do servidor
	    this.recebedor = new Recebedor(cliente.getInputStream());
        new Thread(recebedor).start();

        // lê msgs do teclado e manda pro servidor
        this.saida = new PrintStream(cliente.getOutputStream());
        
		return true;
    }
	 
	public void setConsole(boolean param) {
		this.Console = param;
	}

	public boolean getGameStatusFirstPlayer() {
		return SocketRequestControl.firstplayer;
	}     
	
	public boolean getGameStatus() {
		return this.gamestatus;
	}
	
	public void setKey(String value) {
		this.key = value;
	}
	
	public void setHash(String value) {
		this.hash = value;
	}
	public void setInput(InputControllerGUI input) {
		this.input = input;
	}
	public int getPlayerRotate() {
		return this.input.getPlayerRotate();
	}
}