package com.github.server;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	private int id;
    private InputStream cliente;
    private Server servidor;
    private PrintStream ps;
    
    public TrataCliente(PrintStream ps, InputStream cliente, int id, Server servidor) {
    	this.id = id;
    	this.cliente = cliente;
        this.servidor = servidor;
        this.ps = ps;
        
    }

    public Clientes getCliente() {
        Clientes cli = new Clientes(this.ps, this.id, this.cliente);
        return cli;
    }

    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
        
			servidor.distribuiMensagem(s.nextLine(), this.id);
			
        }
     // Saiu do loop, é porque desconectou...
        servidor.unsetCliente(id); // Remove da lista de clientes da classe Servidor lá trás...
        System.out.println("Cliente " + id + " desconectado!");

        s.close();
 
    }
}