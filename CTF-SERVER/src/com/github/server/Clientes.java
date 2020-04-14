package com.github.server;

import java.io.PrintStream;
import java.net.Socket;

public class Clientes {
	private int id;
	private char[][] matriz;
	private char tipo;
	private PrintStream client;
	
	public Clientes(PrintStream cliente, int id) {
		this.id = id;
		this.client = cliente;
	}
	
	public PrintStream getCliente() {
		return this.client;
	}
	
	public int getID() {
		return this.id;
	}
}
