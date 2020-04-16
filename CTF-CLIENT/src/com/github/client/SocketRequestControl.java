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
	    MatrixController matrix = new MatrixController(r.id, data);
	    PrintConsole print = new PrintConsole();
	    
        // lê msgs do teclado e manda pro servidor
       
        PrintStream saida = new PrintStream(cliente.getOutputStream());
      
        	//saida.println("Me envie a matriz!");
 	       
 	       	boolean GameOver = false;
			
			while(!GameOver) {
				Thread.sleep(200);
				saida.println("Me envie a matriz!");
				if(r.data.equals("EUODEIOMINHAVIDA")){
					saida.println("Me envie a matriz!");

				}else {
					data = matrix.dataManipulation(input, r.data);
					Client.CleanScreen();
					print.setMatriz(data);
					print.RenderPrintConsole(true);
					
				}


			}
			saida.close();
	        cliente.close();

    }                
    
    public class Recebedor implements Runnable {
        public String id = "";
        private String inputController;
        private InputStream servidor;
        public String data = "";

        public Recebedor(InputStream servidor) {
            this.servidor = servidor;
        }

        public void run() {
            // recebe msgs do servidor e imprime na tela
            Scanner s = new Scanner(this.servidor);
            while (s.hasNextLine()) {
            	this.inputController = s.nextLine();
            	//System.out.println("aqiu:"+this.inputController.substring(0, 3));
            	
            	if(this.inputController.substring(0, 7).equals("idUser:")) {
            		this.id = this.inputController.substring(7, 15);
            		this.data = "EUODEIOMINHAVIDA";
            	}
            	else if(this.inputController.substring(0, 3).equals("M4X")) {
            		//System.out.print("ENTROUEEEEEEEEEEEEEEEEEEEEEEEEE");
            		this.data = inputController;
            		break;
            	}else {
            		this.data = "EUODEIOMINHAVIDA";
            	}
                System.out.println(s.nextLine());
            }
        }
    }
}