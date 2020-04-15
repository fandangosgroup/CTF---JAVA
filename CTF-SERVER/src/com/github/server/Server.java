package com.github.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
  
public class Server {
	private int porta;
    private List<Clientes> clientes;
    private List<Positions> positions;
	
	public static void main(String[] args) throws IOException {
        // inicia o servidor
        new Server(12345).executa();
    }

    public Server (int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Clientes>();
        this.positions = new ArrayList<Positions>();
    }
    
    public void unsetCliente(int id) {
    	for(Clientes cli : this.clientes) {
    		if(cli.getID() == id) {
    			this.clientes.remove(cli);
    			break;
    		}
    	}
    }

    public void executa () throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Servidor Aberto!");
        System.out.println("Porta 12345 aberta!");
        System.out.println("Iniciado!");

        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            System.out.println("Nova conex�o com o cliente " + cliente.getInetAddress().getHostAddress());
            System.out.println(cliente.getRemoteSocketAddress());
            
            // adiciona saida do cliente � lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            
            String id1,id2;
            id1 = cliente.getRemoteSocketAddress().toString().substring(1, 4);
            id2 = cliente.getRemoteSocketAddress().toString().substring(11, 16);
            id1 = id1 + id2;
            int aux = Integer.parseInt(id1);
            
//            Clientes cl = new Clientes(ps, aux, cliente.getInputStream());
//            this.clientes.add(cl);

            // cria tratador de cliente numa nova thread
            ps.println("idUser:"+aux);
            TrataCliente tc = new TrataCliente(ps, cliente.getInputStream(), aux, this);
            
            Clientes cl = tc.getCliente();
            this.clientes.add(cl);
            
            Positions pos = tc.getPos();
            this.positions.add(pos);
            
            new Thread(tc).start();
        }
        

    }

    public void distribuiMensagem(String msg, int id) {
        // envia msg para todo mundo
    	//System.out.println("msg:"+msg);
    	String location = "";
    	int aux;
    	
    	for (Positions Pos : this.positions) {//12 10 20
    		aux = Pos.getPosID();
    		location = location + Integer.toString(aux);
    		location = location + ',';
    		
    		aux = Pos.getPosX();
    		location = location + Integer.toString(aux);
    		location = location + ',';
    		
    		aux = Pos.getPosY();
    		location = location + Integer.toString(aux);
    		location = location + '|';
    	}
    	
        for (Clientes cliente : this.clientes) {
        	//System.out.println(msg);
        	//System.out.println("id:"+id);
        	//System.out.println("Cl:"+cliente.getID());
        	
        	cliente.getCliente().println("Quantidade:"+this.clientes.size());
        	
        	
        	
        	System.out.println(location);
        	cliente.getCliente().println(location);
        	
        	if(cliente.getID() == id) {
        		//System.out.println("entrou no if");
        		//cliente.getCliente().println(data);
        		
        		cliente.getCliente().println(msg);
        		//System.out.println(cliente.getCliente().checkError());
        		
        		//System.out.println("chegou no if fim");
        		
        		//cliente.getCliente().println(msg);
        	}
			
        }
    }
}
     