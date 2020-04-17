package com.github.server;

import java.io.InputStream;
import java.io.PrintStream;

public class Clientes {
	private int id;
	private PrintStream client;
	private InputStream cli;
	
	public Clientes(PrintStream cliente, int id, InputStream cli) {
		this.id = id;
		this.client = cliente;
		this.cli = cli;
	}
	
	public PrintStream getCliente() {
		return this.client;
	}
	
	public int getID() {
		return this.id;
	}
	
	public InputStream getCli() {
		return this.cli;
	}
}
