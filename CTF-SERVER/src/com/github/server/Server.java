package com.github.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
  
public class Server {
	public static void main(String[] args) throws IOException {
        // inicia o servidor
        new Server(12345).executa();
    }

    private int porta;
    private List<Clientes> clientes;

    public Server (int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Clientes>();
    }

    public void executa () throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta 12345 aberta!");

        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
            System.out.println(cliente.getRemoteSocketAddress());
            
            // adiciona saida do cliente à lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            
            String id1,id2;
            id1 = cliente.getRemoteSocketAddress().toString().substring(1, 4);
            id2 = cliente.getRemoteSocketAddress().toString().substring(11, 16);
            id1 = id1 + id2;
            int aux = Integer.parseInt(id1);
            
            Clientes cl = new Clientes(ps, aux);
            this.clientes.add(cl);

            // cria tratador de cliente numa nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), aux, this);
            new Thread(tc).start();
        }

    }

    public void distribuiMensagem(String msg, int id) {
        // envia msg para todo mundo
        for (Clientes cliente : this.clientes) {
        	System.out.println(msg);
        	System.out.println(id);
        	if(cliente.getID() == id) {
        		cliente.getCliente().println(msg);
        	}	
        }
    }
}
     
